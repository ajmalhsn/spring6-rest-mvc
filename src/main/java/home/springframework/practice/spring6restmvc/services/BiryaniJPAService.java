package home.springframework.practice.spring6restmvc.services;


import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BiryaniJPAService implements BiryaniService{
    private final BiryaniRepository biryaniRepository;
    private final BiryaniMapper biryaniMapper;

    @Override
    public Optional<BiryaniDTO> getBiryaniById(UUID id) {
        return Optional.ofNullable(biryaniMapper
                .biryaniToBiryaniDTO(biryaniRepository.findById(id).orElse(null)));
    }

    @Override
    public List<BiryaniDTO> biryaniList() {
        return biryaniRepository.findAll()
                .stream()
                .map(biryaniMapper::biryaniToBiryaniDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BiryaniDTO saveNewBiryani(BiryaniDTO biryaniDTO) {
        return null;
    }

    @Override
    public void updateById(UUID id, BiryaniDTO biryaniDTO) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void patchById(UUID biryaniId, BiryaniDTO biryaniDTO) {

    }
}
