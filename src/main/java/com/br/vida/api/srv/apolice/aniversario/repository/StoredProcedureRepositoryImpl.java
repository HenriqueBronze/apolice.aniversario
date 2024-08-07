package com.br.vida.api.srv.apolice.aniversario.repository;

import com.br.vida.api.srv.apolice.aniversario.commom.utils.SortDataUtils;
import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.entities.CachedResult;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.exceptions.BadRequestException;
import com.br.vida.api.srv.apolice.aniversario.interfaces.StoredProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoredProcedureRepositoryImpl implements StoredProcedureRepository<ApolicesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(StoredProcedureRepositoryImpl.class);

    public static final String CERTIFICADO_CACHE = "certificadoCache";
    private final EntityManager entityManager;
    private final CacheManager cacheManager;

    public StoredProcedureRepositoryImpl(EntityManager entityManager, CacheManager cacheManager) {
        this.entityManager = entityManager;
        this.cacheManager = cacheManager;
    }

    @Override
    public Page<ApolicesEntity> callStoredProcedure(ProcedureRequest procedureRequest, String sortColumn, Pageable pageable) {
        try {
            Class<ApolicesEntity> entityClass = ApolicesEntity.class;
    
            String cacheKey = "proc_cards_inadimplencia";
    
            // Verifica se os dados já estão no cache
            Cache cache = cacheManager.getCache(CERTIFICADO_CACHE);
            if (cache != null) {
                CachedResult<ApolicesEntity> cachedResult = cache.get(cacheKey, CachedResult.class);
    
                if (cachedResult == null) {
                    // Se os dados não estiverem no cache, executa a stored procedure e armazena no cache
                    List<ApolicesEntity> allResults = entityManager.
                            createStoredProcedureQuery("temp_consulta_carga_cards_renov", entityClass)
                            .registerStoredProcedureParameter("@cd_usuario", String.class, ParameterMode.IN)
                            .setParameter("@cd_usuario", procedureRequest.getCodCorretor())
                            .getResultList();

                    cachedResult = new CachedResult<>(allResults, null, null, pageable.getPageNumber(), pageable.getPageSize());
                    cache.put(cacheKey, cachedResult);
                }

                SortDataUtils sortDataUtils = new SortDataUtils();

                // Aplica a ordenação nos dados
                List<ApolicesEntity> sortedData = sortDataUtils.sortData(cachedResult.getData(), procedureRequest.getSortColumn(), procedureRequest.getSortDirection());
    
                // Aplica a paginação nos dados
                int fromIndex = Math.min(pageable.getPageNumber() * pageable.getPageSize(), sortedData.size());
                int toIndex = Math.min(fromIndex + pageable.getPageSize(), sortedData.size());
                List<ApolicesEntity> paginatedResults = new ArrayList<>(sortedData.subList(fromIndex, toIndex));
                
                return new PageImpl<>(paginatedResults, pageable, cachedResult.getData().size());
            }
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        } catch (PersistenceException e) {
            logger.error("Ocorreu um erro ao executar a stored procedure {}",e.getMessage());
            throw new BadRequestException("Erro ao executar a stored procedure");
        }
    }
    
}
