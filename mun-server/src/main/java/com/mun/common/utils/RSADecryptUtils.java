package com.mun.common.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/10/10
 */
public class RSADecryptUtils {

    public static String decrypt(String encryptedData) throws Exception {
        String privateKeyPEM = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCib/6YRSkSdrAHCZXAyIN511OtH1HdPJkMuCqJvlhGP7+ZrKTVKe5pbrJfzIG1d7n1GOHcV9PzuWE468E+Enl43ogHF12nNzNPFByb+X5jqfmXWv0UQYgbURwMiSiw3ALre+Pn8HEkIVg6xnPEbyLoETl3K159Nz/XOTHyFzbyfM3ZFebApROVxubastcJ00MIcAmOawDfnQniOR1o8clzYiWNzKOeyW2TCLszJlX2VB74Agg/8Jtb7JWAxun6/JeE36fr/rfk6FfjH+6BaL/QflFeWEaLe1462UgV18pMJEaVv35tp71l03qublAV7cmQ0qMZc0byzRoKtP7FVKeHAgMBAAECggEAKBNrg9PkWzrPGR1jRgnybhgS++5zOxsdQ4cYb513l/VYZyiyDilnObnm5j3FbqkYRwEZkExW7ZW6B9oadh8Guyxj8SPxpjS3QvZaTJ0UcrBX9OCs+ddlJiAvxGP+lwvg6IpLY0juERTs+KtHmpo0yX6eZ+laSZhc9tOUn/7cPpzoemIyki15TAV2mm8yvvAb1NL8RU2Va68ei7yaTZHBPYliUYDPRluEsjspxTvfKONTEGE7mPoRXLx9neFK8YZt2Zw1fNcmhWVvBj6//QxUv/jvUtDCzjEFX+pf8QVDqKjx26jkyJaKavq8p77aCfCul0nmhKxI6jx42Ps7ozUmYQKBgQDpomBrVkPgf4nCn9rdNPRprS9oiLRPVOxhDwuenQFEl5eXUM4awLNfrbLJA7z/5qWOxN1QnI/pfdBHnaenHh6yZlQxXxAyVr6NNjRS3YVQJPOsDsML+0e5eopWvUpCGjz2u08Vq/+oynb2XjyhEmiYo+wrwPKmDWXrLZgVSeyDFwKBgQCx/NBJpbBCyP6bqHUAnyLq8p3Zz1SVDetZD8yM9thsrWQu6TSLMTovDboy93LEKobO1sEpSFAcziM7wLgM2CB7Tu3EZkIMtgPZiBzcKXLWNs/EVMpipxEmnNC12x9XTfcrDGEryu3vP8g/r7iQ/4TdtXZDLvnp/Nxwb5ShLsOFEQKBgB92yLlr4rRHOMxHLvh/ezMIKcxQLMhKTZM8rXns9xC/waCVN0yPCxklkWBU6o0kCMPTa2LB0vfFA9nBN6Tf/45TDZ+gh9PfrsXhuOY4Aw4qC87uJgJlEuG9yQepcmi4k+E+lIX8pe+bZK4FPvEKQ5/a46F42LTYNROZDlQ/omR9AoGAVexsSQF5w+PwNgju4uvwGWgimAGlHYfos8gSwYJxHJXgJV1jhkavhgOkffJDLzwwdC3sdhd4xU8mZzQ0Jc84bw69bfDHtgcweJVjCU3hKFSxnGAqi9GHUBKoFwK/cJAs4FVLm66NitBJxaT4/ta7t6RggTJuZ20wpf7Xy7Adp2ECgYEAiIrf2XZigykdkrUt3lB5d1An9RHks931G/CXlRl1HLald5LKzeHuBFbtaPwifSo54b4YSCYkEgPJmshwNFH5XVbD/0BCQoctDwBHg/nKBOyCzRMcFaLE4/pPe5+3LkHko98GGJ/cF+VtkLWIN2tL1secqrx9uigKwtKkYu+W7mo=";

        byte[] encodedPrivateKey = Base64.decodeBase64(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encryptedData));

        return new String(decryptedBytes);
    }

    public static void main() throws NoSuchAlgorithmException {
        generatorKey();
    }
    public static void generatorKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 设置密钥长度，一般选择2048位或更多
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 将公钥和私钥转换为Base64编码的字符串，以便保存和传输
        String publicKeyBase64 = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 输出公钥和私钥
        System.out.println("公钥：");
        System.out.println(publicKeyBase64);
        System.out.println("私钥：");
        System.out.println(privateKeyBase64);
    }
}

