package asyncs;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncMethods {
    public static String threadName;
    public static String configuredThreadName;
    public static String VALUE = "Some Value";

    @Async
    public void asyncMethodWithVoidReturnType() {
        threadName = Thread.currentThread().getName();
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        CompletableFuture<String> future = new CompletableFuture<String>();
        threadName = Thread.currentThread().getName();
        try {
            TimeUnit.SECONDS.sleep(1);
            future.complete(VALUE);
        } catch (InterruptedException e) {
            //
        }

        return future;
    }

    @Async
    public Future<String> asyncMethodWithReturnTypeThatThrows() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        throw new IllegalStateException();
    }

//    @Async("threadPoolTaskExecutor")
//    public void asyncMethodWithConfiguredExecutor() {
//        this.configuredThreadName = Thread.currentThread().getName();
//    }
}
