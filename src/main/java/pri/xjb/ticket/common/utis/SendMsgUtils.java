package pri.xjb.ticket.common.utis;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: xjb
 * @date: 2019/8/9
 * @description:
 **/
@Component
public class SendMsgUtils {

    @Value("${sendMsg.key}")
    private String key;
    @Value("${sendMsg.uid}")
    private String uid;

    /**
     * @param phoneNum 目的手机号
     * @param code     验证码
     * @return
     */
    public String sendMsg(String phoneNum, String code) throws IOException {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", uid),
                new NameValuePair("Key", key),
                new NameValuePair("smsMob", phoneNum),
                new NameValuePair("smsText", "注册验证码：" + code)};
        post.setRequestBody(data);

        client.executeMethod(post);
//        Header[] headers = post.getResponseHeaders();
//        int statusCode = post.getStatusCode();
////        System.out.println("statusCode:" + statusCode);
////        for (Header h : headers) {
////            System.out.println(h.toString());
////        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
//        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        String msg;
        switch (result) {
            case "-1":
                msg = "没有该用户账户";
                break;
            case "-2":
                msg = "接口密钥不正确";
                break;
            case "-21":
                msg = "MD5接口密钥加密不正确";
                break;
            case "-3":
                msg = "短信数量不足";
                break;
            case "-11":
                msg = "该用户被禁用";
                break;
            case "-14":
                msg = "短信内容出现非法字符";
                break;
            case "-4":
                msg = "手机号格式不正确";
                break;
            case "-41":
                msg = "手机号码为空";
                break;
            case "-42":
                msg = "短信内容为空";
                break;
            case "-51":
                msg = "短信签名格式不正确";
                break;
            case "-52":
                msg = "短信签名太长";
                break;
            case "-6":
                msg = "IP限制";
                break;
            default:
                msg = "发送成功";
                break;
        }
        return msg;
    }


    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", "肖既滨x"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", "16619923173"),
                new NameValuePair("smsText", "验证码：8888")};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态


        post.releaseConnection();

    }
}
