package com.br.vida.api.srv.apolice.aniversario.commom.utils;


import java.time.LocalDateTime;

public class CachedResult<T> {
    private final T result;
    private final LocalDateTime lastUpdated;

    public CachedResult(T result, LocalDateTime lastUpdated) {
        this.result = result;
        this.lastUpdated = lastUpdated;
    }

    public T getResult() {
        return result;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}