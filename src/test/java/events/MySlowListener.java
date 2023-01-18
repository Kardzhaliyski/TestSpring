package events;

import org.springframework.context.event.EventListener;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


public class MySlowListener {

    public Long event1Started = null;
    public Long event1Finished = null;
    public Long event2Finished = null;
    public Long event2Started = null;
    @EventListener
    public void event1(Object obj) throws InterruptedException {

        event1Started = System.currentTimeMillis();
        task();
        event1Finished = System.currentTimeMillis();
    }

    @EventListener
    public void event2(Object obj) throws InterruptedException {
        event2Started = System.currentTimeMillis();
        task();
        event2Finished = System.currentTimeMillis();
    }

    private static void task() {
        String str = "";
        for (int i = 0; i < 100000; i++) {
            str = str + Math.random();
            if(i % 100 == 0) {
                str = str.substring(str.length() / 2);
            }
        }
    }
}
