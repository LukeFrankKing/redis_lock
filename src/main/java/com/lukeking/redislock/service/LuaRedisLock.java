package com.lukeking.redislock.service;

import com.lukeking.redis.conf.RedisUtils;

/***
 * 
 * get the power to execute
 * 
 * 
 * */
public final class LuaRedisLock {
   private static String lockHash=RedisUtils.getString("locklua");

   
   
   public static boolean getExecutePower(String lockName,String timeTolerant){
	   if(null==lockHash||"".equals(lockHash)){
		   lockHash=RedisUtils.getString("locklua");
	   }
	   
	   String result = RedisUtils.evalSha(lockHash, 1,lockName,(System.currentTimeMillis())+"",timeTolerant).toString();
	   
	   if("1".equals(result)){
		   return true;
	   }
	   return false;
   }
}
