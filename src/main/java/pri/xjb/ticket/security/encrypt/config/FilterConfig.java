package pri.xjb.ticket.security.encrypt.config;


import pri.xjb.ticket.security.encrypt.core.EncryptionConfig;
import pri.xjb.ticket.security.encrypt.core.EncryptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xjb on 2019/1/22
 **/
@Configuration
public class FilterConfig {

    @Autowired
    EncryptionConfig encryptionConfig;
    @Bean
    public FilterRegistrationBean filterRegistration() {
//        EncryptionConfig config = new EncryptionConfig();
//        config.setKey(key);
//
//        config.setDebug(debug);
//        config.setRequestDecyptUriList(Arrays.asList("/consumer/encryptStr","/consumer/save","/consumer/login","/consumer/riskevent/getNewRiskEventList"));
//        config.setResponseEncryptUriList(Arrays.asList("/consumer/encryptStr","/consumer/save","/consumer/login","/consumer/riskevent/getNewRiskEventList"));
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new EncryptionFilter(encryptionConfig));
        registration.addUrlPatterns("/*");
        registration.setName("EncryptionFilter");
        registration.setOrder(1);
        return registration;
    }

}
