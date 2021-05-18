package com.delivey.infra.repository;

import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        return manager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscarPor(id);
        manager.remove(restaurante);
    }

}
