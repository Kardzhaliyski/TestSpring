package classes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


public class L {
    @Value("${name}")
    public String name;
}
