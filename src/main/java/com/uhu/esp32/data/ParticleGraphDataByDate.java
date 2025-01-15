package com.uhu.esp32.data;

import java.time.LocalDate;

/**
 *
 * @author Saúl Rodríguez Naranjo
 * @param <T>
 */
public class ParticleGraphDataByDate<T>
{
    private LocalDate date;
    private T value;

    public ParticleGraphDataByDate()
    {
    }

    public ParticleGraphDataByDate(LocalDate date, T value)
    {
        this.date = date;
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

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
