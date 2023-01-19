package values;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    static Properties properties;
    AnnotationConfigApplicationContext context;
    FieldInjection fieldInjectionObject;
    MethodInjection methodInjectionObject;

    @BeforeAll
    static void init() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/values.properties"));
    }

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext("values");
        fieldInjectionObject = context.getBean(FieldInjection.class);
        methodInjectionObject = context.getBean(MethodInjection.class);
    }

    @Test
    void testHardcodedValue() {
        assertEquals(fieldInjectionObject.HARDCODED_VALUE, fieldInjectionObject.hardcodedValue);
    }

    @Test
    void testValueFromFile() {
        assertEquals(properties.getProperty("value.from.file"), fieldInjectionObject.valueFromFile);
    }

    @Test
    void testDefaultValue() {
        assertEquals("my default value", fieldInjectionObject.defaultValue);
    }

    @Test
    void testDefaultValue2() {
        assertNotEquals(System.getProperty("os.name"), fieldInjectionObject.defaultValue2);
    }

    @Test
    void testSystemPropertyPriority() {
        assertNotEquals(properties.getProperty("os.name"), fieldInjectionObject.systemPropertyPriority);
        assertEquals(System.getProperty("os.name"), fieldInjectionObject.systemPropertyPriority);
    }

    @Test
    void testArrOfCommaSeparatedValues() {
        String commaSeparatedValues = properties.getProperty("commaSeparatedValues");
        String[] arr = commaSeparatedValues.split(",");
        assertEquals(arr.length, fieldInjectionObject.valueArr.length);
        assertArrayEquals(arr, fieldInjectionObject.valueArr);
    }

    @Test
    void testGetSystemPropertyUsingSpEL() {
        assertNotNull(fieldInjectionObject.systemPropertyBySpEL);
        assertEquals(System.getProperty("os.name"), fieldInjectionObject.systemPropertyBySpEL);
    }

    @Test
    void testGetDefaultValueIfSystemPropertyReturnsNullSpEl() {
        assertEquals(fieldInjectionObject.DEFAULT_VALUE, fieldInjectionObject.unknownSystemProperty);
    }

    @Test
    void testGetValueFromBeanField() {
        assertEquals(properties.getProperty("value.from.file"), fieldInjectionObject.valueFromBean);
    }

    @Test
    void testArrOfCommaSeparatedValuesSpEL() {
        String commaSeparatedValues = properties.getProperty("commaSeparatedValues");
        String[] arr = commaSeparatedValues.split(",");
        assertEquals(arr.length, fieldInjectionObject.valueArrSpEL.length);
        assertArrayEquals(arr, fieldInjectionObject.valueArrSpEL);
    }

    @Test
    void testListOfCommaSeparatedValues() {
        String commaSeparatedValues = properties.getProperty("commaSeparatedValues");
        List<String> list = Arrays.stream(commaSeparatedValues.split(",")).toList();
        assertEquals(list.size(), fieldInjectionObject.valueListSpEL.size());
        assertEquals(list, fieldInjectionObject.valueListSpEL);
    }

    @Test
    void testMethodInjection() {
        assertNotNull(methodInjectionObject.methodInject);
        assertEquals(properties.getProperty("value.from.file"), methodInjectionObject.methodInject);
    }

    @Test
    void testMethodParamInjectionNeedAutowireAnnotation() {
        assertNull(methodInjectionObject.paramInjectNoAutowire);
        assertNotNull(methodInjectionObject.paramInject);
        assertEquals(properties.getProperty("value.from.file"), methodInjectionObject.paramInject);
    }

    @Test
    void testConstructorParamInjection() {
        assertNotNull(methodInjectionObject.constructorInject);
        assertEquals(properties.getProperty("value.from.file"), methodInjectionObject.constructorInject);
    }


}
