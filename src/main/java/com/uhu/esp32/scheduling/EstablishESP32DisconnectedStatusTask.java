package com.uhu.esp32.scheduling;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.services.ESP32ConnectionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Component
public class EstablishESP32DisconnectedStatusTask 
{
    @Autowired
    private ESP32ConnectionStatusService esp32ConnectionStatusService;
    
    @Scheduled(fixedRate = 60000)
    public void executeTask()
    {
        esp32ConnectionStatusService.setESP32ConnectionStatus(ESP32ConnectionStatus.STATUS_DISCONNECTED);
    }
}
