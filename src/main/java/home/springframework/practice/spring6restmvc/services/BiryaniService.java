package home.springframework.practice.spring6restmvc.services;

import home.springframework.practice.spring6restmvc.models.Biryani;

import java.util.UUID;

public interface BiryaniService {

    public Biryani getBiryaniById(UUID id);
}
