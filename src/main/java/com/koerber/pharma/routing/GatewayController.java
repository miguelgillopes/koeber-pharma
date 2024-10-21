package com.koerber.pharma.routing;

import com.koerber.pharma.dto.Patient;
import com.koerber.pharma.services.GatewayService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharma")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping("/consults")
    public void creatConsults(){

    }

    @GetMapping("/specialties")
    public void getSpecialties(){

    }

    @GetMapping("/patients")
    public Page<Patient> getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return gatewayService.getPatients(page, size);
    }
}
