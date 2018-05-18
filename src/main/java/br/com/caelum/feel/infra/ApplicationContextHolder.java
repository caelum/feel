package br.com.caelum.feel.infra;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationContextHolder implements ApplicationContextAware{
     
    private static ApplicationContext instance;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContextHolder.instance = applicationContext;
    }
 
    public static ApplicationContext getInstance(){
        return instance;
    }
    
	public static void autorwire(Object entity) {
		Assert.notNull(instance,"O método setApplicationContext precisa ser chamado antes...");
		instance.getAutowireCapableBeanFactory().autowireBean(entity);

	}    
 
 
}