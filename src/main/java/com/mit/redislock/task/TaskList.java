package com.mit.redislock.task;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mit.redislock.service.LuaRedisLock;
import com.mit.redislock.utils.DateUtil;

public class TaskList {

	/**
	 * heart beat
	 */
	public static void beat() {
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				try {
					if(LuaRedisLock.getExecutePower("five_minute","4000")){
						System.out.println(DateUtil.longDateFormat(new Date())+":i get the power,i am alive, everything will be ok!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 5000-System.currentTimeMillis()%5000, 1000*5, TimeUnit.MILLISECONDS);
	}

}
