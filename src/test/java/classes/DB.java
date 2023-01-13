package classes;

import org.springframework.beans.factory.annotation.Qualifier;

public class DB {
    public int n;

    public DB(@Qualifier("i3") int n) {
        this.n = n;
    }
}
