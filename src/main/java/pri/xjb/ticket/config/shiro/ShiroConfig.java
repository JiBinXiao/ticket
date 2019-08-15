package pri.xjb.ticket.config.shiro;



import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pri.xjb.ticket.config.shiro.shiro.*;
import pri.xjb.ticket.config.shiro.shiro.bean.UserRealm;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 * Created by xjb on 2018/11/7
 **/
@Configuration
public class ShiroConfig {
    @Value("${shiro.shiroRealm.authenticationCachingEnabled}")
    private boolean authenticationCachingEnabled=false;

    @Value("${shiro.shiroRealm.authenticationCacheName}")
    private String authenticationCacheName="authenticationCache";

    @Value("${shiro.shiroRealm.authorizationCachingEnabled}")
    private boolean authorizationCachingEnabled=false;

    @Value("${shiro.shiroRealm.authorizationCacheName}")
    private String authorizationCacheName="authorizationCache";

    @Value("${shiro.redisCacheManager.principalIdFieldName}")
    private String principalIdFieldName="username";

    @Value("${shiro.redisCacheManager.expire}")
    private int redisCacheManagerExpire=1800;

    @Value("${shiro.redisSessionDAO.expire}")
    private int redisSessionDAOExpire=600;

    @Value("${shiro.sessionManager.globalSessionTimeout}")
    private int globalSessionTimeout=600;

    @Value("${shiro.sessionManager.deleteInvalidSessions}")
    private boolean deleteInvalidSessions=true;

    @Value("${shiro.sessionManager.sessionValidationSchedulerEnabled}")
    private boolean sessionValidationSchedulerEnabled=true;

    @Value("${shiro.sessionManager.sessionValidationInterval}")
    private int sessionValidationInterval=300;

    @Value("${shiro.kickoutSessionControlFilter.kickoutAfter}")
    private boolean kickoutAfter=false;

    @Value("${shiro.kickoutSessionControlFilter.maxSession}")
    private int maxSession=10;


    /**
     *  ShiroFilterFactoryBean 处理拦截资源文件问题。
     *  注意：初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *  Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //必须设置 SecurityManager,Shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //自定义拦截器限制并发人数
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数
        filtersMap.put("kickout", kickoutSessionControlFilter());
        //统计登录人数
        shiroFilterFactoryBean.setFilters(filtersMap);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器：
         *      anon：无需认证（登录）可以访问
         *      authc：必须认证才可访问
         *      user：如果使用rememberme的功能可以直接访问
         *      perms：该资源必须得到资源权限才可访问
         *      role：该资源必须获得角色权限才可访问
         *
         */
        // 配置访问权限 必须是LinkedHashMap，因为它必须保证有序
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 --> : 这是一个坑，一不小心代码就不好使了
        Map<String,String> filterChainDefinitionMap =new LinkedHashMap<>();

        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置不会被拦截的链接 顺序判断
        //配置不登录可以访问的资源，anon 表示资源都可以匿名访问
//        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/webjars/bycdao-ui/**","anon");

        filterChainDefinitionMap.put("/doc.html","anon");
        filterChainDefinitionMap.put("/image/recognizeTicket","anon");
        filterChainDefinitionMap.put("/v2/api-docs","anon");
        filterChainDefinitionMap.put("/swagger-resources","anon");
        filterChainDefinitionMap.put("/user/register","anon");
        filterChainDefinitionMap.put("/user/login","kickout,anon");
        filterChainDefinitionMap.put("/checkLogin","anon");
        filterChainDefinitionMap.put("/invalidParameter","anon");
        filterChainDefinitionMap.put("/ticketCategory/queryAll","anon");
        filterChainDefinitionMap.put("/ticket/queryAll","anon");
        filterChainDefinitionMap.put("/ticket/getAisleByPrice","anon");
        filterChainDefinitionMap.put("/ticket/getRowNumByPriceAndAisle","anon");
        //其他资源都需要认证  authc 表示需要认证才能进行访问 user表示配置记住我或认证通过可以访问的地址
        //表示 访问/**下的资源 首先要通过 kickout 后面的filter，然后再通过user后面对应的filter才可以访问。
        filterChainDefinitionMap.put("/**","kickout,authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/noAuth");
        //设置未授权跳转地方
        //shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(shiroRealm());
//        //配置记住我 参考博客：
//        securityManager.setRememberMeManager(rememberMeManager());


        //配置自定义session管理，使用ehcache 或redis

        //配置redis缓存
        securityManager.setCacheManager(cacheManager());
        //配置自定义session管理，使用redis
        securityManager.setSessionManager(sessionManager());


        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public UserRealm shiroRealm(){
        UserRealm shiroRealm = new UserRealm();
        shiroRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        shiroRealm.setAuthenticationCachingEnabled(authenticationCachingEnabled);
        //缓存AuthenticationInfo信息的缓存名称
        shiroRealm.setAuthenticationCacheName(authenticationCacheName);



        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        shiroRealm.setAuthorizationCachingEnabled(authorizationCachingEnabled);
        //缓存AuthorizationInfo信息的缓存名称
        shiroRealm.setAuthorizationCacheName(authorizationCacheName);
        //配置自定义密码比较器
        shiroRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());

        return shiroRealm;
    }



    /**
     * 开启shiro 注解模式
     * 可以在controller中的方法前加上注解
     * 如 @RequiresPermissions("userInfo:add")
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redis中针对不同用户缓存
        redisCacheManager.setPrincipalIdFieldName(principalIdFieldName);
        //用户权限信息缓存时间  单位秒
        redisCacheManager.setExpire(redisCacheManagerExpire);
        return redisCacheManager;
    }

    /**
     * 让某个实例的某个方法的返回值注入为Bean的实例
     * Spring静态注入
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }

    /**
     * 配置session监听
     * @return
     */
    @Bean(name ="sessionListener")
    public ShiroSessionListener sessionListener(){
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }

    /**
     * 配置会话ID生成器
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        return redisManager;
    }

    @Bean(name ="sessionFactory")
    public ShiroSessionFactory sessionFactory(){
        ShiroSessionFactory sessionFactory = new ShiroSessionFactory();
        return sessionFactory;
    }

    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean(name="sessionDAO")
    public SessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //session在redis中的保存时间,最好大于session会话超时时间  秒
        redisSessionDAO.setExpire(redisSessionDAOExpire);
        return redisSessionDAO;
    }

    /**
     * 配置保存sessionId的cookie
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
     * 默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid
     * @return
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        //这个参数是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    @Bean(name ="sessionManager")
    public SessionManager sessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        //配置监听
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setSessionFactory(sessionFactory());

        //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
        sessionManager.setGlobalSessionTimeout(globalSessionTimeout*1000);
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(deleteInvalidSessions);
        //是否开启定时调度器进行检测过期session 默认为true
        sessionManager.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        sessionManager.setSessionValidationInterval(sessionValidationInterval*1000);
//        //取消url 后面的 JSESSIONID
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;

    }

    /**
     * 并发登录控制
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setRedisManager(redisManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
        kickoutSessionControlFilter.setKickoutAfter(kickoutAfter);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(maxSession);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionControlFilter;
    }


    /**
     * 配置密码比较器
     * @return
     */
    @Bean
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(){
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setRedisManager(redisManager());

        //如果密码加密,可以打开下面配置
        //加密算法的名称
        //retryLimitHashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //配置加密的次数
        //retryLimitHashedCredentialsMatcher.setHashIterations(1024);
        //是否存储为16进制
        //retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return retryLimitHashedCredentialsMatcher;
    }



}
