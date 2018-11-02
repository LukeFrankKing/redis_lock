package com.mit.redislock.main;

import com.mit.redis.conf.RedisUtils;
import com.mit.redislock.task.TaskList;

public class Main {
	
	
	
	
	public static void main(String[] args) {
		//加载进入 redis
		contextInit();
		
		
		//findResult();
		//心跳检测
		TaskList.beat();
		
		


	}
	
	
	public static void contextInit(){
		//
		String lockTxt="";
		//locklua
		String luahash = RedisUtils.getString("locklua");
		if(null==luahash){
			//load lock
			lockTxt = RedisUtils.loadLua("lock.lua");
			RedisUtils.setString("locklua", lockTxt);
		}
		
	}
	
}
