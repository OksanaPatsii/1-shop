package de.oks.g52shop.service.mapping;

import de.oks.g52shop.domain.dto.CustomerDto;
import de.oks.g52shop.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartMappingService.class)
public interface CustomerMappingService {

    CustomerDto mapEntityToDto(Customer entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Customer mapDtoToEntity(CustomerDto dto);
}
