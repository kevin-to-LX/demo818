package com.hik.demo.config;

import com.hik.demo.bean.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Creat by kevin on 2018/8/10
 */
@Configuration
public class ShiroConfig {


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置登录的url
        shiroFilterFactoryBean.setLoginUrl("/u");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/success");
        //配置访问权限,白名单拦截器.
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>();
        filterChainMap.put("/login","anon");
        filterChainMap.put("/doLogin","anon");
        filterChainMap.put("/doRegister","anon");
        filterChainMap.put("/register","anon");

        //配置推出过滤器，其中的具体退出代码已实现
        filterChainMap.put("/doLogout","logout");

        //其余请求需要进行验证
        filterChainMap.put("/**","authc");

        // 未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/40X");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;

    }
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(HashedCredentialsMatcher matcher){
        MyShiroRealm shiroRealm = new MyShiroRealm();
        shiroRealm.setCredentialsMatcher(matcher);
        return shiroRealm;
    }
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(myShiroRealm(hashedCredentialsMatcher));
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }
    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-1");
        //设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        redisManager.setTimeout(0);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setPassword("123456");
        return redisManager;
    }
    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件s
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
