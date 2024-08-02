package com.br.vida.api.srv.apolice.aniversario.services;

import com.br.vida.api.srv.apolice.aniversario.exceptions.InternalRequestException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CacheService<T> {
    public static final String CERTIFICADO_CACHE = "certificadoCache";
    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @SuppressWarnings("unchecked")
    public T getOrLoad(String key, Supplier<T> loader) {
        Cache cache = cacheManager.getCache(CERTIFICADO_CACHE);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            if (valueWrapper != null) {
                return (T) valueWrapper.get();
            }

            T value = loader.get();
            cache.put(key, value);
            return value;
        } else {
            return loader.get();
        }
    }

    public void put(String key, T value) {
        Cache cache = cacheManager.getCache(CERTIFICADO_CACHE);
        if (cache != null) {
            cache.put(key, value);
        } else {
            throw new InternalRequestException("O cache não esta dísponivel");
        }
    }


}