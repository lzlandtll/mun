package com.mun.test;

import com.mun.common.utils.RSADecryptUtils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Test {

    public static void main1(String[] args) throws Exception {
        // 使用RSA算法生成密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度，一般选择2048位或更多
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 将公钥和私钥转换为Base64编码的字符串，以便保存和传输
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 输出公钥和私钥
        System.out.println("公钥：");
        System.out.println(publicKeyBase64);
        System.out.println("私钥：");
        System.out.println(privateKeyBase64);



        System.out.println(RSADecryptUtils.decrypt("U2FsdGVkX199mGTmlFz3ZWSlUbveCMnt28VfoLr7f7Y="));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(RSADecryptUtils.decrypt("dHrtsFRNd1UU3D7Rz9jMUjZArW8tuLR7DLc6fQECbgqcn7oTq61iGQlCuJVHEOok6UHEzPPJxJ3nNR7z3W6aMCAhQkk1v7YOU6Xgw0JoFb8hq/B8UiVqrv5Z2koZaqA8QEbPT759HE8muW41dtTQhf+PoZ2MO50KDPeuGmjrW5zpOSG9AwvhqwF4p4Zr7m+H1YzTxfe3BKCqFipCcBEh7uduNVUcc1cBKaRCl/tfyCc7h2Yr52yEVCD0tCHTlq6DugK0ng/ARakhUHqjF9bg/llmb1FFqtvTE+2aDe+bd442F8gtttOU3VqesKgoEivjrPSNh+XIkyn9oDPbYCCBgg=="));

    }

}

