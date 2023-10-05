package com.itmastar.product.service;

import com.itmastar.product.dto.ProductRequest;

import com.itmastar.product.dto.ProductResponse;
import com.itmastar.product.modal.Product;
import com.itmastar.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
          Product product=Product.builder()
                  .name(productRequest.getName())
                  .price(productRequest.getPrice())
                  .description(productRequest.getDescription())
                  .build();
    log.info("Product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
       return  products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();

    }
}
