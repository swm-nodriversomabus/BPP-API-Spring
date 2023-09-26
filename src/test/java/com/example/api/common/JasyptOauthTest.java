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
@ExtendWith(SpringExtension.class)
@WithMockUser(roles = "User")
class JasyptOauthTest {

    @Value("${jasypt.encryptor.password}")
    private String key = "";
    private String client_id = "";

    private String secret = "";

    @Test
    void jasypt(){

        String encci = jasyptEncrypt(client_id);
        String encse = jasyptEncrypt(secret);
        System.out.println(encci);
        System.out.println(encse);


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