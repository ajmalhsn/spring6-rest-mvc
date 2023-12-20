package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BiryaniService {

    public Optional<BiryaniDTO> getBiryaniById(UUID id);

    public List<BiryaniDTO> biryaniList();

    BiryaniDTO saveNewBiryani(BiryaniDTO biryaniDTO);

    Optional<BiryaniDTO> updateById(UUID id, BiryaniDTO biryaniDTO);

    Boolean deleteById(UUID id);

    Optional<BiryaniDTO> patchById(UUID biryaniId, BiryaniDTO biryaniDTO);
}
