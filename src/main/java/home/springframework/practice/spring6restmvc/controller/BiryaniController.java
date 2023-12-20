package home.springframework.practice.spring6restmvc.controller;

import home.springframework.practice.spring6restmvc.exceptions.NotFoundException;
import home.springframework.practice.spring6restmvc.models.BiryaniDTO;
import home.springframework.practice.spring6restmvc.services.BiryaniService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/biryani/v1")
public class BiryaniController {

    private final BiryaniService biryaniService;

    @PatchMapping("{biryaniId}")
    public ResponseEntity patchById(@PathVariable("biryaniId") UUID biryaniId,
                                    @RequestBody BiryaniDTO biryaniDTO) {
        if(biryaniService.patchById(biryaniId, biryaniDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{biryaniId}")
    public ResponseEntity deletedById(@PathVariable("biryaniId") UUID id) {

        if(! biryaniService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{biryaniId}")
    public ResponseEntity updateById(@NotNull @PathVariable("biryaniId") UUID biryaniId,
                                     @Validated @RequestBody BiryaniDTO biryaniDTO) {

        if(biryaniService.updateById(biryaniId, biryaniDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity addBiryani(@Validated @RequestBody BiryaniDTO biryaniDTO) {
        BiryaniDTO newBiryaniDTO = biryaniService.saveNewBiryani(biryaniDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "biryani/v1/" + newBiryaniDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping("/list")
    public List<BiryaniDTO> getBiryaniList() {
        log.debug("Biryani List called");
        return biryaniService.biryaniList();
    }

    @RequestMapping("/{Id}")
    public BiryaniDTO getBiryaniById(@PathVariable UUID Id) {
        log.debug("Get Biryani in the Controller");

        return biryaniService.getBiryaniById(Id).orElseThrow(NotFoundException::new);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundHandler() {

        System.out.println("Exception Occurred");
        return ResponseEntity.notFound().build();
    }


}
