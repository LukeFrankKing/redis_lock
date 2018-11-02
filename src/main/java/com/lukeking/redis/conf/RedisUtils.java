package com.lukeking.redis.conf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis pool tool
 * 
 * @author Administrator
 *
 */
public final class RedisUtils {

    // Redis server IP
    private static String ADDR = "127.0.0.1";

    // Redis port
    private static int PORT = 6379;

    // password
    private static String AUTH = "";


    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 100000;
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    
    
    
    
    public static JedisPool initPool() {
        try {
            // maxActive ==> maxTotal
            // maxWait ==> maxWaitMillis
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setTestOnReturn(false);
            config.setBlockWhenExhausted(true);
            config.setTestOnBorrow(true);
            config.setSoftMinEvictableIdleTimeMillis(120000);
            //add auth
            //jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            //no auth
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
            return jedisPool;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
		return jedisPool;
    }

    /**
     * 获取Jedis实例
     * 
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
            	System.out.println("jedisPool is null which will recreate!s");
            	JedisPool initPool = initPool();
                return initPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * 
     * @param jedis
     */
	public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
        	//returnResource too old
            jedisPool.returnResource(jedis);
        }
    }
	
	
	
	
	
	
	
	
    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public static String getString(String key) {
        Jedis jedis = getJedis();
    	try {
            if (jedis == null || !jedis.exists(key)) {
                return null;
            }
            String value = getJedis().get(key);
            if (null == value || "".equals(value)) {

                return null;
            }
            return value;
		} finally {
			// TODO: handle finally clause
			returnResource(jedis);
		}

    }
	
	
	
	/**
	 * 检查是否还有脚本
	 * @param hash
	 * @return
	 */
	public static boolean existsScript(String hash) {
		Jedis jedis = getJedis();
		try {
			boolean o = jedis.scriptExists(hash);
			return o;
		} finally {
			// TODO: handle finally clause
			returnResource(jedis);
		}
	}
	
	
	
	
	/**
	 * 加载lua脚本工具
	 */
	public static String loadLua(String fileName){
		InputStream is = RedisUtils.class.getClassLoader().getResourceAsStream(fileName);
		Jedis jedis = getJedis();
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			byte[] buff=new byte[100];
			int rc=0;
			try {
				while((rc=is.read(buff,0,100))>0){
					baos.write(buff,0,rc);
				}
				
				String luaTxt=baos.toString();
				System.out.println("luatxt:"+luaTxt);
				String scriptLoadHash = jedis.scriptLoad(luaTxt);
				return scriptLoadHash;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			// TODO: handle finally clause
			returnResource(jedis);
		}
		return null;
	}
	
	
	
	/**
	 * 设置 过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @param value
	 */
	public static String setString(String key, String value) {
		Jedis jedis = getJedis();
		if (jedis.exists(key)) {
			jedis.del(key);
		}
		jedis.set(key, value);
		returnResource(jedis);
		return value;

	}

	/**
	 * 执行脚本hash
	 * @return 
	 * 
	 */
	public static int evalSha(String script,List<String> keys,List<String> params) {
		// TODO Auto-generated method stub
		Jedis jedis = getJedis();
		Object result = jedis.evalsha(script, keys, params);
		System.out.println("evalsha result:"+result.toString());
		result.toString();
		return Integer.parseInt(result.toString());
	}
	
	/**
	 * 执行脚本hash
	 * @return 
	 * 
	 */
	public static String evalSha(String script,int keyCount,String...params) {
		// TODO Auto-generated method stub
		Jedis jedis = getJedis();
		String result = jedis.evalsha(script, keyCount, params).toString();
		return result;
	}
}