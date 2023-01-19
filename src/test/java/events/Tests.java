package events;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    void testImplListenerReceivePublishedObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyImplListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyImplListener listener = context.getBean(MyImplListener.class);

        String event = "event";
        assertNotEquals(event, listener.eventSourceReceived);
        publisher.publishEvent(event);
        assertEquals(event, listener.eventSourceReceived);
    }

    @Test
    void testListenerReceivePublishedObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyListener listener = context.getBean(MyListener.class);

        String event = "event";
        assertNotEquals(event, listener.receivedObject);
        publisher.publishSomething(event);
        assertEquals(event, listener.receivedObject);
    }

    @Test
    void testListenerReceiveContextRefreshEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyListener.class);
        MyListener listener = context.getBean(MyListener.class);
        assertEquals(ContextRefreshedEvent.class, listener.receivedObject.getClass());
    }

    @Test
    void testMultipleListenersReceivePublishedObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyListener.class, MyListener2.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyListener listener = context.getBean(MyListener.class);
        MyListener2 listener2 = context.getBean(MyListener2.class);

        String event = "event";
        assertNotEquals(event, listener.receivedObject);
        assertNotEquals(event, listener2.receivedObject);
        publisher.publishSomething(event);
        assertEquals(event, listener.receivedObject);
        assertEquals(event, listener2.receivedObject);
    }

    @Test
    void testListenerReceiveOnlySpecificTypePublishedObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyStringListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyStringListener listener = context.getBean(MyStringListener.class);

        Long obj1 = 33L;
        assertNotEquals(obj1, listener.receivedObject);
        publisher.publishSomething(obj1);
        assertNotEquals(obj1, listener.receivedObject);

        String obj2 = "event";
        assertNotEquals(obj2, listener.receivedObject);
        publisher.publishSomething(obj2);
        assertEquals(obj2, listener.receivedObject);
    }

    @Test
    void testSingleThreadPublisher() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MySlowListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MySlowListener listener = context.getBean(MySlowListener.class);
        publisher.publishSomething("event");

        assertNotNull(listener.event1Finished);
        assertNotNull(listener.event2Finished);
        assertTrue(listener.event1Finished <= listener.event2Started ||
                listener.event2Finished <= listener.event1Started);
    }

    @Test
    void testMultiThreadPublisher() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MySlowListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MySlowListener listener = context.getBean(MySlowListener.class);
        publisher.multicastSomething(new MyApplicationEvent("event"));

        TimeUnit.SECONDS.sleep(1);
        assertNotEquals(0, listener.event1Finished);
        assertNotEquals(0, listener.event2Finished);
        assertTrue(listener.event2Started < listener.event1Finished);
        assertTrue(listener.event1Started < listener.event2Finished);
    }

    @Test
    void testInfiniteLoopListener() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyInfiniteLoopListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        assertThrows(StackOverflowError.class, () -> publisher.publishSomething("Something"));
    }

    @Test
    void testListenerCanRepeatOnItself() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyInfiniteLoopListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        assertThrows(StackOverflowError.class, () -> publisher.publishSomething(0));
    }

    @Test
    void testListenerCanRepeatOnItselfWhileMulticasting() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyInfiniteLoopListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        assertThrows(StackOverflowError.class, () -> publisher.multicastSomething(new MyApplicationEvent(0)));
    }

    @Test
    void testResendingListener() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyEventPublisher.class, MyResendingListener.class);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyResendingListener listener = context.getBean(MyResendingListener.class);

        assertFalse(listener.success);
        publisher.publishSomething(3);
        assertTrue(listener.success);
    }
}
