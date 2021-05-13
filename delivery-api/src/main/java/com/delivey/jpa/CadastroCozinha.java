package com.delivey.jpa;

import com.delivey.domain.model.Cozinha;
import org.springframework.stereotype.Component;

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

}