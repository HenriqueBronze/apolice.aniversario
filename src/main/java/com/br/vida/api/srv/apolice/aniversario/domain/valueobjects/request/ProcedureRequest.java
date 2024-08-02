package com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request;

import lombok.Data;

@Data
public class ProcedureRequest {
    private String sortColumn;

    private String sortDirection;

    private Pageable pageable;
    
}