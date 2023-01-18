package classes;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class N implements InitializingBean, DisposableBean {

    public N() {
        System.out.println("Constructor used!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AfterPropertiesSet used!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroy used!");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct used!");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy used!");
    }
}
