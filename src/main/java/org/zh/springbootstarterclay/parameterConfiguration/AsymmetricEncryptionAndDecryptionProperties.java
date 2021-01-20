package org.zh.springbootstarterclay.parameterConfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author main
 * 非对称加密解密配置实体类
 */
@Data
@ConfigurationProperties(prefix = "asymmetric-encryption-and-decryption")
public class AsymmetricEncryptionAndDecryptionProperties {

    private String publicKey;
    private String privateKey;


}
