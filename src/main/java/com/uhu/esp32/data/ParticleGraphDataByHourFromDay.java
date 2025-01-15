package com.uhu.esp32.data;

import java.time.LocalDate;

/**
 *
 * @author Saúl Rodríguez Naranjo
 * @param <T>
 */
public class ParticleGraphDataByHourFromDay<T> 
{
    private LocalDate date;
    private String hour;
    private T value;

    public ParticleGraphDataByHourFromDay()
    {
    }

    public ParticleGraphDataByHourFromDay(LocalDate date, String hour, T value)
    {
        this.date = date;
        this.hour = hour;
        this.value = value;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getHour()
    {
        return hour;
    }

    public void setHour(String hour)
    {
        this.hour = hour;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
