package classes;

import org.springframework.beans.factory.annotation.Autowired;

public class H {
    public B bField1;
    @Autowired
    public B bField2;
    public B bField3;

    @Autowired
    public H(B bField1) {
        this.bField1 = bField1;
        System.out.println("Constructor");
        System.out.println("C -> Field: " + bField2);

    }

    @Autowired
    public H setbField3(B bField3) {
        this.bField3 = bField3;
        System.out.println("Setter");
        System.out.println("S -> Field: " + bField2);
        return this;
    }
}
