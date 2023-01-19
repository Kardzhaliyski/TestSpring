package events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {

    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    SimpleApplicationEventMulticaster eventMulticaster;

    public void publishEvent(Object obj) {
        eventPublisher.publishEvent(new ApplicationEvent(obj) {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
    }
    public void publishSomething(Object obj) {
        eventPublisher.publishEvent(obj);
    }

    public void multicastSomething(ApplicationEvent event) {
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        eventMulticaster.multicastEvent(event);
    }
}
