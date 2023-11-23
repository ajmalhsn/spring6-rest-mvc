package home.springframework.practice.spring6restmvc.controller;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.services.BiryaniService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;


@Slf4j
@AllArgsConstructor
@Controller
public class BiryaniController {

    private final BiryaniService biryaniService;

    public Biryani getBiryaniById(String id) {
        log.debug("Get Biryani in the Controller");
        UUID uuid = UUID.randomUUID();
        return biryaniService.getBiryaniById(uuid);
    }


}
