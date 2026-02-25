package de.oks.g52shop.service;

import de.oks.g52shop.domain.dto.CustomerDto;
import de.oks.g52shop.domain.entity.Cart;
import de.oks.g52shop.domain.entity.Customer;
import de.oks.g52shop.domain.entity.Product;
import de.oks.g52shop.exception_handling.exceptions.CustomerNotFoundException;
import de.oks.g52shop.repository.CustomerRepository;
import de.oks.g52shop.service.interfaces.CustomerService;
import de.oks.g52shop.service.interfaces.ProductService;
import de.oks.g52shop.service.mapping.CustomerMappingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMappingService mappingService;
    private final ProductService productService;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mappingService, ProductService productService) {
        this.repository = repository;
        this.mappingService = mappingService;
        this.productService = productService;
    }


    @Override
    @Transactional
    public CustomerDto save(CustomerDto dto) {
        Customer entity = mappingService.mapDtoToEntity(dto);

        Cart cart = new Cart();
        cart.setCustomer(entity);
        entity.setCart(cart);

        entity = repository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return List.of();
    }

    @Override
    public CustomerDto getActiveCustomerById(Long id) {
        return null;
    }

    public Customer getActiveCustomerEntityById(Long id) {
        return repository.findById(id)
                .filter(Customer::isActive)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void update(CustomerDto customer) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getAllActiveCustomersNumber() {
        return 0;
    }

    @Override
    public BigDecimal getCustomersCartTotalCost(Long customerId) {
        return null;
    }

    @Override
    public BigDecimal getCustomersProductAveragePrice(Long customerId) {
        return null;
    }

    @Override
    @Transactional
    public void addProductToCustomersCart(Long customerId, Long productId) {
        Customer customer = getActiveCustomerEntityById(customerId);
        Product product = productService.getActiveProductEntityById(productId);
        customer.getCart().addProduct(product);
    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCustomersCart(Long customerId) {

    }
}
