package pri.xjb.ticket.security.encrypt.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类
 * 加密时：先使用aes加密  在进行base64加密
 * 解密时：先使用base64 解密 再进行aes加密
 * Created by xjb on 2019/1/22
 **/

public class AesEncryptUtils {


    private static final String KEY = "d7b85f6e214abcda";
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
//        String content = "{entName: \"高升控股股份有限公司\", bgtime: \"2019-1-13\", edtime: \"2019-2-13\"}";
//        System.out.println("加密前：" + content);
//
//        String encrypt = aesEncrypt(content, KEY);
//        encrypt.replaceAll("\r\n","");
        String encrypt= "gLlvjQvisXqadyQQiLRGRzQIItIDQdnuUPJ4jwiGqaISA9TT5j21WdEGgtJWn/V5JOP2zTUmqy+4m0IYDF6XpsROVdnOo0Ij4TMG+ZkLgDdKSQ76yiUqWkCPtQiUDm/T";
        System.out.println(encrypt.length() + ":\n加密后：\n" + encrypt);

        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }

}
