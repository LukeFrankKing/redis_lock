-- my redis lock
-- lockname
local key = KEYS[1]
-- locktime
local lock_time = ARGV[1]
-- lock time tolerant
local time_tolerant = ARGV[2]
-- redis lock time
local redis_time = redis.call('get', key)
if(redis_time==nil or redis_time==false) then
	redis.call('set', key, lock_time)
	return 1;
else
	local get_time_tolerant=lock_time-redis_time
	if(get_time_tolerant>=tonumber(time_tolerant)-2) then
		redis.call('set', key, lock_time)
		return 1;
	else
		return 0;
	end;
end;