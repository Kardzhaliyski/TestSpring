package classes;

import org.springframework.beans.factory.annotation.Qualifier;

public class DC {
    public int n;

    public DC(@Qualifier("i4") int n) {
        this.n = n;
    }
}
