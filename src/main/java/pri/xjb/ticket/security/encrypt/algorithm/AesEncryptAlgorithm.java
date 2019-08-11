package pri.xjb.ticket.security.encrypt.algorithm;


import pri.xjb.ticket.security.encrypt.utils.AesEncryptUtils;

/**
 * aes加解密 实现类
 * Created by xjb on 2019/1/22
 **/
public class AesEncryptAlgorithm implements EncryptAlgorithm {

    @Override
    public String encrypt(String content, String encryptKey) throws Exception {
        return AesEncryptUtils.aesEncrypt(content, encryptKey);
    }

    @Override
    public String decrypt(String encryptStr, String decryptKey) throws Exception {
        return AesEncryptUtils.aesDecrypt(encryptStr, decryptKey);
    }

}
