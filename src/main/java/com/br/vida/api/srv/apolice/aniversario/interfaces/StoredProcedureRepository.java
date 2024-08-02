package com.br.vida.api.srv.apolice.aniversario.interfaces;


import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoredProcedureRepository <T> {

    Page<T> callStoredProcedure(ProcedureRequest requestData, String sortColumn, Pageable pageable);

}