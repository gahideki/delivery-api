package com.delivey.infra.repository;

import com.delivey.domain.model.Cozinha;
import com.delivey.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    public Cozinha buscarPor(Long id) {
        Cozinha cozinha = manager.find(Cozinha.class, id);

        if (ObjectUtils.isEmpty(cozinha))
            throw new EmptyResultDataAccessException(1);

        return cozinha;
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = buscarPor(id);
        manager.remove(cozinha);
    }

}
