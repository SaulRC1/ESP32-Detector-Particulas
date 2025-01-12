package com.uhu.esp32.controller;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.services.ESP32ConnectionStatusService;
import com.uhu.esp32.services.ParticleDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private ESP32ConnectionStatusService esp32ConnectionStatusService;
    
    @PostMapping("/save-particle-data")
    public String saveParticleData(@RequestBody ParticleData particleData)
    {
        particleDataService.saveParticleData(particleData);
        return "Particle data saved";
    }
    
    @GetMapping("/get-recent-particle-data")
    public List<ParticleData> getMostRecentParticleData()
    {
        return particleDataService.findMostRecentData();
    }
    
    @GetMapping("/get-paginated-particle-data/{page}/{sortingField}")
    public List<ParticleData> getPaginatedParticleData(@PathVariable String page, @PathVariable String sortingField)
    {
        int numericPage = Integer.parseInt(page);
        Pageable pageable;
        
        switch (sortingField)
        {
            case "date_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").descending());
                return particleDataService.findPaginatedData(pageable);
            case "date_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").ascending());
                return particleDataService.findPaginatedData(pageable);
            case "pm10_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm10").descending());
                return particleDataService.findPaginatedData(pageable);
            case "pm10_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm10").ascending());
                return particleDataService.findPaginatedData(pageable);
            case "pm2_5_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm25").descending());
                return particleDataService.findPaginatedData(pageable);
            case "pm2_5_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm25").ascending());
                return particleDataService.findPaginatedData(pageable);
            default:
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").descending());
                return particleDataService.findPaginatedData(pageable);
        }
    }
    
    @GetMapping("/get-paginated-particle-data-number-of-pages")
    public int getPaginatedParticleData()
    {        
        Pageable pageable = PageRequest.of(0, 30, Sort.by("measurementTimestamp").descending());
        
        return particleDataService.findNumberOfPagesForPaginatedData(pageable);
    }
    
    @PostMapping("/set-esp32-status")
    public String setEsp32Status(@RequestBody ESP32ConnectionStatus esp32ConnectionStatus)
    {
        this.esp32ConnectionStatusService.setESP32ConnectionStatus(esp32ConnectionStatus.getStatus());
        return "ESP32 status has been set to '" + esp32ConnectionStatus.getStatus() + "'";
    }
}
