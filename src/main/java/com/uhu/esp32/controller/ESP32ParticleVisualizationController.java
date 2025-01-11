package com.uhu.esp32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Controller
@RequestMapping("/")
public class ESP32ParticleVisualizationController
{
    @GetMapping
    public String getESP32ParticleVisualizationPage(Model model)
    {
        return "particle_visualization";
    }
}
