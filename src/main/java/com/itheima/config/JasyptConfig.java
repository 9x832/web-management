package com.itheima.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    /**
     * 配置Jasypt加密器
     * 使用时：
     * 1. 运行 encrypt 方法生成加密后的值
     * 2. 在配置文件中使用格式 ENC(加密后的值)
     * 3. 启动应用时通过环境变量或命令行参数传入加密密钥：
     *    -Djasypt.encryptor.password=密钥
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        // 设置加密算法
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        // 设置密钥，优先从环境变量获取，环境变量不存在则使用默认值
        config.setPassword(System.getProperty("jasypt.encryptor.password", "defaultSecretKey"));
        // 设置密钥获取次数
        config.setKeyObtentionIterations("1000");
        // 设置加密池大小
        config.setPoolSize("1");
        // 设置其他参数
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        
        encryptor.setConfig(config);
        return encryptor;
    }
    
    /**
     * 测试加密（仅开发时使用，不要在生产环境使用）
     */
    public static void main(String[] args) {
        // 创建加密器
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setPassword("mySecretKey"); // 设置加密密钥
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        
        // 要加密的内容（如数据库密码）
        String originalPassword = "123456";
        // 加密
        String encryptedPassword = encryptor.encrypt(originalPassword);
        // 解密
        String decryptedPassword = encryptor.decrypt(encryptedPassword);
        
        System.out.println("原始密码: " + originalPassword);
        System.out.println("加密密码: " + encryptedPassword);
        System.out.println("解密密码: " + decryptedPassword);
        System.out.println("配置文件中使用: ENC(" + encryptedPassword + ")");
    }
} 