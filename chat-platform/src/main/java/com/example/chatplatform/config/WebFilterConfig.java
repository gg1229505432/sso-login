package com.example.chatplatform.config;

import com.sso.ssoCore.conf.Conf;
import com.sso.ssoCore.filter.SsoFilter;
import com.sso.ssoCore.utils.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yenanren
 * @date 2022/3/9 0009
 * @Description
 */
@Configuration
public class WebFilterConfig implements DisposableBean {


    @Value("${sso.server}")
    private String ssoServer;

    @Value("${sso.excluded.paths}")
    private String ssoExcludedPaths;

    @Value("${sso.redis.address}")
    private String ssoRedisAddress;

    @Value("${sso.redis.port}")
    private String ssoRedisPort;


    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {

        JedisUtil.init(ssoRedisAddress, Integer.valueOf(ssoRedisPort));

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setName("SsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new SsoFilter());

        registration.addInitParameter(Conf.SSO_SERVER, ssoServer);
        registration.addInitParameter(Conf.SSO_EXCLUDED_PATHS, ssoExcludedPaths);

        return registration;
    }

    @Override
    public void destroy() throws Exception {

    }
}
