package com.br.vida.api.srv.apolice.aniversario.controller;

import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.services.ApoliceAtrasoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApoliceAtrasoController {

    private static final Logger logger = LoggerFactory.getLogger(ApoliceAtrasoController.class);

    private ApoliceAtrasoService apoliceAtrasoService;

    public ApoliceAtrasoController(ApoliceAtrasoService apoliceAtrasoService){
        this.apoliceAtrasoService = apoliceAtrasoService;
    }

    @Operation(summary = "Retorna apólices em atraso", description = "Busca apólices em atraso com base nos parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/apolices/atraso")
    public Page<ApolicesEntity> retornaCardAtraso(
            @RequestBody(description = "Objeto de requisição contendo os parâmetros necessários", required = true)
            @org.springframework.web.bind.annotation.RequestBody ProcedureRequest requestData) {

        logger.info("Chamando o procedimento armazenado com os parâmetros: {}", requestData);

        return apoliceAtrasoService.callStoredProcedure(requestData);
    }
}