package com.br.vida.api.srv.apolice.aniversario;

import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.domain.entities.CachedResult;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Pageable;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.ProcedureRequest;
import com.br.vida.api.srv.apolice.aniversario.domain.valueobjects.request.Sort;
import com.br.vida.api.srv.apolice.aniversario.exceptions.BadRequestException;
import com.br.vida.api.srv.apolice.aniversario.repository.StoredProcedureRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoredProcedureRepositoryImplTest {

	@InjectMocks
	private StoredProcedureRepositoryImpl storedProcedureRepository;

	@Mock
	private EntityManager entityManager;

	@Mock
	private CacheManager cacheManager;

	@Test
	@DisplayName("Teste integrado para listar todas as apolices")
	void testCallStoredProcedure() {
		// Configuração do ProcedureRequest e Pageable
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

		List<ApolicesEntity> list = new ArrayList<>();

		// Mock do cache
		Cache cache = mock(Cache.class);
		when(cacheManager.getCache(anyString())).thenReturn(cache);
		when(cache.get(anyString(), eq(CachedResult.class))).thenReturn(null);

		// Mock do EntityManager
		StoredProcedureQuery query = mock(StoredProcedureQuery.class);
		when(entityManager.createStoredProcedureQuery(anyString(), eq(ApolicesEntity.class))).thenReturn(query);
		when(query.getResultList()).thenReturn(list);

		// Chamada do método
		Page<ApolicesEntity> responseEntity = storedProcedureRepository.callStoredProcedure(request, "seguradoApolice", PageRequest.of(1, 10));

		// Verificação das respostas
		assertEquals(0, responseEntity.getTotalElements());

		// Verificação para ver se os métodos foram chamados
		verify(entityManager, times(1)).createStoredProcedureQuery(anyString(), eq(ApolicesEntity.class));
		verify(cache, times(1)).put(anyString(), any(CachedResult.class));
	}

	@Test
	@DisplayName("Teste integrado Exception para listar todas as apolices")
	void testCallStoredProcedureException() {
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

		// Mock do cache
		Cache cache = mock(Cache.class);
		when(cacheManager.getCache(anyString())).thenReturn(cache);
		when(cache.get(anyString(), eq(CachedResult.class))).thenReturn(null);

		// Mock do EntityManager
		//StoredProcedureQuery query = mock(StoredProcedureQuery.class);
		when(entityManager.createStoredProcedureQuery(anyString(), eq(ApolicesEntity.class)))
				.thenThrow(new BadRequestException("Erro ao executar a stored procedure"));

		// Verificação para exceção
		BadRequestException exception = assertThrows(BadRequestException.class, () -> {
			storedProcedureRepository.callStoredProcedure(request, "seguradoApolice", PageRequest.of(1, 10));
		});

		assertEquals("Erro ao executar a stored procedure", exception.getMessage());

		// Verificação para ver se os métodos foram chamados
		verify(entityManager, times(1)).createStoredProcedureQuery(anyString(), eq(ApolicesEntity.class));
		verify(cache, never()).put(anyString(), any(CachedResult.class));
	}
}
