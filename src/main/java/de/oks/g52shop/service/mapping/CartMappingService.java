package de.oks.g52shop.service.mapping;

import de.oks.g52shop.domain.dto.CartDto;
import de.oks.g52shop.domain.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMappingService.class)
public interface CartMappingService {

    CartDto mapEntityToDto(Cart entity);

    Cart mapDtoToEntity(CartDto dto);
}
