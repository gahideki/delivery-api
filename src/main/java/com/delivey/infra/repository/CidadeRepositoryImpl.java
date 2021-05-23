package com.delivey.infra.repository;

import com.delivey.domain.model.Cidade;
import com.delivey.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    public Cidade buscarPor(Long id) {
        Cidade cidade = manager.find(Cidade.class, id);

        if (ObjectUtils.isEmpty(cidade))
            throw new EmptyResultDataAccessException(1);

        return cidade;
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cidade cidade = buscarPor(id);
        manager.remove(cidade);
    }

}
