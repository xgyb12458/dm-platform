package com.damon.order.domain.test;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@EnableEncryptableProperties
@SpringBootTest(classes= Deserializers.Base.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class EncryptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testEncryption() {
        String username= "byyroot";
        String password= "9w#bGqiKz6l+";

        System.out.println("生成加密后的账户：" + stringEncryptor.encrypt(username));
        System.out.println("生成加密后的密码：" + stringEncryptor.encrypt(password));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
