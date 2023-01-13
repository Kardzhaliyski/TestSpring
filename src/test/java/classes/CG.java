package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class CG {

    public A aField;
    public B bField;
    public D dField;

    @Autowired(required = false)
    public CG(A aField, B bField, D dField) {
        this.aField = aField;
        this.bField = bField;
        this.dField = dField;
    }

    @Autowired(required = false)
    public CG(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }

    @Autowired(required = false)
    public CG(D dField, A aField) {
        this.aField = aField;
        this.dField = dField;
    }

    @Autowired(required = false)
    public CG(D dField) {
        this.aField = null;
        this.bField = null;
        this.dField = dField;
    }
    @Autowired(required = false)
    public CG(B bField) {
        this.aField = null;
        this.bField = bField;
        this.dField = null;
    }

}
