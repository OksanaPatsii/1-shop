package de.oks.g52shop.service.interfaces;

import de.oks.g52shop.domain.dto.ProductDto;
import de.oks.g52shop.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    // Speichert das Produkt in der Datenbank
    // (nach dem Speichern wird das Produkt automatisch als aktiv betrachtet).
    ProductDto save(ProductDto product);

    // Gibt alle aktiven Produkte aus der Datenbank zurück.
    List<ProductDto> getAllActiveProducts();

    // Gibt ein aktives Produkt anhand seiner ID aus der Datenbank zurück.
    ProductDto getById(Long id);

    //   Ein aktives Produkt aus der Datenbank abrufen
    Product getActiveProductEntityById(Long id);

    // Aktualisiert ein Produkt in der Datenbank anhand seiner ID.
    void update(ProductDto product);

    // Löscht ein Produkt anhand seiner ID aus der Datenbank.
    void deleteById(Long id);

    // Löscht ein Produkt anhand seines Namens aus der Datenbank.
    void deleteByTitle(String title);

    // Stellt ein gelöschtes Produkt anhand seiner ID in der Datenbank wieder her.
    void restoreById(Long id);

    // Gibt die Gesamtzahl der aktiven Produkte in der Datenbank zurück.
    long getAllActiveProductsCount();

    // Gibt den Gesamtpreis aller aktiven Produkte in der Datenbank zurück.
    BigDecimal getAllActiveProductsTotalCost();

    // Gibt den Durchschnittspreis eines Produkts in der Datenbank (aktiv) zurück.
    BigDecimal getAllActiveProductsAveragePrice();

    // Füge einen Link zu einem Produktbild anhand des Produktnamens hinzu
    void attachImage(String imageUrl, String productTitle);
}
