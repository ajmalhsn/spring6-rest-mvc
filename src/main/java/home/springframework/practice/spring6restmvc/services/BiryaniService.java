package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.BiryaniDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BiryaniService {

    public Optional<BiryaniDTO> getBiryaniById(UUID id);

    public List<BiryaniDTO> biryaniList();

    BiryaniDTO saveNewBiryani(BiryaniDTO biryaniDTO);

    void updateById(UUID id, BiryaniDTO biryaniDTO);

    void deleteById(UUID id);

    void patchById(UUID biryaniId, BiryaniDTO biryaniDTO);
}
