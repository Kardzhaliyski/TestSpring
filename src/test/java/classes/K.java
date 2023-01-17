package classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class K {
    @Autowired
    public static int num1;
    public static int num2;
    public static int num3;

    @Autowired
    public static void setNum2(int num2) {
        K.num2 = num2;
    }

    @Autowired
    public void setNum3(int num3) {
        K.num3 = num3;
    }
}
