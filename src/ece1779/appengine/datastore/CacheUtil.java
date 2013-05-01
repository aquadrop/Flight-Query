package ece1779.appengine.datastore;

import java.util.List;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class CacheUtil {

	private static MemcacheService keycache = MemcacheServiceFactory.getMemcacheService();

	public static void addToCache(String key, Object value) {
		keycache.put(key, value);
	}

	public static void deleteFromCache(String key) {
		keycache.delete(key);
	}

	public static void deleteFromCache(List<String> keys) {
		keycache.deleteAll(keys);
	}

	public static Object getFromCache(String key) {
		return keycache.get(key);
	}
}
