package com.uhu.esp32.repositories;

import com.uhu.esp32.data.ParticleData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Repository
public interface ParticleDataRepository extends JpaRepository<ParticleData, Long>
{
    public List<ParticleData> findTop50ByOrderByMeasurementTimestampDesc();
}
