package com.danilodoria.gowmerce_backend.product;

public class ProductMapper {

    /**
     * Convierte un ProductRequestDTO recibido desde la API
     * en una entidad Product que puede ser persistida.
     */
    public static Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .stock(dto.stock())
                .sku(dto.sku())
                .build();
    }

    /**
     * Convierte una entidad Product en un ProductResponseDTO.
     * Aquí incluimos información que pertenece al backend,
     * como el ID y las fechas de creación/modificación.
     */
    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getSku(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
