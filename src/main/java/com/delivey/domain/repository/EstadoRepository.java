package com.delivey.domain.repository;

import com.delivey.domain.model.Estado;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}
