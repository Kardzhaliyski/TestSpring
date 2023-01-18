package events;

import org.springframework.context.event.EventListener;

public class MyListener {
    public Object receivedObject = null;
    @EventListener
    public void event(Object obj) {
        receivedObject = obj;
    }
}
