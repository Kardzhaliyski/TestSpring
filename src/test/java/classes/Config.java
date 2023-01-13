package classes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {
    @Bean
    @Primary
    int anInt() {
        return 11;
    }

    @Bean(name = "i2")
    int anInt2() {
        return 12;
    }

    @Bean(name = "i3")
    int anInt3() {
        return 13;
    }

    @Bean
    @Qualifier("i4")
    int anInt4() {
        return 14;
    }

    @Bean
    F f() {
        return new FImpl();
    }
}
