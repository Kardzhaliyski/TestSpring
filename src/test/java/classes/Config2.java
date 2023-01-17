package classes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config2 {
    @Bean
    public static byte anInt1() {
        return 21;
    }

    @Bean(name = "i2")
    public static int anInt2() {
        return 22;
    }

    @Bean(name = "i3")
    public static int anInt3() {
        return 23;
    }

    @Bean
    @Qualifier("i4")
    public static int anInt5() {
        return 24;
    }

    @Bean
    @Qualifier("maxNum")
    public static int aNum() {
        return Integer.MAX_VALUE;
    }

    @Bean
    @Qualifier("maxNum2")
    public static int aNum22() {
        return Integer.MAX_VALUE;
    }
}
