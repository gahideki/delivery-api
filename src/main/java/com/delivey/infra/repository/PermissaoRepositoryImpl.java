package com.delivey.infra.repository;

import com.delivey.domain.model.Permissao;
import com.delivey.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    public Permissao buscarPor(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Permissao permissao = buscarPor(id);
        manager.remove(permissao);
    }

}
