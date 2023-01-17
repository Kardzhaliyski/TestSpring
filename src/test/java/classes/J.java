package classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class J {
    @Autowired
    @Qualifier("maxNum2")
    public long maxLong;
    @Autowired
    @Qualifier("maxNum2")
    public int maxInt;
}
