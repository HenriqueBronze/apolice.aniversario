package com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request;

import lombok.Data;

@Data
public class Pageable {

    private int pageNumber;
    private int pageSize;

    private Sort sort;

}

