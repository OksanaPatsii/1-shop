package de.oks.g52shop.controller;

import de.oks.g52shop.domain.dto.CustomerDto;
import de.oks.g52shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    private CustomerDto save(@RequestBody CustomerDto customer) {
        return service.save(customer);
    }

    @PutMapping("/{customerId}/add-product/{productId}")
    public void addProductToCustomersCart(
             @PathVariable Long customerId,
             @PathVariable Long productId) {

        service.addProductToCustomersCart(customerId, productId);
    }
}
