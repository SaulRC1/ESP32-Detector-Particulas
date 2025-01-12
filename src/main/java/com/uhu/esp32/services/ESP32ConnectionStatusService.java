package com.uhu.esp32.services;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.repositories.ESP32ConnectionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
@Transactional(readOnly = true)
public class ESP32ConnectionStatusService 
{
    @Autowired
    private ESP32ConnectionStatusRepository esp32ConnectionStatusRepository;
    
    public ESP32ConnectionStatus getESP32ConnectionStatus()
    {
        return esp32ConnectionStatusRepository.findById(1).get();
    }
    
    @Transactional(readOnly = false)
    @Modifying
    public void setESP32ConnectionStatus(String status)
    {
        this.esp32ConnectionStatusRepository.updateESP32ConnectionStatus(status);
    }
}
