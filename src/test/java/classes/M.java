package classes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class M {
    static int counter = 1;
    public int num = 0;

    public M() {
        num = counter++;
    }
}
