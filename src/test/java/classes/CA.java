package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CA {
    A aField;
    B bField;

    @Autowired
    public CA(A aField, B bField) {
        this.aField = aField;
        this.bField = bField;
    }

    public CA(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }
}
