package events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyImplListener implements ApplicationListener<ApplicationEvent> {

    public Object eventSourceReceived = null;
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        eventSourceReceived = event.getSource();
    }
}
