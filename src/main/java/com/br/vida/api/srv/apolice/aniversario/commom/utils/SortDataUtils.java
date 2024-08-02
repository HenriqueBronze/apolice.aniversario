package com.br.vida.api.srv.apolice.aniversario.commom.utils;

import com.br.vida.api.srv.apolice.aniversario.domain.entities.ApolicesEntity;
import com.br.vida.api.srv.apolice.aniversario.exceptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

public class SortDataUtils {
    private static final Logger logger = LoggerFactory.getLogger(SortDataUtils.class);

    public List<ApolicesEntity> sortData(List<ApolicesEntity> data, String sortColumn, String sortDirection) {
        Comparator<ApolicesEntity> comparator = getSortComparator(ApolicesEntity.class, sortColumn, sortDirection);
        data.sort(comparator);
        return data;
    }

    @SuppressWarnings("unchecked")
    public Comparator<ApolicesEntity> getSortComparator(Class<ApolicesEntity> entityClass, String sortColumn, String sortDirection) {
        Comparator<ApolicesEntity> comparator = (entity1, entity2) -> {
            Comparable<Object> value1;
            Comparable<Object> value2;

            try {
                // Obter o método getter correspondente ao campo
                String getterName = "get" + Character.toUpperCase(sortColumn.charAt(0)) + sortColumn.substring(1);
                Method getter = entityClass.getMethod(getterName);

                value1 = (Comparable<Object>) getter.invoke(entity1);
                value2 = (Comparable<Object>) getter.invoke(entity2);

                if (value1 == null && value2 == null) {
                    return 0;
                }
                if (value1 == null) {
                    return 1; // Coloque nulos no final
                }
                if (value2 == null) {
                    return -1; // Coloque nulos no final
                }

                return value1.compareTo(value2);

            } catch (ReflectiveOperationException e) {
                logger.error("Erro ao acessar o método para ordenação: {}", e.getMessage());
                throw new BadRequestException("Erro ao acessar o método para ordenação");
            }
        };

        if ("DESC".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

}
