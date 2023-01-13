package classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class A {
    public B bField;


    public A(B bField) {
        this.bField = bField;
    }
}
