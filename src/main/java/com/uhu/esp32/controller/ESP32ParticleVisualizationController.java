package com.uhu.esp32.controller;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.services.ESP32ConnectionStatusService;
import com.uhu.esp32.services.ParticleDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
public class ESP32ParticleVisualizationController
{
    @Autowired
    private ParticleDataService particleDataService;
    
    @Autowired
    private ESP32ConnectionStatusService esp32ConnectionStatusService;
    
    @GetMapping("/")
    public String getESP32ParticleVisualizationWelcomePage(Model model)
    {
        ESP32ConnectionStatus esp32ConnectionStatus = esp32ConnectionStatusService.getESP32ConnectionStatus();
        
        if(esp32ConnectionStatus.getStatus().equals(ESP32ConnectionStatus.STATUS_CONNECTED))
        {
            model.addAttribute("status", ESP32ConnectionStatus.STATUS_CONNECTED);
            model.addAttribute("esp32_status", "conectado");
            model.addAttribute("esp32_status_color", "green");
        }
        else if(esp32ConnectionStatus.getStatus().equals(ESP32ConnectionStatus.STATUS_DISCONNECTED))
        {
            model.addAttribute("status", ESP32ConnectionStatus.STATUS_DISCONNECTED);
            model.addAttribute("esp32_status", "desconectado");
            model.addAttribute("esp32_status_color", "red");
        }
        
        return "particle_visualization";
    }
    
    @GetMapping("/datos-recientes")
    public String getESP32RecentDataPage(Model model)
    {
        /*List<ParticleData> mostRecentData = particleDataService.findMostRecentData();
        
        for (ParticleData particleData : mostRecentData)
        {
            System.out.println(particleData);
        }*/
        
        return "recent_data";
    }
    
    @GetMapping("/historico")
    public String getESP32HistoricDataPage(Model model)
    {
        return "historic_data";
    }
    
    @GetMapping("/graficos")
    public String getESP32DataGraphsPage(Model model)
    {
        return "data_graphs";
    }
}
