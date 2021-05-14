package com.delivey.jpa;

import com.delivey.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    public Cozinha buscarPor(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = buscarPor(id);
        manager.remove(cozinha);
    }

}
