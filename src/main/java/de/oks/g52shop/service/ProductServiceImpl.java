package de.oks.g52shop.service;

import de.oks.g52shop.domain.dto.ProductDto;
import de.oks.g52shop.domain.entity.Product;
import de.oks.g52shop.exception_handling.exceptions.ProductNotFoundException;
import de.oks.g52shop.exception_handling.exceptions.ProductValidationException;
import de.oks.g52shop.repository.ProductRepository;
import de.oks.g52shop.service.interfaces.ProductService;
import de.oks.g52shop.service.mapping.ProductMappingService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    // private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        try {
        Product entity = mappingService.mapDtoToEntity(dto);
        entity = repository.save(entity);
        return mappingService.mapEntityToDto(entity);
        } catch (Exception e) {
            throw new ProductValidationException(e);
        }
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {

//        logger.info("Request for all products received");
//        logger.warn("Request for all products received");
//        logger.error("Request for all products received");


        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
//        Product product = repository.findById(id).orElse(null);
//
//        if (product == null || !product.isActive()) {
//            throw new ProductNotFoundException(id);
//        }
//
//        return mappingService.mapEntityToDto(product);


        return mappingService.mapEntityToDto(repository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Override
    public void update(ProductDto product) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByTitle(String title) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getAllActiveProductsCount() {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .count();
    }

    @Override
    public BigDecimal getAllActiveProductsTotalCost() {
        return null;
    }

    @Override
    public BigDecimal getAllActiveProductsAveragePrice() {
        return null;
    }
}