package com.uhu.esp32.services;

import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.repositories.ParticleDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
@Transactional(readOnly = true)
public class ParticleDataService 
{
    @Autowired
    private ParticleDataRepository particleDataRepository;
    
    public List<ParticleData> findAllParticleData() 
    {
        return this.particleDataRepository.findAll();
    }
    
    @Transactional(readOnly = false)
    public void saveParticleData(ParticleData particleData)
    {
        this.particleDataRepository.save(particleData);
    }
}
