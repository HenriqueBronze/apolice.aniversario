package com.br.vida.api.srv.apolice.aniversario.controller;

import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Pageable;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Sort;
import com.br.vida.api.srv.apolice.aniversario.services.ApoliceAtrasoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AplicacaoControllerTest {


    @MockBean
    private ApoliceAtrasoService apoliceAtrasoService;

    private final Page<ApolicesEntity> respostaEsperada = criarResponse();

    @Test
    @DisplayName("Teste para listar todas as apolices")
    void listarAplolicesAtraso()  {

        ProcedureRequest request = new ProcedureRequest();
        request.setSortColumn("seguradoApolice");
        request.setSortDirection("ASC");

        Sort sort = new Sort();
        sort.setUnsorted(false);
        sort.setSorted(true);
        sort.setEmpty(false);

        Pageable pageable = new Pageable();
        pageable.setPageSize(10);
        pageable.setPageNumber(1);
        pageable.setSort(sort);

        request.setPageable(pageable);

        when(apoliceAtrasoService.callStoredProcedure(request))
                .thenReturn(respostaEsperada);

        Page<ApolicesEntity> apolicesPage = apoliceAtrasoService.callStoredProcedure(request);
        List<ApolicesEntity> apolices = apolicesPage.getContent();

        assertNotNull(apolicesPage);
        assertEquals("John Arm Less", apolices.get(0).getSeguradoApolice());
    }

    private Page<ApolicesEntity> criarResponse(){
        ApolicesEntity response = new ApolicesEntity();
        response.setHandle(1L);
        response.setCertificadoApolice(12345L);
        response.setPropostaSeguro(54321L);
        response.setProdutoApolice("Seguro Vida");
        response.setSeguradoApolice("John Arm Less");
        response.setCpfCnpj("12345678901");
        response.setTelefoneSegurado("(11) 98765-4321");
        response.setEmailSegurado("johnarmless@example.com");
        response.setSucursalApolice(1);
        response.setAgenciaBancariaSegurado(1234);
        response.setCorretorSeguroVida(5678);
        response.setFormaPagamentoFatura(1);
        response.setPeriodicidadePagamentoFatura(1);
        response.setQtParcelaPendenteApolice((short) 3);
        response.setNuParcelaPendenteApolice((short) 1);
        response.setVrParcelaPendenteApolice(new BigDecimal("100.50"));
        response.setDtVencimentoParcelaApolice(LocalDate.of(2023, 7, 1));
        response.setCdEndossoApolice(1L);
        response.setWkChaveUnicaReferenciaSap("ABC123");
        response.setCdSituacaoParcelaApolice((byte) 1);
        response.setQtDiaCancelamentoApolice(30);

        List<ApolicesEntity> list = new ArrayList<>();
        list.add(response);

        return new PageImpl<>(list);
    }
}
