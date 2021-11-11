package com.delivey.infra.repository;

import com.delivey.domain.exception.EntidadeNaoEncontradaException;
import com.delivey.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "FROM " + getDomainClass().getName();
        T entity = entityManager.createQuery(jpql, getDomainClass())
                                .setMaxResults(1).getSingleResult();

        return Optional.ofNullable(entity);
    }

    @Override
    public T buscarOuFalhar(Long id) {
        String jpql = String.format("FROM %s WHERE id = :id", getDomainClass().getName());

        try {
            T entity = entityManager
                    .createQuery(jpql, getDomainClass())
                    .setParameter("id", id)
                    .getSingleResult();

            return entity;
        } catch (NoResultException e) {
            throw new EntidadeNaoEncontradaException(String.format(String.format("%s de código %d não encontrada", getDomainClass().getSimpleName(), id)));
        }
    }

}
