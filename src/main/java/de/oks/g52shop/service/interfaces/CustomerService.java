package de.oks.g52shop.service.interfaces;

import de.oks.g52shop.domain.dto.CustomerDto;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {

    // Speichere den Kunden in der Datenbank (wenn der Kunde gespeichert ist,
    // wird er automatisch als aktiv betrachtet).
    CustomerDto save(CustomerDto customer);

    //    Alle Kunden aus der Datenbank zurückgeben (aktiv).
    List<CustomerDto> getAllActiveCustomers();

    //   Gibt einen Kunden aus der Datenbank anhand seiner ID zurück (falls aktiv).
    CustomerDto getActiveCustomerById(Long id);

    //    Ändern Sie einen Kunden in der Datenbank anhand seiner ID.
    void update(CustomerDto customer);

    //    Einen Kunden anhand seiner ID aus der Datenbank löschen.
    void deleteById(Long id);

    //    Einen Kunden anhand seines Namens aus der Datenbank entfernen.
    void deleteByName(String name);

    //    Einen gelöschten Kunden anhand seiner ID in der Datenbank wiederherstellen.
    void restoreById(Long id);

    //    Gibt die Gesamtzahl der Kunden in der Datenbank (aktiv) zurück.
    long getAllActiveCustomersNumber();

    //    Gibt den Wert des Warenkorbs des Kunden anhand seiner ID zurück (sofern aktiv).
    BigDecimal getCustomersCartTotalCost(Long customerId);

    //    Gibt den Durchschnittspreis eines Produkts im Warenkorb eines Kunden anhand seiner ID zurück (sofern aktiv).
    BigDecimal getCustomersProductAveragePrice(Long customerId);

    //    Füge Artikel anhand der Kunden-IDs zum Warenkorb hinzu (sofern beide aktiv sind).
    void addProductToCustomersCart(Long customerId, Long productId);

    //    Entfernen Sie Artikel aus dem Warenkorb anhand ihrer IDs.
    void removeProductFromCustomersCart(Long customerId, Long productId);

    //    Den gesamten Warenkorb eines Kunden anhand seiner ID leeren (sofern aktiv).
    void clearCustomersCart(Long customerId);
}
