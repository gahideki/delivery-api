package com.delivey.infra.repository;

import com.delivey.domain.model.Restaurante;
import com.delivey.domain.repository.RestauranteRepository;
import com.delivey.domain.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.delivey.domain.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.delivey.domain.repository.spec.RestauranteSpecs.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasLength(nome))
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));

        if (!ObjectUtils.isEmpty(taxaFreteInicial))
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));

        if (!ObjectUtils.isEmpty(taxaFreteFinal))
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));

        criteria.where(convertArrayListToArray(predicates));

        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }

    private Predicate[] convertArrayListToArray(ArrayList<Predicate> predicates) {
        return predicates.toArray(new Predicate[0]);
    }

}
