package com.cosmetics.store.controller;

import com.cosmetics.store.config.ApiConfig;
import com.cosmetics.store.entity.request.ProductRequestDto;
import com.cosmetics.store.service.contract.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/product")
@RequiredArgsConstructor
public class ProductController extends Controller {
    private final IProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody @Valid  ProductRequestDto productRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                productService.createProduct(productRequestDto, authentication)
                        )
                );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getPaginatedAllProducts(@RequestParam HashMap<String, Object> params) {
        Pageable pageable = getPageable(params);
        return ResponseEntity
                .ok(
                        message.ok(
                                productService.findAllProducts(pageable, params)
                        )
                );
    }

    @GetMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN', 'USER')")
    public ResponseEntity<?> getProductById(@PathVariable Long idProduct) {
        return ResponseEntity
                .ok(
                        message.ok(
                                productService.findProductById(idProduct)
                        )
                );
    }

    @PutMapping(value = "/{idProduct}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable Long idProduct,
                                           @RequestBody @Valid ProductRequestDto productRequestDto,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                productService.updateProduct(idProduct, productRequestDto, authentication)
                        )
                );
    }

    @DeleteMapping(value = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long idProduct,
                                           Authentication authentication) {
        productService.deleteProduct(idProduct, authentication);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/reactivate/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    public ResponseEntity<?> reactivateProduct(@PathVariable Long idProduct,
                                           Authentication authentication) {
        return ResponseEntity
                .ok(
                        message.ok(
                                productService.reactivateProduct(idProduct, authentication)
                        )
                );
    }
}
