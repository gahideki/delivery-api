package com.delivey.domain.repository;

import com.delivey.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);
    Integer countByCozinhaId(Long cozinhaId);
    // List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

}
