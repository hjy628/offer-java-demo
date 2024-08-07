package cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther: hjy
 * @Date: 2020/6/10 16:42
 * @Description: ConcurrentHashMap实现本地缓存
 */

public class CacheManager {



    private static ConcurrentHashMap<String,Object> caches = new ConcurrentHashMap<>();

    public static void set(final String key,Object o){
        caches.put(key, o);
    }

    public static void remove(final String key){
        caches.remove(key);
    }

    public static  Object get(String key){
        return caches.get(key);
    }



}
