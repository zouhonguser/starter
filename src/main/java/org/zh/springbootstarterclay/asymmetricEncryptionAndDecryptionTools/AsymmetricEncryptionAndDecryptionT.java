package org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools;


import org.zh.springbootstarterclay.tools.RSACoder;

/**
 * @author main
 * 非对称加密解密工具类
 */
public class AsymmetricEncryptionAndDecryptionT {

    //公钥
    private String publicKey;
    //私钥
    private String privateKey;

    /**
     * 构造方法
     * @param publicKey 公钥
     * @param privateKey 私钥
     */
    public AsymmetricEncryptionAndDecryptionT(String publicKey, String privateKey){
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * RSA公钥加密
     * @param string 需要加密的字符串
     * @return 密文
     */
    public String encrypt(String string)  {
        try {
            return RSACoder.encrypt(string,publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }


    /**
     * RSA私钥解密
     * @param string 密文
     * @return 明文
     */
    public String decrypt(String string) {
        try {
            return RSACoder.decrypt(string,privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

}
