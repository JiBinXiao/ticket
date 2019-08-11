package pri.xjb.ticket.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * json标准对象
 * Created by xjb on 2018/9/25
 **/
@ApiModel("统一返回结果实体类")
public class RtnResult<T> implements Serializable {


    private static final int RESULT_CODE_STATUS_SUCCESS = 200;//返回成功
    private static final int RESULT_CODE_STATUS_FAIL = 400;//返回失败
    private static final int RESULT_CODE_STATUS_NOAUTH = 401;//没有权限
    private static final int RESULT_CODE_BAD_REQUEST = 412;  // bad request 参数有误

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private int status;
    /**
     * 标识
     */
    @ApiModelProperty(value = "请求标志")
    private boolean success = false;

    /**
     * 信息
     */
    @ApiModelProperty(value = "信息")
    private String msg;

    /**
     * 日期 long  不知怎么赋值
     */
    @ApiModelProperty(value = "查询时间戳")
    private String timestamp;


    /**
     * 结果对象
     */
    @ApiModelProperty(value = "结果对象")
    private T data;


    /**
     * 成功信息
     *
     * @param msg
     * @param data
     * @return
     */
    public static RtnResult successInfo(String msg, Object data) {
        RtnResult rtnResult = new RtnResult();
        rtnResult.setTimestamp(String.valueOf(new Date().getTime()));
        rtnResult.setStatus(RESULT_CODE_STATUS_SUCCESS);
        rtnResult.setSuccess(true);
        rtnResult.setMsg(msg);
        rtnResult.setData(data);
        return rtnResult;
    }

    /**
     * 成功信息
     *
     * @param data
     * @return
     */
    public static RtnResult successInfo(Object data) {
        RtnResult rtnResult = new RtnResult<>();
        rtnResult.setTimestamp(String.valueOf(new Date().getTime()));
        rtnResult.setStatus(RESULT_CODE_STATUS_SUCCESS);
        rtnResult.setSuccess(true);
        rtnResult.setMsg("成功");
        rtnResult.setData(data);
        return rtnResult;
    }

    /**
     * 错误信息
     *
     * @param msg
     * @param data
     * @return
     */
    public static RtnResult errorInfo(String msg, Object data) {
        RtnResult rtnResult = new RtnResult();
        rtnResult.setStatus(RESULT_CODE_STATUS_FAIL);
        rtnResult.setSuccess(false);
        rtnResult.setMsg(msg);
        rtnResult.setData(data);
        return rtnResult;
    }

    /**
     * 未登录信息提示
     *
     * @param msg
     * @return
     */
    public static RtnResult noAuthInfo(String msg) {
        RtnResult rtnResult = new RtnResult();
        rtnResult.setStatus(RESULT_CODE_STATUS_NOAUTH);
        rtnResult.setSuccess(false);
        rtnResult.setMsg(msg);
        return rtnResult;
    }

    /**
     * 错误请求   如：参数请求有误
     *
     * @param msg
     * @return
     */
    public static RtnResult badRequestInfo(String msg) {
        RtnResult rtnResult = new RtnResult();
        rtnResult.setStatus(RESULT_CODE_BAD_REQUEST);
        rtnResult.setSuccess(false);
        rtnResult.setMsg(msg);
        return rtnResult;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
