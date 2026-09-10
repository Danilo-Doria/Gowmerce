package com.danilodoria.gowmerce_backend.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String sku,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
