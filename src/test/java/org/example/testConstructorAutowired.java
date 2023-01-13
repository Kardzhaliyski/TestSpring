package org.example;

import classes.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class testConstructorAutowired {
    @Test
    void shouldSetObject() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A.class, B.class);
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertNotNull(a.bField);
    }

    @Test
    void shouldThrowForMultipleConstructorsAndNoAutowired() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(A.class, B.class, C.class));
    }

    @Test
    void shouldNotThrowForMultipleConstructorsAndOneAutowired() {
        assertDoesNotThrow(() -> new AnnotationConfigApplicationContext(A.class, B.class, CA.class));
    }

    @Test
    void shouldThrowForMultipleConstructorsAllAutowiredAllRequired() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(A.class, B.class, CB.class));
    }

    @Test
    void shouldThrowForMultipleConstructorsAllAutowiredSomeRequired() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(A.class, B.class, CD.class));
    }

    @Test
    void shouldNotThrowForMultipleConstructorsAllAutowiredNoRequired() {
        assertDoesNotThrow(() -> new AnnotationConfigApplicationContext(A.class, B.class, CE.class));
    }

    @Test
    void multipleConstructorTest1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A.class, B.class, CF.class);
        CF o = context.getBean(CF.class);
        assertNotNull(o);
        assertNotNull(o.aField);
        assertNotNull(o.bField);
    }

    @Test
    void multipleConstructorTest2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(B.class, CF.class);
        CF o = context.getBean(CF.class);
        assertNotNull(o);
        assertNull(o.aField);
        assertNotNull(o.bField);
    }

    @Test
    void multipleConstructorTest3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, B.class, D.class, CG.class);
        CG o = context.getBean(CG.class);
        assertNotNull(o);
    }

    @Test
    void multipleConstructorTest4() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(Config.class, A.class, D.class, CG.class));
    }

    @Test
    void multipleConstructorTest5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, A.class, B.class, CG.class);
        CG o = context.getBean(CG.class);
        assertNotNull(o);
        assertNotNull(o.aField);
        assertNotNull(o.bField);
    }

    @Test
    void multipleConstructorTest6() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, A.class, B.class, D.class, CG.class);
        CG o = context.getBean(CG.class);
        assertNotNull(o);
        assertNotNull(o.aField);
        assertNotNull(o.bField);
        assertNotNull(o.dField);
    }

    @Test
    void shouldThrowForPrimitives() {
        assertAll(() -> assertThrows(UnsatisfiedDependencyException.class, () -> new AnnotationConfigApplicationContext(D.class, int.class)),
                () -> assertThrows(UnsatisfiedDependencyException.class, () -> new AnnotationConfigApplicationContext(D.class)));
    }

    @Test
    void shouldSelectPrimaryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, D.class);
        D d = context.getBean(D.class);
        assertNotNull(d.n);
        assertEquals(11, d.n);
    }

    @Test
    void shouldSelectPrimaryBean2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, DA.class);
        DA d = context.getBean(DA.class);
        assertNotNull(d.n);
        assertEquals(11, d.n);
    }

    @Test
    void shouldSelectBeanWithNameEqualsQualifierAnnotationValue() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, DB.class);
        DB d = context.getBean(DB.class);
        assertNotNull(d.n);
        assertEquals(13, d.n);
    }

    @Test
    void shouldSelectBeanWithNameEqualsQualifierAnnotationValue2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, DC.class);
        DC d = context.getBean(DC.class);
        assertNotNull(d.n);
        assertEquals(14, d.n);
    }

    @Test
    void autowireWithInterface() {
        assertThrows(BeanCreationException.class ,() -> new AnnotationConfigApplicationContext(F.class, FImpl.class));
    }

    @Test
    void autowireWithInterface2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FS.class, FImpl.class);
        FS fs = context.getBean(FS.class);
        F fField = fs.fField;
        assertNotNull(fField);
    }
}