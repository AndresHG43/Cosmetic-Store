package com.cosmetics.store.service.contract;

import com.cosmetics.store.entity.request.ProductRequestDto;
import com.cosmetics.store.entity.response.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.HashMap;

public interface IProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto, Authentication authentication) throws RuntimeException;
    Page<ProductResponseDto> findAllProducts(Pageable pageable, HashMap<String, Object> params) throws RuntimeException;
    ProductResponseDto findProductById(Long idProduct) throws RuntimeException;
    ProductResponseDto updateProduct(Long idProduct, ProductRequestDto productRequestDto, Authentication authentication) throws RuntimeException;
    void deleteProduct(Long idProduct, Authentication authentication) throws RuntimeException;
    ProductResponseDto reactivateProduct(Long idProduct, Authentication authentication) throws RuntimeException;
}
