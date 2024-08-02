package com.br.vida.api.srv.apolice.aniversario.controller;

import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Pageable;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Sort;
import com.br.vida.api.srv.apolice.aniversario.services.ApoliceAniversarioService;
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
    private ApoliceAniversarioService apoliceAniversarioService;

    private final Page<ApolicesEntity> respostaEsperada = criarResponse();

    @Test
    @DisplayName("Teste para listar todas as apolices")
    void listarAplolicesaniversario()  {

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

        when(apoliceAniversarioService.callStoredProcedure(request))
                .thenReturn(respostaEsperada);

        Page<ApolicesEntity> apolicesPage = apoliceAniversarioService.callStoredProcedure(request);
        List<ApolicesEntity> apolices = apolicesPage.getContent();

        assertNotNull(apolicesPage);
        assertEquals(12345L, apolices.get(0).getCCertApolc());
    }

    private Page<ApolicesEntity> criarResponse(){
        ApolicesEntity response = new ApolicesEntity();
        response.setHandle(1L);
        response.setCCertApolc(12345L);

        List<ApolicesEntity> list = new ArrayList<>();
        list.add(response);

        return new PageImpl<>(list);
    }
}
