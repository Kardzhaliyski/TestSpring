package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CE {
    A aField;
    B bField;

    @Autowired(required = false)
    public CE(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }

    @Autowired(required = false)
    public CE(A aField, B bField) {
        this.aField = aField;
        this.bField = bField;
    }

}
