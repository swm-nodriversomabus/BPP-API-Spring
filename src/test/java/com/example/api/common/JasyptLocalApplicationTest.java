package com.example.api.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ActiveProfiles("local")
@SpringBootTest
@ExtendWith(SpringExtension.class)
@WithMockUser(roles = "User")
class JasyptLocalApplicationTest {

    @Value("${jasypt.encryptor.password}")
    private String key;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbuserName;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private String redisPort;
    @Value("${redis.password}")
    private String redisPW;
    @Value("${jwt.secret}")
    private String jwt;

    @Test
    void jasypt(){
        System.out.println(dbuserName);
        System.out.println(dbUrl);
        System.out.println(dbPassword);
        System.out.println(bootstrapAddress);
        System.out.println(redisHost);
        System.out.println(redisPort);
        System.out.println(redisPW);
        System.out.println(jwt);
        String encryptDBName = jasyptEncrypt(dbuserName);
        String encryptDBUrl = jasyptEncrypt(dbUrl);
        String encryptDBPassword = jasyptEncrypt(dbPassword);
        String encryptBootstrap = jasyptEncrypt(bootstrapAddress);
        String encryptRedisHost = jasyptEncrypt(redisHost);
        String encryptRedisPort = jasyptEncrypt(redisPort);
        String encryptRedisPW = jasyptEncrypt(redisPW);
        String encryptJwt = jasyptEncrypt(jwt);

        System.out.println(encryptDBName);
        System.out.println(encryptDBUrl);
        System.out.println(encryptDBPassword);
        System.out.println(encryptBootstrap);
        System.out.println(encryptRedisHost);
        System.out.println(encryptRedisPort);
        System.out.println(encryptRedisPW);
        System.out.println(encryptJwt);


//
//        System.out.println(encryptDBUrl);
////        System.out.println(jasyptDecrypt(encryptDBUrl));
//        System.out.println(encryptDBName);
//        System.out.println(encryptDBPassword);
//        System.out.println(encryptBootstrap);
//
//        Assertions.assertThat(dbUrl).isEqualTo(jasyptDecrypt(encryptDBUrl));

    }

    // 암호화
    private String jasyptEncrypt(String input){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setPassword(key);
        encryptor.setIvGenerator(new RandomIvGenerator());
        return encryptor.encrypt(input);
    }

    // 복호화
    private String jasyptDecrypt(String input){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }

}