package classes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application.properties")
@Configuration
public class Config {
    @Bean
    @Primary
    public static int anInt() {
        return 11;
    }

    @Bean(name = "i2")
    public static int anInt2() {
        return 12;
    }

    @Bean(name = "i3")
    public static int anInt3() {
        return 13;
    }

    @Bean
    @Qualifier("i4")
    public static int anInt4() {
        return 14;
    }

    @Bean
    @Qualifier("maxNum")
    public static long aNum() {
        return Long.MAX_VALUE;
    }

    @Bean
    @Qualifier("maxNum2")
    public static long aNum2() {
        return Long.MAX_VALUE;
    }

}
