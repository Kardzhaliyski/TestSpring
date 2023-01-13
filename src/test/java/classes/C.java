package classes;

public class C {
    A aField;
    B bField;

    public C(A aField, B bField) {
        this.aField = aField;
        this.bField = bField;
    }

    public C(B bField, A aField) {
        this.aField = aField;
        this.bField = bField;
    }
}
