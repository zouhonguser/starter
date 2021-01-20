package org.zh.springbootstarterclay.annotation;


import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NeedAsymmetricEncryptionAndDecryption {

    boolean need() default true;

}
