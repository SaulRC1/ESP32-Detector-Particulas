package com.uhu.esp32.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

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
    private String latitude;
    
    @Column(name = "longitude")
    private String longitude;
    
    public ParticleData()
    {
        
    }

    public ParticleData(long id, Timestamp measurementTimestamp, Integer pm10, Integer pm2_5, String latitude, String longitude)
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

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.measurementTimestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ParticleData other = (ParticleData) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return Objects.equals(this.measurementTimestamp, other.measurementTimestamp);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ParticleData{");
        sb.append("id=").append(id);
        sb.append(", measurementTimestamp=").append(measurementTimestamp);
        sb.append(", pm10=").append(pm10);
        sb.append(", pm2_5=").append(pm2_5);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
    
}
