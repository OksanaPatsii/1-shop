package de.oks.g52shop.controller;


import de.oks.g52shop.domain.dto.ProductDto;
import de.oks.g52shop.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Controller for various operations with Products")

public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    //Produkt in der Datenbank speichern
    //POST -> http://12.34.56.78:8080/products

    @PostMapping
    public ProductDto save(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Instance of a Product")
            ProductDto product) {
        return service.save(product);
    }


    // Alle Produkte aus der Datenbank zurückgeben (aktiv)
    // GET -> http://12.34.56.78:8080/products/all

    @GetMapping("/all")
    @Operation(
            summary = "Get all products",
            description = "Getting all products that exist in the database"
    )
    public List<ProductDto> getAll() {
        return service.getAllActiveProducts();
    }


    // Gibt ein Produkt aus der Datenbank anhand seiner Kennung zurück (sofern es aktiv ist).
    // GET -> http://12.34.56.78:8080/products?id=5 - Option durch Angabe eines Parameters
    // GET -> http://12.34.56.78:8080/products/5 - Variante, die die Teilzeichenkette der Anfrage verwendet

    @GetMapping("/{id}")
    public ProductDto getById(
            @PathVariable
            @Parameter(description = "Product unique identifier")
            Long id) {
        return service.getById(id);
    }

    // Ein Produkt in der Datenbank anhand der Ego-ID ändern.
    // PUT → http://12.34.56.78:8080/products (Die Ego-ID wird im Anfragetext gesendet.)

    @PutMapping
    public void update(@RequestBody ProductDto product) {
        service.update(product);
    }

    // Produkt anhand seiner Kennung aus der Datenbank löschen.
    // DELETE → http://12.34.56.78:8080/products/5

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    // DELETE -> http://12.34.56.78:8080/products?title=banana - Option 2
    // In diesem Fall verwenden wir die @RequestParam-Annotation.

    @DeleteMapping("/by-title/{title}")
    public void deleteByTitle(@PathVariable String title) {
        service.deleteByTitle(title);
    }

    // Das gelöschte Produkt wird anhand seiner ID in der Datenbank wiederhergestellt.
    // PUT  -> http://12.34.56.78:8080/products/restore/5

    @PutMapping("/{id}")
    public void restoreById(@PathVariable Long id) {
        service.restoreById(id);
    }

    // Gibt die Gesamtzahl der Produkte in der Datenbank (aktiv) zurück.
    // GET → http://12.34.56.78:8080/products/quantity

    @GetMapping("/quantity")
    public long getProductsQuantity() {
        return service.getAllActiveProductsCount();
    }

    // Gibt die Gesamtkosten aller Produkte in der (aktiven) Datenbank zurück.
    // GET → http://12.34.56.78:8080/products/total-cost

    @GetMapping("/total-cost")
    public BigDecimal getProductsTotalCost() {
        return service.getAllActiveProductsTotalCost();
    }

    // Gibt den Durchschnittspreis des Produkts in der Datenbank (der aktiven Produkte) zurück.
    // GET → http://12.34.56.78:8080/products/avg-price

    @GetMapping("/avg-price")
    public BigDecimal getProductsAveragePrice() {
        return service.getAllActiveProductsAveragePrice();
    }

}
