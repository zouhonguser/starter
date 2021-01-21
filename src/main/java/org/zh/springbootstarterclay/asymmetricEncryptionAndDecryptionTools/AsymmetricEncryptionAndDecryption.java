package org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools;


import org.springframework.context.annotation.DependsOn;

/**
 * @author main
 * 非对称加密解密工具类
 */
@DependsOn("AsymmetricEncryptionAndDecryptionT")
public class AsymmetricEncryptionAndDecryption <T>{


    /**
     * RSA公钥加密泛型对象
     * @param t 加密对象
     * @return 加密之后的对象
     */
    public T encryptObject(T t) {
        return t;
    }

    /**
     * RSA私钥解密泛型对象
     * @param t 解密对象
     * @return 解密之后的对象
     */
    public T decryptObject(T t) {
        return t;
    }
}
