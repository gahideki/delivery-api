package com.delivey.infra.repository;

import com.delivey.domain.model.Estado;
import com.delivey.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    public Estado buscarPor(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Estado estado = buscarPor(id);
        manager.remove(estado);
    }

}
