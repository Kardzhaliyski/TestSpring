package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CF {
    public A aField;
    public B bField;

    @Autowired(required = false)
    public CF(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }

    @Autowired(required = false)
    public CF(B bField) {
        this.aField = null;
        this.bField = bField;
    }
}
