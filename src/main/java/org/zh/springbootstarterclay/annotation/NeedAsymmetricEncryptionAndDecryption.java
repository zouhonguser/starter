package org.zh.springbootstarterclay.annotation;

import java.lang.annotation.*;

/**
 * 此注解为
 * 需要进行非对称加密解密操作
 * @author main
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NeedAsymmetricEncryptionAndDecryption {

}
