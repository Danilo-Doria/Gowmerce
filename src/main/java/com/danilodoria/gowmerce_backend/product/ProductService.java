package com.danilodoria.gowmerce_backend.product;

import com.danilodoria.gowmerce_backend.exception.DuplicateSkuException;
import com.danilodoria.gowmerce_backend.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        if (productRepository.existsBySku(request.sku())) {
            throw new DuplicateSkuException("El SKU '" + request.sku() + "' ya está registrado.");
        }

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponseDTO(savedProduct);
    }

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "El producto con id: '" + id + "' no fue encontrado."
                ));

        if (!existingProduct.getSku().equals(dto.sku()) && productRepository.existsBySku(dto.sku())) {
            throw new DuplicateSkuException(
                    "El SKU '" + dto.sku() + "' ya está registrado por otro producto."
            );
        }

        existingProduct.setName(dto.name());
        existingProduct.setDescription(dto.description());
        existingProduct.setPrice(dto.price());
        existingProduct.setStock(dto.stock());
        existingProduct.setSku(dto.sku());

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.toResponseDTO(updatedProduct);
    }


    List<ProductResponseDTO> getAllActiveProducts() {
        List<Product> productList = productRepository.findAllByActive(true);

        return productList.stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
    }


    ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "El producto con id: '" + id + "' no fue encontrado."
                ));

        return ProductMapper.toResponseDTO(product);
    }

    ProductResponseDTO getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException(
                        "El producto con SKU '" + sku + "' no fue encontrado."
                ));

        return ProductMapper.toResponseDTO(product);
    }

    void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "El producto con id: '" + id + "' no fue encontrado."
                ));

        product.setActive(false);

        productRepository.save(product);
    }

}
