package com.example.unifiedauthentication.config;

import com.example.unifiedauthentication.utils.JedisUtil;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
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

    private String address = "127.0.0.1";
    private int port = 6379;

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
