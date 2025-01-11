package com.uhu.esp32.controller;

import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.services.ParticleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@RestController
public class ESP32DataReceiverController 
{
    @Autowired
    private ParticleDataService particleDataService;
    
    @PostMapping("/save-particle-data")
    public String saveParticleData(@RequestBody ParticleData particleData)
    {
        particleDataService.saveParticleData(particleData);
        return "Particle data saved";
    }
    
    @GetMapping("/get-particle-data")
    public String getParticleData()
    {
        return "Particle data get";
    }
}
