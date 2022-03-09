package com.example.unifiedauthentication.config;

import com.sso.ssoCore.utils.JedisUtil;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yenanren
 * @date 2022/3/7 0007
 * @Description
 */

@Configuration
//@ConfigurationProperties(prefix="redis-wangyi")
@Data
public class RedisConfig implements InitializingBean, DisposableBean {

    @Value("${sso.redis.address}")
    private String address;

    @Value("${sso.redis.port}")
    private int port;

    private int redisExpireMinute;

    @Override
    public void afterPropertiesSet() throws Exception {
//        SessionStoreRedisHelper.setRedisExpireMinite(redisExpireMinute);
        JedisUtil.init(address, port);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }
}
