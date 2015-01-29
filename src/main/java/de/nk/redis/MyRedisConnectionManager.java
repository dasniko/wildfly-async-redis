package de.nk.redis;

import org.redisson.Config;
import org.redisson.connection.SingleConnectionManager;

import com.lambdaworks.redis.RedisAsyncConnection;

public class MyRedisConnectionManager extends SingleConnectionManager {

    public MyRedisConnectionManager() {
        super(config().useSingleServer().setAddress("127.0.0.1:6379"), config());
    }

    private static Config config() {
        Config cfg = new Config();
        cfg.setCodec(new MyCodec());
        return cfg;
    }

    public RedisAsyncConnection<Object, Object> connect() {
        return this.connectionWriteOp(0).getAsync();
    }
}
