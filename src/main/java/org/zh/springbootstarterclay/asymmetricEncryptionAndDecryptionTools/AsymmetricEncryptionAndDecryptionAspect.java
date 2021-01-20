package org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.zh.springbootstarterclay.annotation.NeedAsymmetricEncryptionAndDecryption;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DependsOn("asymmetricEncryptionAndDecryptionT")
@Aspect
public class AsymmetricEncryptionAndDecryptionAspect {

    @Pointcut("execution (* org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools.AsymmetricEncryptionAndDecryption.*(..))")
    public void encryptObjectPointCut(){

    }

    @Autowired
    AsymmetricEncryptionAndDecryptionT asymmetricEncryptionAndDecryptionT;

    //加密
    private final String ENCRYPT_OBJECT = "encryptObject";
    //解密
    private final String DECRYPT_OBJECT = "decryptObject";

    @Around("encryptObjectPointCut()")
    public <T> Object encryptObjectPointCutAround(ProceedingJoinPoint joinPoint)  {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //加密方法
        Object[] args = joinPoint.getArgs();
        if(method.getName().equals(ENCRYPT_OBJECT)) {
            Object arg = args[0];
            if( arg instanceof String ) {
                String encrypt = asymmetricEncryptionAndDecryptionT.encrypt(arg.toString());
                args[0] = encrypt;
            }else{
                Class<T> aClass = (Class<T>) arg.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                for(Field field : declaredFields) {
                    //judge is have NeedAsymmetricEncryptionAndDecryption annotation
                    if(field.isAnnotationPresent(NeedAsymmetricEncryptionAndDecryption.class)) {
                        String fieldName = field.getName();
                        String getMethodName = splicingGetMethodName(fieldName);
                        String setMethodName = splicingSetMethodName(fieldName);
                        try {
                            //Get parameters
                            Method method1 = aClass.getMethod(getMethodName, null);
                            method1.setAccessible(true);
                            String fieldValue = method1.invoke(arg,null).toString();
                            //Encryption of parameters
                            aClass.getMethod(setMethodName, String.class).invoke(arg,asymmetricEncryptionAndDecryptionT.encrypt(fieldValue));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //解密方法
        if(method.getName().equals(DECRYPT_OBJECT)) {
            Object arg = args[0];
            if( arg instanceof String ) {
                String encrypt = asymmetricEncryptionAndDecryptionT.decrypt(arg.toString());
                args[0] = encrypt;
            }else{
                Class<T> aClass = (Class<T>) arg.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                for(Field field : declaredFields) {
                    //judge is have NeedAsymmetricEncryptionAndDecryption annotation
                    if(field.isAnnotationPresent(NeedAsymmetricEncryptionAndDecryption.class)) {
                        String fieldName = field.getName();
                        String getMethodName = splicingGetMethodName(fieldName);
                        String setMethodName = splicingSetMethodName(fieldName);
                        try {
                            //Get parameters
                            String fieldValue = aClass.getMethod(getMethodName).invoke(arg,null).toString();
                            //Decryption of parameters
                            aClass.getMethod(setMethodName, String.class).invoke(arg,asymmetricEncryptionAndDecryptionT.decrypt(fieldValue));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        try {
            return joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字段名称拼接get方法名
     * @param fieldName 字段名称
     * @return get字段方法名
     */
    private String splicingGetMethodName(String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0,1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    /**
     * 根据字段名称拼接set方法名
     * @param fieldName 字段名称
     * @return set方法名
     */
    private String splicingSetMethodName(String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("set");
        sb.append(fieldName.substring(0,1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

}
