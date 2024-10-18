package com.koerber.pharma.routing;


import com.koerber.pharma.dto.Patient;
import com.koerber.pharma.services.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharma")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @PostMapping("/consults")
    public void creatConsults(){

    }

    @GetMapping("/specialties")
    public void getSpecialties(){

    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return gatewayService.getPatients();
    }
}
