package com.uhu.esp32.repositories;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.data.ParticleData;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SaulRC1
 */
@Repository
public interface ESP32ConnectionStatusRepository extends JpaRepository<ESP32ConnectionStatus, Integer>
{
    @Transactional
    @Modifying
    @Query("UPDATE ESP32ConnectionStatus e SET e.status = :status WHERE e.id = 1")
    public void updateESP32ConnectionStatus(String status);
}
