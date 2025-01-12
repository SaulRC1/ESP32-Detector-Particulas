package com.uhu.esp32.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Objects;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "ESP32_CONNECTION_STATUS")
public class ESP32ConnectionStatus 
{
    @Transient
    public static final String STATUS_CONNECTED = "CONNECTED";
    
    @Transient
    public static final String STATUS_DISCONNECTED = "DISCONNECTED";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "status")
    private String status;

    public ESP32ConnectionStatus()
    {
    }

    public ESP32ConnectionStatus(int id, String status)
    {
        this.id = id;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.status);
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
        final ESP32ConnectionStatus other = (ESP32ConnectionStatus) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return Objects.equals(this.status, other.status);
    }
}
