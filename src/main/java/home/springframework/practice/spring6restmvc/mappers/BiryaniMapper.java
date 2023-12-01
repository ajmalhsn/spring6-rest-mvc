package home.springframework.practice.spring6restmvc.mappers;

import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import org.mapstruct.Mapper;

@Mapper
public interface BiryaniMapper {
    Biryani biryaniDTOToBiryani(BiryaniDTO dto);
    BiryaniDTO biryaniToBiryaniDTO(Biryani biryani);
}
