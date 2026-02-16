package com.store.cosmetics.service.implementation;

import com.store.cosmetics.entity.Product;
import com.store.cosmetics.entity.Users;
import com.store.cosmetics.entity.mapper.ProductMapper;
import com.store.cosmetics.entity.request.ProductRequestDto;
import com.store.cosmetics.entity.response.ProductResponseDto;
import com.store.cosmetics.repository.ProductRepository;
import com.store.cosmetics.service.contract.IProductService;
import com.store.cosmetics.specification.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Product product = productRequestDto.toEntity();
            product.setActive(true);
            product.setUsersCreated(user);
            productRepository.save(product);

            ProductResponseDto productResponseDto = new ProductResponseDto();
            ProductMapper.convertToResponseDto(product, productResponseDto);
            return productResponseDto;
        } catch (Exception e) {
            log.error("[Product] : Error trying to create the product. ", e);
            throw new RuntimeException ("Error trying to create the product");
        }
    }

    @Override
    public Page<ProductResponseDto> findAllProducts(Pageable pageable, HashMap<String, Object> params) throws RuntimeException {
        try {
            Specification<Product> spec = Specification.allOf(ProductSpecification.active(true));

            if (params.containsKey("field") && params.containsKey("value")) {
                if (!params.get("field").toString().equals("null")
                        && !params.get("field").toString().equals("undefined")
                        && !params.get("field").toString().isEmpty() && !params.get("value").toString().equals("null")
                        && !params.get("value").toString().equals("undefined")
                        && !params.get("value").toString().isEmpty()) {
                    String field = params.get("field").toString();
                    String value = params.get("value").toString();
                    if (field.equals("name") || field.equals("description")) {
                        spec = spec.and(ProductSpecification.containsNormalized(value, field));
                    }
                }
            }

            Page<Product> productPage = productRepository.findAll(spec, pageable);

            return productPage.map(product -> {
                ProductResponseDto productResponseDto = new ProductResponseDto();
                ProductMapper.convertToResponseDto(product, productResponseDto);
                return productResponseDto;
            });
        } catch (Exception e) {
            log.error("[Product] : Error trying to get product's list. ", e);
            throw new RuntimeException ("Error trying to get product's list");
        }
    }

    @Override
    public ProductResponseDto findProductById(Long idProduct) throws RuntimeException {
        try {
            Product product = productRepository.findProductByActiveTrueAndId(idProduct).orElseThrow(
                    () -> new EntityNotFoundException("Product with id " + idProduct + " not found"));

            ProductResponseDto productResponseDto = new ProductResponseDto();

            ProductMapper.convertToResponseDto(product, productResponseDto);
            return productResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Product] : Product not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Product] : Error trying to get product by id. ", e);
            throw new RuntimeException ("Error trying to get product by id");
        }
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long idProduct, ProductRequestDto productRequestDto, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Product product = productRepository.findProductByActiveTrueAndId(idProduct).orElseThrow(
                    () -> new EntityNotFoundException("Product with id " + idProduct + " not found"));

            ProductMapper.updateProduct(productRequestDto, product);

            product.setUsersUpdated(user);
            productRepository.save(product);

            ProductResponseDto productResponseDto = new ProductResponseDto();
            ProductMapper.convertToResponseDto(product, productResponseDto);
            return productResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Product] : Product not found. ", e);
            throw e;
        } catch (Exception e) {
            log.error("[Product] : Error trying to update the product. ", e);
            throw new RuntimeException ("Error trying to update the product");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long idProduct, Authentication authentication) throws RuntimeException {
        try {
            LocalDateTime dateDeleted = LocalDateTime.now();

            Users user = (Users) authentication.getPrincipal();

            Product product = productRepository.findProductByActiveTrueAndId(idProduct).orElseThrow(
                    () -> new EntityNotFoundException("Product with id " + idProduct + " not found"));

            product.setActive(false);
            product.setDateDeleted(dateDeleted);
            product.setUsersDeleted(user);
            productRepository.save(product);
        } catch (EntityNotFoundException e) {
            log.warn("[Product] : Product not found. ", e);
            throw e;
        } catch (Exception e)  {
            log.error("[Product] : Error trying to delete the product. ", e);
            throw new RuntimeException ("Error trying to delete the product");
        }
    }

    @Override
    @Transactional
    public ProductResponseDto reactivateProduct(Long idProduct, Authentication authentication) throws RuntimeException {
        try {
            Users user = (Users) authentication.getPrincipal();

            Product product = productRepository.findProductByActiveFalseAndId(idProduct).orElseThrow(
                    () -> new EntityNotFoundException("Deleted product with id " + idProduct + " not found"));

            product.setActive(true);
            product.setDateDeleted(null);
            product.setUsersDeleted(null);
            product.setUsersUpdated(user);
            productRepository.save(product);

            ProductResponseDto productResponseDto = new ProductResponseDto();
            ProductMapper.convertToResponseDto(product, productResponseDto);
            return productResponseDto;
        } catch (EntityNotFoundException e) {
            log.warn("[Product] : Product not found. ", e);
            throw e;
        } catch (Exception e)  {
            log.error("[Product] : Error trying to activate the product. ", e);
            throw new RuntimeException ("Error trying to activate the product");
        }
    }
}
