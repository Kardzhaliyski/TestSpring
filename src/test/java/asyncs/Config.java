package asyncs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class Config {

//    @Bean(name = "threadPoolTaskExecutor")
//    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("MyPrefix");
//        return executor;
//    }
}
