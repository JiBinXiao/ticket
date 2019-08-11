package pri.xjb.ticket.model;

/**
 * 访问日志实体类
 * Created by xjb on 2019/01/14
 */


import java.io.Serializable;
import java.util.Date;

public class LoggerEntity implements Serializable {
    //编号
    private Long id;
    //客户端请求ip
    private String clientip;
    //客户端请求路径
    private String uri;
    //终端请求方式,普通请求,ajax请求
    private String type;
    //请求方式method,post,get等
    private String method;
    //请求的类及方法
    private String classmethod;
    //请求参数内容,json
    private String paramdata;
    //请求接口唯一session标识
    private String sessionid;
    //访问用户
    private String createby;
    //请求时间
    private Date createtime;
    //接口返回时间
    private Date returntime;
    //接口返回数据json
    private String returndata;
    //请求时httpStatusCode代码，如：200,400,404等
    private String httpstatuscode;
    //请求耗时毫秒单位
    private int  timeconsuming;
    //异常描述
    private String exceptionmessage;
    //请求开始时间
    private long starttime;
    //请求结束时间
    private long endtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassmethod() {
        return classmethod;
    }

    public void setClassmethod(String classmethod) {
        this.classmethod = classmethod;
    }

    public String getParamdata() {
        return paramdata;
    }

    public void setParamdata(String paramdata) {
        this.paramdata = paramdata;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public String getReturndata() {
        return returndata;
    }

    public void setReturndata(String returndata) {
        this.returndata = returndata;
    }

    public String getHttpstatuscode() {
        return httpstatuscode;
    }

    public void setHttpstatuscode(String httpstatuscode) {
        this.httpstatuscode = httpstatuscode;
    }

    public int getTimeconsuming() {
        return timeconsuming;
    }

    public void setTimeconsuming(int timeconsuming) {
        this.timeconsuming = timeconsuming;
    }

    public String getExceptionmessage() {
        return exceptionmessage;
    }

    public void setExceptionmessage(String exceptionmessage) {
        this.exceptionmessage = exceptionmessage;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }
}
