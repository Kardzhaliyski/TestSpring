package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CD {
    A aField;
    B bField;

    @Autowired(required = false)
    public CD(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }

    @Autowired
    public CD(A aField, B bField) {
        this.aField = aField;
        this.bField = bField;
    }
}
