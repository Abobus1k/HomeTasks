package edu.hw10.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<List<Object>, Long> cache = new HashMap<>();
    private final boolean persist;
    private final String cacheDirectory;

    private CacheProxy(Object target, boolean persist, String cacheDirectory) {
        this.target = target;
        this.persist = persist;
        this.cacheDirectory = cacheDirectory;

        if (persist) {
            loadCache();
        }
    }

    public static <T> T create(T target, Class<?> interfaceClass, boolean persist, String cacheDirectory) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[]{interfaceClass},
            new CacheProxy(target, persist, cacheDirectory)
        );
    }

    private static boolean getPersistAnnotation(Class<?> interfaceClass) {
        Cache cacheAnnotation = interfaceClass.getAnnotation(Cache.class);
        return cacheAnnotation != null && cacheAnnotation.persist();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            List<Object> key = Arrays.asList(args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                long result = (long) method.invoke(target, args);
                cache.put(key, result);

                if (persist) {
                    persistCache();
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private void persistCache() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getCacheFileName()))) {
            oos.writeObject(cache);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCacheFileName() {
        return cacheDirectory + File.separator + "cache.ser";
    }

    private void loadCache() {
        File cacheFile = new File(getCacheFileName());
        if (cacheFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
                cache.putAll((Map<List<Object>, Long>) ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
