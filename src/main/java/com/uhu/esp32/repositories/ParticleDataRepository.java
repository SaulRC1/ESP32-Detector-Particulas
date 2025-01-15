package com.uhu.esp32.repositories;

import com.uhu.esp32.data.ParticleData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Repository
public interface ParticleDataRepository extends JpaRepository<ParticleData, Long>
{
    public List<ParticleData> findTop50ByOrderByMeasurementTimestampDesc();
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	AVG(PM10) as media_pm10
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM10AverageByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	MAX(PM10) as max_pm10
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM10MaximumByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	MIN(PM10) as min_pm10
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM10MinimumByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	AVG(PM2_5) as media_pm25
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM25AverageByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	MAX(PM2_5) as max_pm25
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM25MaximumByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE_FORMAT(measurement_timestamp, '%Y-%m-%d %H:00:00') AS hour_group,
        	MIN(PM2_5) as min_pm25
        FROM ESP32_PARTICULAS.PARTICLE_DATA
        WHERE DATE(measurement_timestamp) = :date
        GROUP BY hour_group
        ORDER BY hour_group
        """)
    public List<Object[]> findPM25MinimumByHourFromDay(String date);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            AVG(pm10) AS media_pm10
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM10AverageByDateRange(String startDate, String endDate);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            MAX(pm10) AS max_pm10
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM10MaximumByDateRange(String startDate, String endDate);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            MIN(pm10) AS min_pm10
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM10MinimumByDateRange(String startDate, String endDate);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            AVG(pm2_5) AS media_pm25
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM25AverageByDateRange(String startDate, String endDate);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            MAX(pm2_5) AS max_pm25
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM25MaximumByDateRange(String startDate, String endDate);
    
    @NativeQuery(value = """
        SELECT 
            DATE(measurement_timestamp) AS dia,
            MIN(pm2_5) AS min_pm25
        FROM 
            ESP32_PARTICULAS.PARTICLE_DATA
        WHERE 
            DATE(measurement_timestamp) BETWEEN :startDate AND :endDate
        GROUP BY 
            DATE(measurement_timestamp)
        ORDER BY 
            dia;
        """)
    public List<Object[]> findPM25MinimumByDateRange(String startDate, String endDate);
}
