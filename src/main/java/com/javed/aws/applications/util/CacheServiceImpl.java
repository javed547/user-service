/*
package com.javed.aws.applications.util;

import com.javed.aws.applications.model.User;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {

    @Resource(name = "redisTemplate")
    private HashMap<String, User> userHashMap;

    @Resource(name = "redisTemplate")
    private RedisOperations<String,String> latestMessageExpiration;

    public void addUser(String username, User user) {
        userHashMap.put(username,user);
    }

    public User showUser(String username) {
        return userHashMap.get(username);
    }
}
*/
