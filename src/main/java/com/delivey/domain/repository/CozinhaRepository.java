package com.delivey.domain.repository;

import com.delivey.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findQualquerCoisaByNomeContaining(String nome);
    Optional<Cozinha> queryByNome(String nome);
    Boolean existsCozinhaByNome(String nome);

}
