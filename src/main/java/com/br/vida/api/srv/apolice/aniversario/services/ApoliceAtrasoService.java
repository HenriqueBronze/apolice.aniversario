package com.br.vida.api.srv.apolice.aniversario.services;

import com.br.vida.api.srv.apolice.aniversario.commom.utils.PageableWrapper;
import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.interfaces.StoredProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "certificadoCache")
public class ApoliceAtrasoService {
    private static final Logger logger = LoggerFactory.getLogger(ApoliceAtrasoService.class);

    private final StoredProcedureRepository<ApolicesEntity> myRepository;
    private final CacheService<Page<ApolicesEntity>> cacheService;

    public ApoliceAtrasoService(
        StoredProcedureRepository<ApolicesEntity> myRepository,
        CacheService<Page<ApolicesEntity>> cacheService
    ) {
        this.myRepository = myRepository;
        this.cacheService = cacheService;
    }

    //@Cacheable(key = "{#procedureRequest, #sortColumn, #pageable}", unless = "#result == null")
    public Page<ApolicesEntity> callStoredProcedure(
        ProcedureRequest procedureRequest
    ) {
        logger.info("Chamada do Serviço");
        Pageable pageable = PageRequest.of(procedureRequest.getPageable().getPageNumber(), procedureRequest.getPageable().getPageSize());

        String cacheKey = getCacheKey(procedureRequest, pageable, procedureRequest.getSortColumn(), procedureRequest.getSortDirection());
        return cacheService.getOrLoad(cacheKey, () -> myRepository.callStoredProcedure(procedureRequest, procedureRequest.getSortColumn(), pageable));
    }

    @Scheduled(cron = "0 0 5 * * ?")
    @CacheEvict(allEntries = true)
    public void clearAndReloadCache() {
        logger.info("Limpeza e atualização do cache");
        String cacheKey = "all_records";
        Page<ApolicesEntity> updatedData = myRepository.callStoredProcedure(
            new ProcedureRequest(),
            "column1",
            PageRequest.of(0, 10)
        );
        cacheService.put(cacheKey, updatedData);
    }

    private String getCacheKey(ProcedureRequest requestData, Pageable pageable, String sortColumn, String sortDirection) {
        PageableWrapper pageableWrapper = new PageableWrapper(pageable);
        return requestData.toString() + pageableWrapper + "_" + sortColumn + "_" + sortDirection;
    }
}