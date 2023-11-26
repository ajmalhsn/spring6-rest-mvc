package home.springframework.practice.spring6restmvc.controller;

import home.springframework.practice.spring6restmvc.models.Biryani;
import home.springframework.practice.spring6restmvc.services.BiryaniService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/biryani/v1")
public class BiryaniController {

    private final BiryaniService biryaniService;

    @RequestMapping("/list")
    public List<Biryani> getBiryaniList() {
        log.debug("Biryani List called");
        return biryaniService.biryaniList();
    }

    @RequestMapping("/{Id}")
    public Biryani getBiryaniById(@PathVariable UUID Id) {
        log.debug("Get Biryani in the Controller");

        return biryaniService.getBiryaniById(Id);
    }


}
