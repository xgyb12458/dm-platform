//package com.damon.oauth.starter.configure;
//
//import com.damon.oauth.shared.enums.KickOutStrategy;
//import com.damon.oauth.starter.filter.KickOutSessionFilter;
//import com.damon.oauth.starter.security.MyShiroRealm;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Slf4j
////@Configuration
//public class WebShiroConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
////    @Autowired(required = false)
////    private PermissionService permissionService;
//
//    private final static String KICK_OUT = "KICK_OUT";
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter() {
//        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
//        shiroFilterFactory.setSecurityManager(securityManager());
//        shiroFilterFactory.setLoginUrl("/login");
//        shiroFilterFactory.setSuccessUrl("/index");
//        shiroFilterFactory.setUnauthorizedUrl("/403");
//
//        //限制同一帐号同时在线的个数。
//        Map<String, Filter> kickOutfilters = new LinkedHashMap<>();
//        kickOutfilters.put(KICK_OUT, kickOutSessionFilter());
//
//        /*
//        Filter Chain定义说明：
//            1、一个URL可以配置多个Filter，使用逗号分隔
//            2、当设置多个过滤器时，全部验证通过，才视为通过
//            3、部分过滤器可指定参数，如perms，roles
//
//            authc：该过滤器下的页面必须登录后才能访问
//            anon: 可以理解为不拦截
//            user: 登录了就不拦截
//            roles["admin"]: 用户拥有admin角色
//            perms["permission1"]: 用户拥有permission1权限
//            filter: 顺序按照定义顺序匹配，匹配到就验证，验证完毕结束。
//            url: 匹配通配符支持：? * **,分别表示匹配1个，匹配0-n个（不含子路径），匹配下级所有路径
//        */
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//
//        // 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/add", "perms[权限添加]");
//
//        // 过滤链定义，从上向下顺序执行，一般将 /**放在最下边；
//        // authc: 所有url都必须认证通过才可以访问;
//        // anon: 所有url都都可以匿名访问
//        filterChainDefinitionMap.put("/**", "authc");
//
////        List<SysPermissionInit> list = permissionService.selectAll();
////        for (PermissionInit permissionInit : list) {
////            filterChainDefinitionMap.put(permissionInit.getUrl(), permissionInit.getPermissionInit());
////        }
//
//        shiroFilterFactory.setFilters(kickOutfilters);
//        shiroFilterFactory.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        log.info("Shiro拦截器工厂类注入成功");
//
//        return shiroFilterFactory;
//    }
//
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(shiroRealm());
//        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(cacheManager());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
//        // 注入记住我管理器;
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }
//
//    @Bean
//    public MyShiroRealm shiroRealm() {
//        return new MyShiroRealm();
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager());
//        return advisor;
//    }
//
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        return sessionManager;
//    }
//
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }
//
//
//    private RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }
//
//    private CookieRememberMeManager rememberMeManager(){
//        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
//        rememberMeManager.setCookie(rememberMeCookie());
//        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        rememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
//        return rememberMeManager;
//    }
//
//
//    private RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(port);
//        //配置缓存过期时间
////        redisManager.setExpire(1800);
//        redisManager.setTimeout(timeout);
//        return redisManager;
//    }
//
//    private SimpleCookie rememberMeCookie() {
//        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie remember = new SimpleCookie("rememberMe");
//        // 记住我cookie生效时间30天，单位秒
//        // 2592000
//        remember.setMaxAge(30 * 24 * 60 * 60);
//        return remember;
//    }
//
//    private KickOutSessionFilter kickOutSessionFilter(){
//        KickOutSessionFilter kickOutFilter = new KickOutSessionFilter();
//        // 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系
//        kickOutFilter.setCacheManager(cacheManager());
//        // 用于根据会话ID，获取会话进行踢出操作的；
//        kickOutFilter.setSessionManager(sessionManager());
//        // 登录踢出策略：默认是后登录的用户踢出先登录的用户
//        kickOutFilter.setStrategy(KickOutStrategy.FIFO);
//        // 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
//        kickOutFilter.setMaxSession(1);
//        // 被踢出后重定向到的地址；
//        kickOutFilter.setKickOutUrl("/kickOut");
//        return kickOutFilter;
//    }
//}
