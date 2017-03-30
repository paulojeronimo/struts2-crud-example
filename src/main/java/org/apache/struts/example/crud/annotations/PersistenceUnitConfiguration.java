package org.apache.struts.example.crud.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface PersistenceUnitConfiguration {
    String persistenceUnitName();

    String entityManagerField();
}
