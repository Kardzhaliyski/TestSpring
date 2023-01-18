package org.example;

import classes.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertySource;

import java.util.Properties;

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
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(F.class, FImpl.class));
    }

    @Test
    void autowireWithInterface2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FS.class, FImpl.class);
        FS fs = context.getBean(FS.class);
        F fField = fs.fField;
        assertNotNull(fField);
    }

    @Test
    void autowireWithAbstractClass() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(G.class));
    }

    @Test
    void autowireWithAbstractClass2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GS.class, GImpl.class);
        GS gs = context.getBean(GS.class);
        assertNotNull(gs);
        assertNotNull(gs.gField);
    }

    @Test
    void autowireWithAbstractClass3() {
        assertThrows(BeanCreationException.class, () -> new AnnotationConfigApplicationContext(GS.class, GImpl.class, GImpl2.class));
    }

    @Test
    void callMethodsConstructorsAndFields() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(H.class, B.class);
        H bean = context.getBean(H.class);
        assertNotNull(bean.bField1);
        assertNotNull(bean.bField2);
        assertNotNull(bean.bField3);
    }

    @Test
    void multipleConfigsKeepPrimaryIfMethodNotReplaced() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, Config2.class, DB.class);
        D bean = context.getBean(D.class);
        DB bean2 = context.getBean(DB.class);
        assertEquals(11, bean.n);
    }

    @Test
    void multipleConfigsReplacesMethodsWithSameMethodName() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, Config2.class, DB.class);
        DB bean2 = context.getBean(DB.class);
        assertEquals(23, bean2.n);
    }

    @Test
    void multipleConfigsDoesntReplacesMethodsWithSameQualifierName() {
        assertThrows(UnsatisfiedDependencyException.class, () -> new AnnotationConfigApplicationContext(Config.class, Config2.class, DC.class));
    }

    @Test
    void multipleConfigReplacingMethodsWithSameNameAndDifferentTypes() {
        assertThrows(UnsatisfiedDependencyException.class, () -> new AnnotationConfigApplicationContext(Config.class, Config2.class, I.class));
    }

    @Test
    void multipleConfigReplacingMethodsWithSameNameAndDifferentTypes2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, Config2.class, J.class);
        J bean = context.getBean(J.class);
        assertEquals(Integer.MAX_VALUE, bean.maxInt);
        assertEquals(Long.MAX_VALUE, bean.maxLong);
    }

    @Test
    void autowireStaticFieldShouldNotSet() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, K.class);
        assertEquals(0, K.num1);
    }

    @Test
    void autowireStaticSetMethodShouldNotSet() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, K.class);
        assertEquals(0, K.num2);
    }

    @Test
    void autowireNonStaticSetMethodShouldSet() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, K.class);
        assertNotEquals(0, K.num3);
    }

    @Test
    void getValueFromPropertyFileUsingAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class, L.class);
        L l = context.getBean(L.class);
        assertEquals("someName", l.name);
    }

    @Test
    void getValueFromProperties() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Properties properties = new Properties();
        String value = "aName";
        properties.put("name", value);
        PropertySource<String> ps = new PropertySource<String>("Custom") {
            final Properties prop = properties;

            @Override
            public String getProperty(String name) {
                return properties.getProperty(name);
            }
        };

        context.getEnvironment().getPropertySources().addLast(ps);
        context.register(L.class);
        context.refresh();

        L l = context.getBean(L.class);
        assertEquals(value, l.name);
    }

    @Test
    void testSingletonScope() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(M.class);
        M m1 = context.getBean(M.class);
        M m2 = context.getBean(M.class);

        assertNotNull(m1);
        assertEquals(m1.num, m2.num);
        assertEquals(m1, m2);
    }

    @Test
    void testPrototypeScope() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestBeanPostProcessor.class, TestBeanPostProcessor2.class, MA.class);
        MA m1 = context.getBean(MA.class);
        MA m2 = context.getBean(MA.class);

        assertNotNull(m1);
        assertNotEquals(m1.num, m2.num);
        assertNotEquals(m1, m2);
    }


    @Test
    void testLifecycleMethods() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(N.class);
        context.registerShutdownHook();
        System.out.println("----------");
    }

    @Test
    void xmlProperty() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config.xml");
        L l = context.getBean(L.class);
        assertEquals("xmlValue", l.name);
    }

    @Test
    void xmlProperty2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("/config.xml");
        context.addBeanFactoryPostProcessor(new TestBeanFactoryPostProcessor());
        context.refresh();

        L l = context.getBean(L.class);
        assertEquals("BeanFactoryPostProcessor", l.name);
    }

    @Test
    void textBeanFactoryPostProcessor() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/config2.xml");
        L l = context.getBean(L.class);
        assertEquals("BeanFactoryPostProcessor", l.name);
    }



}