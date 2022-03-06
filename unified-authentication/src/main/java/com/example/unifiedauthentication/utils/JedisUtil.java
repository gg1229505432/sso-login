package com.example.unifiedauthentication.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class JedisUtil {
    private static String address;
    private static int port;
    private static volatile JedisPool jedisPool;
    private static ReentrantLock initLock = new ReentrantLock(false);

    public static void init(String address, int port) {
        JedisUtil.address = address;
        JedisUtil.port = port;
        getInstance();
    }

    private static Jedis getInstance() {
        if (jedisPool == null) {
            try {
                if (initLock.tryLock(2L, TimeUnit.SECONDS)) {
                    try {
                        if (jedisPool == null) {
                            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
                            config.setMaxTotal(200);
                            config.setMaxIdle(50);
                            config.setMinIdle(8);
                            config.setMaxWaitMillis(10000L);
                            config.setTestOnBorrow(true);
                            config.setTestOnReturn(false);
                            config.setTestWhileIdle(true);
                            config.setTimeBetweenEvictionRunsMillis(30000L);
                            config.setNumTestsPerEvictionRun(10);
                            config.setMinEvictableIdleTimeMillis(60000L);

                            jedisPool = new JedisPool(config, address, port);
                            log.info("------------JedisPool init success!");
                        }
                    } finally {
                        initLock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (jedisPool == null) {
            throw new NullPointerException("--------------JedisPool is null!");
        }
        return jedisPool.getResource();
    }


    public static boolean set(String key, String value) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.set(key, value);
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean setnx(String key, Object object) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.setnx(key.getBytes(), serialize(object));
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean setnx(String key, String value) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.setnx(key, value);
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean set(String key, Object object) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.set(key.getBytes(), serialize(object));
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean setex(String key, String value, int seconds) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.setex(key, seconds, value);
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean setex(String key, Object object, int seconds) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.setex(key.getBytes(), seconds, serialize(object));
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static String getStringValue(String key) {
        String str = null;
        Jedis client = getInstance();
        try {
            str = client.get(key);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return str;
    }

    public static Object getObjectValue(String key) {
        Object obj = null;
        Jedis client = getInstance();
        try {
            byte[] bytes = client.get(key.getBytes());
            if (bytes != null && bytes.length > 0) {
                obj = unserialize(bytes);
            }
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return obj;
    }

    public static boolean del(String key) {
        boolean result = false;
        Jedis clients = getInstance();
        try {
            clients.del(key);
            result = true;
        } finally {
            if (clients != null) {
                clients.close();
            }
        }
        return result;
    }

    public static boolean exists(String key) {
        boolean result;
        Jedis client = getInstance();
        try {
            result = client.exists(key);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean expire(String key, long seconds) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.expire(key, seconds);
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static boolean expireAt(String key, long unixTime) {
        boolean result = false;
        Jedis client = getInstance();
        try {
            client.expireAt(key, unixTime);
            result = true;
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    private static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    private static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void close() throws IOException {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public static void main(String[] args) {
        address = "127.0.0.1";
        port = 6379;
        init(address, port);
//        final long time = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000; i++) {
//            String s = String.valueOf(i);
//            new Thread(() -> {
//                set(s, UUID.randomUUID().toString().substring(0, 5));
//            }).start();
//        }
//        final long time1 = System.currentTimeMillis();

//        System.out.println(time1 - time);
    }

}
