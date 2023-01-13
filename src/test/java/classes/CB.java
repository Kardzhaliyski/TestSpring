package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CB {
    A aField;
    B bField;

    @Autowired
    public CB(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }

    @Autowired
    public CB(A aField, B bField) {
        this.aField = aField;
        this.bField = bField;
    }
}
