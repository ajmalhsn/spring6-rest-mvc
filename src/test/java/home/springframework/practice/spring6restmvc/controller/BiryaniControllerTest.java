package home.springframework.practice.spring6restmvc.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BiryaniControllerTest {

    @Autowired
    BiryaniController biryaniController;

    @Test
    public void getBiryaniId() {

        System.out.println(biryaniController
                .getBiryaniById(biryaniController
                .getBiryaniList()
                .get(0)
                .getId()));
    }

}