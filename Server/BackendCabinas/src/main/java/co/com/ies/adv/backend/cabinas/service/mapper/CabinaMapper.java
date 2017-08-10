package co.com.ies.adv.backend.cabinas.service.mapper;

import co.com.ies.adv.backend.cabinas.domain.*;
import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cabina and its DTO CabinaDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface CabinaMapper extends EntityMapper <CabinaDTO, Cabina> {

    @Mapping(source = "user.id", target = "userId")
    CabinaDTO toDto(ICabina cabina); 

    @Mapping(source = "userId", target = "user")
    Cabina toEntity(CabinaDTO cabinaDTO); 
    default Cabina fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cabina cabina = new Cabina();
        cabina.setId(id);
        return cabina;
    }
}
