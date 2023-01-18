package events;

import org.springframework.context.event.EventListener;

public class MyStringListener {
    public Object receivedObject = null;
    @EventListener
    public void event(String obj) {
        receivedObject = obj;
    }
}
