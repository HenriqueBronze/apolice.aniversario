package com.br.vida.api.srv.apolice.aniversario.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class CachedResult<T> {
    private List<T> data;
    private String sortColumn;
    private String sortDirection;
    private int page;
    private int size;

    public CachedResult(List<T> data, String sortColumn, String sortDirection, int page, int size) {
        this.data = data;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
        this.page = page;
        this.size = size;
    }
}    

