package com.uhu.esp32.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "PARTICLE_DATA")
public class ParticleData 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "measurement_timestamp")
    private Timestamp measurementTimestamp;
    
    @Column(name = "PM10")
    private Integer pm10;
    
    @Column(name = "PM2_5")
    private Integer pm2_5;
    
    @Column(name = "latitude")
    private Float latitude;
    
    @Column(name = "longitude")
    private Float longitude;

    public ParticleData(long id, Timestamp measurementTimestamp, Integer pm10, Integer pm2_5, Float latitude, Float longitude)
    {
        this.id = id;
        this.measurementTimestamp = measurementTimestamp;
        this.pm10 = pm10;
        this.pm2_5 = pm2_5;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Timestamp getMeasurementTimestamp()
    {
        return measurementTimestamp;
    }

    public void setMeasurementTimestamp(Timestamp measurementTimestamp)
    {
        this.measurementTimestamp = measurementTimestamp;
    }

    public Integer getPm10()
    {
        return pm10;
    }

    public void setPm10(Integer pm10)
    {
        this.pm10 = pm10;
    }

    public Integer getPm2_5()
    {
        return pm2_5;
    }

    public void setPm2_5(Integer pm2_5)
    {
        this.pm2_5 = pm2_5;
    }

    public Float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Float latitude)
    {
        this.latitude = latitude;
    }

    public Float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Float longitude)
    {
        this.longitude = longitude;
    }
}
