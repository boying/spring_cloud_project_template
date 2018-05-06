package com.jzw.common.redis;

import com.jzw.common.gson.GsonUtils;
import com.google.common.reflect.TypeToken;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Type;

/**
 * Created by boying on 2017/9/27.
 */
public class RedisUtils {
    private RedisTemplate redisTemplate;

    public RedisUtils() {
    }

    public RedisUtils(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value, Long milliseconds) {
        if (value == null) {
            throw new IllegalArgumentException("value can't be null");
        }
        final String json;
        if (value instanceof String) {
            json = (String) value;
        } else {
            json = GsonUtils.getGson().toJson(value);
        }
        set(key, json.getBytes(), milliseconds);
    }

    public void set(String key, byte[] bytes, Long milliseconds) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can't be null");
        }
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                if (milliseconds == null) {
                    connection.set(key.getBytes(), bytes);
                } else {
                    connection.set(key.getBytes(), bytes, Expiration.milliseconds(milliseconds), null);
                }
                return null;
            }
        });
    }

    public void set(String key, Object value) {
        set(key, value, null);
    }

    public <T> T get(String key, Class<T> clazz) {
        byte[] bytes = getBytes(key);

        if (bytes == null) {
            return null;
        }

        if (bytes.length == 0) {
            if (clazz == String.class) {
                return (T) "";
            }
            throw new IllegalStateException("value bytes size is 0, can't be deserialized");
        }

        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        String json = stringSerializer.deserialize(bytes);

        if (clazz == String.class) {
            return (T) json;
        }
        return GsonUtils.getGson().fromJson(json, clazz);
    }

    /**
     * Gson gen type, get("key", new TypeToken<>(){}.getType())
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T get(String key, Type type) {
        if (type == null) {
            throw new IllegalArgumentException("type can't be null");
        }

        byte[] bytes = getBytes(key);
        if (bytes == null) {
            return null;
        }

        if (bytes.length == 0) {
            Type stringType = new TypeToken<String>() {
            }.getType();
            if (stringType.equals(type)) {
                return (T) "";
            }
            throw new IllegalStateException("value bytes size is 0, can't be deserialized");
        }

        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        String json = stringSerializer.deserialize(bytes);

        if (json == null) {
            return null;
        }

        return GsonUtils.getGson().fromJson(json, type);
    }

    public byte[] getBytes(String key) {
        return (byte[]) redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public String getString(String key) {
        return get(key, String.class);
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}

