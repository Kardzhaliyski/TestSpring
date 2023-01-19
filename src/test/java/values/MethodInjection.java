package values;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MethodInjection {
    public String methodInject;
    public String paramInject;
    public String paramInjectNoAutowire;

    public String constructorInject;

    public MethodInjection(@Value("${value.from.file}") String constructorInject) {
        this.constructorInject = constructorInject;
    }

    @Value("${value.from.file}")
    public void setMethodInject(String methodInject) {
        this.methodInject = methodInject;
    }

    @Autowired
    public void setParamInject(@Value("${value.from.file}") String paramInject) {
        this.paramInject = paramInject;
    }

    public void setParamInject2(@Value("${value.from.file}") String paramInject) {
        this.paramInjectNoAutowire = paramInject;
    }
}
