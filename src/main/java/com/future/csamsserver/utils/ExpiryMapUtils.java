package com.future.csamsserver.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @dateTime 2025:03:11 21:28
 * @user Jenming
 */
//@Component
public class ExpiryMapUtils<K, V> extends HashMap<K, V> {
    private HashMap<K, Long> expiryMap = new HashMap<>();
    private long defaultExpiry = 3000; // 默认3秒

    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + defaultExpiry);
        return super.put(key, value);
    }
    public V put(K key, V value,long expiryTime, TimeUnit timeUnit) {
        expiryMap.put(key, System.currentTimeMillis() + timeUnit.toMillis(expiryTime));
        return super.put(key, value);
    }

    @Override
    public V get(Object key) {
        if (expiryMap.containsKey(key) && System.currentTimeMillis() > expiryMap.get(key)) {
            remove(key);
            return null;
        }
        return super.get(key);
    }
    public <T> T get(Object key,T defaultValue) {
        if (expiryMap.containsKey(key) && System.currentTimeMillis() > expiryMap.get(key)) {
            remove(key);
            return defaultValue;
        }
        /*
        * 这里易报错
        * */
        return (T) super.get(key);
    }
}