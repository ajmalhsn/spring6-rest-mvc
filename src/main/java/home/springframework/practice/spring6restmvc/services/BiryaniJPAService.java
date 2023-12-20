package home.springframework.practice.spring6restmvc.services;


import home.springframework.practice.spring6restmvc.entities.Biryani;
import home.springframework.practice.spring6restmvc.mappers.BiryaniMapper;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.repositories.BiryaniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
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
        return biryaniMapper.biryaniToBiryaniDTO(biryaniRepository.save(biryaniMapper.biryaniDTOToBiryani(biryaniDTO)));
    }

    @Override
    public Optional<BiryaniDTO> updateById(UUID id, BiryaniDTO biryaniDTO) {
        AtomicReference<Optional<BiryaniDTO>> atomicBiryani = new AtomicReference<>();
        biryaniRepository.findById(id).ifPresentOrElse(foundBiryani -> {
            foundBiryani.setBiryaniName(biryaniDTO.getBiryaniName());
            foundBiryani.setBiryaniStyle(biryaniDTO.getBiryaniStyle());
            foundBiryani.setPrice(biryaniDTO.getPrice());
            foundBiryani.setQuantityOnHand(biryaniDTO.getQuantityOnHand());
            foundBiryani.setBarcode(biryaniDTO.getBarcode());
            foundBiryani.setUpdateDate(LocalDateTime.now());

            atomicBiryani.set(Optional.of(biryaniMapper.biryaniToBiryaniDTO(biryaniRepository.save(foundBiryani))));
        },() -> atomicBiryani.set(Optional.empty()));

        return atomicBiryani.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if(biryaniRepository.existsById(id)) {
            biryaniRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Optional<BiryaniDTO> patchById(UUID biryaniId, BiryaniDTO biryaniDTO) {
        AtomicReference<Optional<BiryaniDTO>> atomicBiryani = new AtomicReference<>();
        Optional<Biryani> existingOptional = biryaniRepository.findById(biryaniId);
        existingOptional.ifPresentOrElse((existing) -> {
            if (StringUtils.hasText(biryaniDTO.getBiryaniName())) {
                existing.setBiryaniName(biryaniDTO.getBiryaniName());
            }
            if (biryaniDTO.getBiryaniStyle() != null) {
                existing.setBiryaniStyle(biryaniDTO.getBiryaniStyle());
            }
            if (biryaniDTO.getPrice() != null) {
                existing.setPrice(biryaniDTO.getPrice());
            }
            if (StringUtils.hasText(biryaniDTO.getBarcode())) {
                existing.setBarcode(biryaniDTO.getBarcode());
            }
            if (biryaniDTO.getQuantityOnHand() != null) {
                existing.setQuantityOnHand(biryaniDTO.getQuantityOnHand());
            }
            if (biryaniDTO.getVersion() != null) {
                existing.setVersion(biryaniDTO.getVersion());
            }

            atomicBiryani.set(Optional.of(biryaniMapper.biryaniToBiryaniDTO(biryaniRepository.save(existing))));
        },() -> atomicBiryani.set(Optional.empty()));

        return atomicBiryani.get();

    }
}
