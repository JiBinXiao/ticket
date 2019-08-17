package pri.xjb.ticket.security.encrypt.core;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import pri.xjb.ticket.common.utis.RequestParamCheckUtils;
import pri.xjb.ticket.security.encrypt.algorithm.AesEncryptAlgorithm;
import pri.xjb.ticket.security.encrypt.algorithm.EncryptAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 过滤器
 * 1.据加解密
 * 2.过滤非法参数
 * 数据加解密过滤器
 */
public class EncryptionFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionFilter.class);


    private EncryptionConfig encryptionConfig;

    private EncryptAlgorithm encryptAlgorithm = new AesEncryptAlgorithm();

    public EncryptionFilter() {
        this.encryptionConfig = new EncryptionConfig();
    }

    public EncryptionFilter(EncryptionConfig config) {
        this.encryptionConfig = config;
    }

    public EncryptionFilter(EncryptionConfig config, EncryptAlgorithm encryptAlgorithm) {
        this.encryptionConfig = config;
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public EncryptionFilter(String key, List<String> responseEncryptUriList, List<String> requestDecyptUriList,
                            String responseCharset, boolean debug) {
        this.encryptionConfig = new EncryptionConfig(key, responseEncryptUriList, requestDecyptUriList, responseCharset, debug);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        //普通接口 检查参数
        // 获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
//		if(StringUtils.isNotEmpty(paramData) && !encryptionConfig.getNoParameterCheckList().contains(uri)){
//			//2.过滤参数
//			if(paramData.startsWith("{") && paramData.endsWith("}")){
//				Map<Object, Object> map = (Map<Object, Object>) JSON.parse(paramData);
//				if(map!=null){
//					for (Map.Entry<Object, Object> entry : map.entrySet()) {
//						//杜绝非法参数
//						if(entry.getValue()==null)
//							continue;
//						String paramValidateStr =  RequestParamCheckUtils.paramValidate(entry.getValue().toString());
//						if ( StringUtils.isNotEmpty(paramValidateStr)) {
//                            System.out.println("3:"+paramValidateStr);
//							request.setAttribute("errorMessage", entry.getKey() + " 参数含非法字符：" + paramValidateStr);
//							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/invalidParameter");
//							requestDispatcher.forward(request, response);
//							return;
//						}
//
//						//防止sql注入
//						String sqlValidateStr =  RequestParamCheckUtils.sqlValidate(entry.getValue().toString());
//						if ( StringUtils.isNotEmpty(sqlValidateStr)) {
//                            System.out.println("4:"+sqlValidateStr);
//							request.setAttribute("errorMessage", entry.getKey() + " 参数含非法SQL字符：" + sqlValidateStr);
//							RequestDispatcher requestDispatcher = request.getRequestDispatcher("/invalidParameter");
//							requestDispatcher.forward(request, response);
//							return;
//						}
//
//					}
//				}
//			}
//		}

        logger.debug("RequestURI: {}", uri);


        // 调试模式不加解密
        if (encryptionConfig.isDebug()
                || "/ticketApi/doc.html".equals(uri)
                || uri.startsWith("/ticketApi/webjars")
                || uri.startsWith("/ticketApi/v2")
                || uri.startsWith("/ticketApi/swagger-resources")) {
            chain.doFilter(req, resp);
            return;
        }

        boolean decryptionStatus = true;
        boolean encryptionStatus = true;
        // 没有配置具体加解密的URI默认全部都开启加解密
        if (encryptionConfig.getRequestDecyptUriList().size() == 0
                && encryptionConfig.getResponseEncryptUriList().size() == 0) {
            decryptionStatus = true;
            encryptionStatus = true;
        } else {
            //是否需要解密
            decryptionStatus = encryptionConfig.getRequestDecyptUriList().contains(uri);
            //是否需要加密
            encryptionStatus = encryptionConfig.getResponseEncryptUriList().contains(uri);
        }


        // 没有加解密操作
        if (!decryptionStatus && !encryptionStatus) {
            chain.doFilter(req, resp);
            return;
        }


        EncryptionResponseWrapper responseWrapper = null;
        EncryptionReqestWrapper reqestWrapper = null;
        // 配置了需要解密才处理
        if (decryptionStatus) {
            reqestWrapper = new EncryptionReqestWrapper(req);
            //得到请求参数
            String requestData = reqestWrapper.getRequestData();
            logger.debug("RequestData: {}", requestData);
            try {
                //1.解密参数
                //解密参数
                String decyptRequestData = encryptAlgorithm.decrypt(requestData, encryptionConfig.getKey());
                logger.debug("DecyptRequestData: {}", decyptRequestData);
//				//2.过滤参数
//				if(decyptRequestData.startsWith("{") && decyptRequestData.endsWith("}")){
//					Map<Object, Object> map = (Map<Object, Object>) JSON.parse(decyptRequestData);
//					if(map!=null){
//						for (Map.Entry<Object, Object> entry : map.entrySet()) {
//							//杜绝非法参数
//							if(entry.getValue()==null)
//								continue;
//							String paramValidateStr =  RequestParamCheckUtils.paramValidate(entry.getValue().toString());
//							if (StringUtils.isNotEmpty(paramValidateStr)) {
//
//								request.setAttribute("errorMessage", entry.getKey() + " 参数含非法字符：" + paramValidateStr);
//								RequestDispatcher requestDispatcher = request.getRequestDispatcher("/invalidParameter");
//								requestDispatcher.forward(request, response);
//								return;
//							}
//
//							//防止sql注入
//							String sqlValidateStr = RequestParamCheckUtils.sqlValidate(entry.getValue().toString());
//							if ( StringUtils.isNotEmpty(sqlValidateStr)) {
//								String paramKey=entry.getKey().toString();
//								if(paramKey.equals("password"))
//									paramKey="密码";
//							    request.setAttribute("errorMessage", paramKey + " 参数含非法SQL字符：" + sqlValidateStr);
//								RequestDispatcher requestDispatcher = request.getRequestDispatcher("/invalidParameter");
//								requestDispatcher.forward(request, response);
//								return;
//							}
//
//						}
//					}
//				}


                reqestWrapper.setRequestData(decyptRequestData);
            } catch (Exception e) {
                logger.error("请求数据解密失败", e);
                throw new RuntimeException(e);
            }
        }

        //加密
        if (encryptionStatus) {
            responseWrapper = new EncryptionResponseWrapper(resp);
        }

        // 同时需要加  解密
        if (encryptionStatus && decryptionStatus) {
            chain.doFilter(reqestWrapper, responseWrapper);
        } else if (encryptionStatus) { //只需要响应加密
            chain.doFilter(req, responseWrapper);
        } else if (decryptionStatus) { //只需要请求解密
            chain.doFilter(reqestWrapper, resp);
        }

        // 配置了需要加密才处理
        if (encryptionStatus) {
            //
            String responeData = responseWrapper.getResponseData();
            if (responeData != null) {
                logger.debug("ResponeData: {}", responeData);
                ServletOutputStream out = null;
                try {
                    //加密
                    responeData = encryptAlgorithm.encrypt(responeData, encryptionConfig.getKey());
                    responeData = responeData.replaceAll("\r\n", "");
                    logger.debug("EncryptResponeData: {}", responeData);
                    response.setCharacterEncoding(encryptionConfig.getResponseCharset());
                    out = response.getOutputStream();
                    response.setContentLength(responeData.length());
                    out.write(responeData.getBytes(encryptionConfig.getResponseCharset()));

                } catch (Exception e) {
                    logger.error("响应数据加密失败", e);
                    throw new RuntimeException(e);
                } finally {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                }
            }


        }

    }

    @Override
    public void destroy() {

    }


}


