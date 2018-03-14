package com.milind.dockersecret;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Milind on 14/03/18.
 */
@Component
public class SecretBeanProcessor implements BeanPostProcessor {

    @Autowired
    private Environment env;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {

            try {
                Field fieldarrr[] = bean.getClass().getDeclaredFields();
                for(Field field: fieldarrr){
                    if( field.isAnnotationPresent(Secret.class)){
                        String secretekey = "/run/secrets/"+field.getAnnotation(Secret.class).secret();
                        if(Files.exists(Paths.get(secretekey))){
                            field.setAccessible(true);
                            //Our Encryption strategy was to reverse the string so to decrypt we need to
                            // Reverse it back
                            field.set(bean,new StringBuilder(new String(Files.readAllBytes(
                                    Paths.get(secretekey)))).reverse().toString() );
                        } else {
                            field.setAccessible(true);
                            field.set(bean,env.getProperty(field.getAnnotation(Secret.class).propValue()));
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }

}
