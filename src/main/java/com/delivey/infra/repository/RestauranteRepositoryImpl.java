package com.delivey.infra.repository;

import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar() {
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    public Restaurante buscarPor(Long id) {
        Restaurante restaurante = manager.find(Restaurante.class, id);

        if (ObjectUtils.isEmpty(restaurante))
            throw new EmptyResultDataAccessException(1);

        return restaurante;
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscarPor(id);
        manager.remove(restaurante);
    }

}
