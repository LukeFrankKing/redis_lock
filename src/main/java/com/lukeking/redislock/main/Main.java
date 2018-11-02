package com.lukeking.redislock.main;

import com.lukeking.redis.conf.RedisUtils;
import com.lukeking.redislock.task.TaskList;

public class Main {
	
	
	
	
	public static void main(String[] args) {

		contextInit();

		//findResult();
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
