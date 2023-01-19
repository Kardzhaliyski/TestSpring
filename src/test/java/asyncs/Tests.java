package asyncs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    AnnotationConfigApplicationContext context;
    AsyncMethods asyncMethodsObject;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext("asyncs");
        asyncMethodsObject = context.getBean(AsyncMethods.class);
    }

    @Test
    void testMethodRunInDifferentThread() throws InterruptedException {
        asyncMethodsObject.asyncMethodWithVoidReturnType();
        TimeUnit.SECONDS.sleep(1);
        String methodThreadName = asyncMethodsObject.threadName;
        String currentThreadName = Thread.currentThread().getName();
        assertNotEquals(currentThreadName, methodThreadName);
    }

    @Test
    public void testAsyncForMethodsWithReturnType() throws InterruptedException, ExecutionException, TimeoutException {
        Future<String> future = asyncMethodsObject.asyncMethodWithReturnType();

        assertFalse(future.isDone());
        String value = future.get(2, TimeUnit.SECONDS);
        assertNotNull(value);
        assertEquals(AsyncMethods.VALUE, value);
    }

    @Test
    public void testAsyncForMethodsWithReturnTypeThatThrows() throws InterruptedException, TimeoutException {
        Future<String> future = asyncMethodsObject.asyncMethodWithReturnTypeThatThrows();
        assertFalse(future.isDone());
        assertThrows(ExecutionException.class, () -> future.get(2, TimeUnit.SECONDS));
    }

    @Test
    public void testAsyncForMethodsWithReturnTypeThatThrows2() throws InterruptedException, TimeoutException {
        Future<String> future = asyncMethodsObject.asyncMethodWithReturnTypeThatThrows();

        assertFalse(future.isDone());
        try {
            future.get(2, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            assertEquals(IllegalStateException.class, e.getCause().getClass());
            return;
        }

        assertFalse(true);
    }

//    @Test
//    public void test() throws InterruptedException {
//        asyncMethodsObject.asyncMethodWithVoidReturnType();
//        asyncMethodsObject.asyncMethodWithConfiguredExecutor();
//        TimeUnit.SECONDS.sleep(1);
//
//        String currentThreadName = Thread.currentThread().getName();
//        System.out.println(currentThreadName);
//        String methodThreadName = asyncMethodsObject.threadName;
//        System.out.println(methodThreadName);
//        System.out.println(asyncMethodsObject.configuredThreadName);
//        assertNotEquals(currentThreadName, methodThreadName);
//    }


}
