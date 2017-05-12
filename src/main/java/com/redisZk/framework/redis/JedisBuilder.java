package com.redisZk.framework.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: JedisBuilder
 * @Description: Redis操作
 * @author 唐亚�?
 * @date 2016�?�?5�?
 */
public class JedisBuilder
{
	private Logger logger = LoggerFactory.getLogger(JedisBuilder.class);

	private JedisPool pool = null;

	private String host;

	private int port;

	private int timeout;

	private boolean flag;

	private String password;
	
	public JedisBuilder(String host, int port, int timeout, int maxidle, boolean flag)
	{
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(maxidle);
		// 在borrow�?��jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(flag);
		config.setTimeBetweenEvictionRunsMillis(-1);
		// 在还会给pool时，是否提前进行validate操作
		config.setTestOnReturn(true);
		pool = new JedisPool(config, host, port, timeout);
		this.flag = flag;
	}

	public JedisBuilder(String host, int port, int timeout, int maxidle, boolean flag, String password)
	{
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(maxidle);
		// 在borrow�?��jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(flag);
		config.setTimeBetweenEvictionRunsMillis(-1);
		// 在还会给pool时，是否提前进行validate操作
		config.setTestOnReturn(true);
		pool = new JedisPool(config, host, port, timeout, password);
		this.flag = flag;
	}

	public void initialize()
	{
		pool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
	}

	private final int maxTry = 3;

	public Jedis buildJedis(int db)
	{
		return buildJedis(db, 1);
	}

	public Jedis buildJedis(int db, int tryNum)
	{
		Jedis jedis = null;
		try
		{
			jedis = pool.getResource();
			if (!jedis.isConnected())
			{
				jedis.connect();
			}
			if (flag)
			{
				jedis.select(db);
			} else
			{ // 主动测试ping�?�?��尝试maxTry�?
				try
				{
					if (!jedis.ping().equals("PONG"))
					{
						throw new Exception(" ping failure");
					}
				} catch (Exception e)
				{
					logger.error("catch Exception in buildJedis when test jedis live ...ThreadId="
							+ Thread.currentThread().getId() + ", redishost=" + host + ", port=" + port, e);
					if (jedis != null)
						jedis.close();
					if (tryNum < maxTry)
					{
						tryNum += 1;
						logger.error("catch Exception retry buildJedis.......time= " + tryNum + " ...ThreadId="
								+ Thread.currentThread().getId() + ", redishost=" + host + ", port=" + port);
						return buildJedis(db, tryNum);
					} else
						return null;
				}
			}
		} catch (Exception e)
		{
			logger.error("catch Exception in buildJedis " + ", redishost=" + host + ", port=" + port, e);
			
			return null;
		}
		return jedis;
	}
	
	public void close(Jedis jedis){
		if (jedis != null)
			jedis.close();
	}
	public void stop() {
		pool.destroy();
	}
	
	
}
