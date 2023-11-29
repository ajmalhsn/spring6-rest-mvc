package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;

import java.util.List;
import java.util.UUID;

public interface BiryaniService {

    public Biryani getBiryaniById(UUID id);

    public List<Biryani> biryaniList();

    Biryani saveNewBiryani(Biryani biryani);

    void updateById(UUID id, Biryani biryani);

    void deleteById(UUID id);

    void patchById(UUID biryaniId, Biryani biryani);
}
