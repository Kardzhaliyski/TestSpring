package values;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldInjection {
    public final String HARDCODED_VALUE = "hardcoded value";
    public final String DEFAULT_VALUE = "my default value";
    @Value(HARDCODED_VALUE)
    public String hardcodedValue;

    @Value("${value.from.file}")
    public String valueFromFile;

    @Value("${unknown.param:" + DEFAULT_VALUE + "}")
    public String defaultValue;
    @Value("${unknown.param:os.name}")
    public String defaultValue2;

    @Value("${os.name}")
    public String systemPropertyPriority;

    @Value("${commaSeparatedValues}")
    public String[] valueArr;

    @Value("#{systemProperties['os.name']}")
    public String systemPropertyBySpEL;

    @Value("#{systemProperties['some.unknown.value'] ?: '" + DEFAULT_VALUE + "'}")
    public String unknownSystemProperty;

    @Value("#{fieldInjection.valueFromFile}")
    public String valueFromBean;

    @Value("#{'${commaSeparatedValues}'}")
    public String[] valueArrSpEL;

    @Value("#{'${commaSeparatedValues}'.split(',')}")
    public List<String> valueListSpEL;
}
