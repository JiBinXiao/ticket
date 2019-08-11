package pri.xjb.ticket.security.encrypt.algorithm;

/**
 * 加解密接口
 * Created by xjb on 2019/1/22
 **/
public interface EncryptAlgorithm {

    /**
     * 加密
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public String encrypt(String content, String encryptKey) throws Exception;

    /**
     * 解密
     * @param encryptStr
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public String decrypt(String encryptStr, String decryptKey) throws Exception;

}