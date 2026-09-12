package com.danilodoria.gowmerce_backend.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByActive(Boolean active);

    boolean existsBySku(String sku);

    Optional<Product> findBySku(String sku);
}