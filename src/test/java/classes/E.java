package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class E {
    public B b;

    @Autowired(required = false)
    public E(B b) {
        this.b = b;
    }
}
