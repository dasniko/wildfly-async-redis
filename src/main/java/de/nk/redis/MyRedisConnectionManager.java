package de.nk.redis;

import org.redisson.Config;
import org.redisson.connection.SingleConnectionManager;

import com.lambdaworks.redis.RedisAsyncConnection;

public class MyRedisConnectionManager extends SingleConnectionManager {

    public MyRedisConnectionManager() {
        super(new Config().useSingleServer().setAddress("127.0.0.1:6379"), new Config());
    }

    public RedisAsyncConnection<Object, Object> connect() {
        return this.connectionWriteOp(0).getAsync();
    }
}
