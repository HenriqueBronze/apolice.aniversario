package com.br.vida.api.srv.apolice.aniversario.commom.utils;

import org.springframework.data.domain.Pageable;

public class PageableWrapper {
    private final Pageable pageable;

    public PageableWrapper(Pageable pageable) {
        this.pageable = pageable;
    }

    public Pageable getPageable() {
        return pageable;
    }
}