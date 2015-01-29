package de.nk.redis;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.redisson.codec.RedissonCodec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyCodec implements RedissonCodec {

    private static final Charset charset = Charset.forName("utf-8");
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(ApplicationUid obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ApplicationUid fromJson(byte[] bytes) {
        try {
            return mapper.reader(ApplicationUid.class).readValue(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object decodeKey(ByteBuffer bytes) {
        return fromJson(bytes.array());
    }

    @Override
    public Object decodeMapKey(ByteBuffer bytes) {
        return fromJson(bytes.array());
    }

    @Override
    public Object decodeMapValue(ByteBuffer bytes) {
        return new String(bytes.array(), charset);
    }

    @Override
    public Object decodeValue(ByteBuffer bytes) {
        return new String(bytes.array(), charset);
    }

    @Override
    public byte[] encodeKey(Object key) {
        if (key instanceof ApplicationUid)
            return toJson((ApplicationUid) key).getBytes(charset);
        return null;
    }

    @Override
    public byte[] encodeValue(Object value) {
        return value.toString().getBytes(charset);
    }

    @Override
    public byte[] encodeMapValue(Object value) {
        return this.encodeValue(value);
    }

    @Override
    public byte[] encodeMapKey(Object key) {
        return this.encodeKey(key);
    }

}
