package org.zh.springbootstarterclay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools.AsymmetricEncryptionAndDecryption;
import org.zh.springbootstarterclay.asymmetricEncryptionAndDecryptionTools.AsymmetricEncryptionAndDecryptionT;
import org.zh.springbootstarterclay.parameterConfiguration.AsymmetricEncryptionAndDecryptionProperties;

@Configuration
@EnableConfigurationProperties(AsymmetricEncryptionAndDecryptionProperties.class)
@ConditionalOnProperty(
                prefix = "asymmetric-encryption-and-decryption",
                name = "isOpen",
                havingValue = "true"
         )
public class AsymmetricEncryptionAndDecryptionToolsConfig {

    @Autowired
    AsymmetricEncryptionAndDecryptionProperties asymmetricEncryptionAndDecryptionProperties;

    @Bean
    public AsymmetricEncryptionAndDecryptionT asymmetricEncryptionAndDecryptionT() {
        return new AsymmetricEncryptionAndDecryptionT(
                asymmetricEncryptionAndDecryptionProperties.getPublicKey(),
                asymmetricEncryptionAndDecryptionProperties.getPrivateKey()
        );
    }

    @Bean
    public AsymmetricEncryptionAndDecryption asymmetricEncryptionAndDecryption(){
        return new AsymmetricEncryptionAndDecryption();
    }


}
