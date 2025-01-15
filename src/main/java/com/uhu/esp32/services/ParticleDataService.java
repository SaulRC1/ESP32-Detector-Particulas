package com.uhu.esp32.services;

import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.data.ParticleGraphDataByDate;
import com.uhu.esp32.data.ParticleGraphDataByHourFromDay;
import com.uhu.esp32.repositories.ParticleDataRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    
    @Transactional(readOnly = false)
    public List<ParticleData> findMostRecentData()
    {
        return this.particleDataRepository.findTop50ByOrderByMeasurementTimestampDesc();
    }
    
    @Transactional(readOnly = false)
    public List<ParticleData> findPaginatedData(Pageable pageable)
    {
        return this.particleDataRepository.findAll(pageable).getContent();
    }
    
    @Transactional(readOnly = false)
    public int findNumberOfPagesForPaginatedData(Pageable pageable)
    {
        return this.particleDataRepository.findAll(pageable).getTotalPages();
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Float>> findPM10AverageByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Float>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm10AverageList = this.particleDataRepository.findPM10AverageByHourFromDay(date);
        
        for (Object[] pm10Average : pm10AverageList)
        {
            String[] split = ((String) pm10Average[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Float> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            if(pm10Average[1] instanceof BigDecimal)
            {
                BigDecimal value = (BigDecimal) pm10Average[1];
                particleGraphDataByHourFromDay.setValue(value.floatValue());
            }
            else
            {
                particleGraphDataByHourFromDay.setValue((Float) pm10Average[1]);
            }
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Integer>> findPM10MaximumByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Integer>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm10MaximumList = this.particleDataRepository.findPM10MaximumByHourFromDay(date);
        
        for (Object[] pm10Maximum : pm10MaximumList)
        {
            String[] split = ((String) pm10Maximum[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Integer> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            particleGraphDataByHourFromDay.setValue((Integer) pm10Maximum[1]);
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Integer>> findPM10MinimumByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Integer>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm10MinimumList = this.particleDataRepository.findPM10MinimumByHourFromDay(date);
        
        for (Object[] pm10Minimum : pm10MinimumList)
        {
            String[] split = ((String) pm10Minimum[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Integer> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            particleGraphDataByHourFromDay.setValue((Integer) pm10Minimum[1]);
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Float>> findPM25AverageByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Float>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm25AverageList = this.particleDataRepository.findPM25AverageByHourFromDay(date);
        
        for (Object[] pm25Average : pm25AverageList)
        {
            String[] split = ((String) pm25Average[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Float> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            if(pm25Average[1] instanceof BigDecimal)
            {
                BigDecimal value = (BigDecimal) pm25Average[1];
                particleGraphDataByHourFromDay.setValue(value.floatValue());
            }
            else
            {
                particleGraphDataByHourFromDay.setValue((Float) pm25Average[1]);
            }
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Integer>> findPM25MaximumByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Integer>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm25MaximumList = this.particleDataRepository.findPM25MaximumByHourFromDay(date);
        
        for (Object[] pm25Maximum : pm25MaximumList)
        {
            String[] split = ((String) pm25Maximum[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Integer> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            particleGraphDataByHourFromDay.setValue((Integer) pm25Maximum[1]);
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByHourFromDay<Integer>> findPM25MinimumByHourFromDay(String date)
    {
        List<ParticleGraphDataByHourFromDay<Integer>> particleGraphDataByHourFromDayList = new ArrayList<>();
        List<Object[]> pm25MinimumList = this.particleDataRepository.findPM25MinimumByHourFromDay(date);
        
        for (Object[] pm25Minimum : pm25MinimumList)
        {
            String[] split = ((String) pm25Minimum[0]).split(" ");
            
            ParticleGraphDataByHourFromDay<Integer> particleGraphDataByHourFromDay = new ParticleGraphDataByHourFromDay<>();
            particleGraphDataByHourFromDay.setDate(LocalDate.parse(split[0]));
            particleGraphDataByHourFromDay.setHour(split[1]);
            
            particleGraphDataByHourFromDay.setValue((Integer) pm25Minimum[1]);
            
            particleGraphDataByHourFromDayList.add(particleGraphDataByHourFromDay);
        }
        
        return particleGraphDataByHourFromDayList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Float>> findPM10AverageByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Float>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm10AverageList = this.particleDataRepository
                .findPM10AverageByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm10Average : pm10AverageList)
        {       
            ParticleGraphDataByDate<Float> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm10Average[0]).toLocalDate());
            
            if(pm10Average[1] instanceof BigDecimal)
            {
                BigDecimal value = (BigDecimal) pm10Average[1];
                particleGraphDataByDate.setValue(value.floatValue());
            }
            else
            {
                particleGraphDataByDate.setValue((Float) pm10Average[1]);
            }
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Integer>> findPM10MaximumByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Integer>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm10MaximumList = this.particleDataRepository
                .findPM10MaximumByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm10Maximum : pm10MaximumList)
        {       
            ParticleGraphDataByDate<Integer> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm10Maximum[0]).toLocalDate());
            
            particleGraphDataByDate.setValue((Integer) pm10Maximum[1]);
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Integer>> findPM10MinimumByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Integer>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm10MinimumList = this.particleDataRepository
                .findPM10MinimumByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm10minimum : pm10MinimumList)
        {       
            ParticleGraphDataByDate<Integer> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm10minimum[0]).toLocalDate());

            particleGraphDataByDate.setValue((Integer) pm10minimum[1]);
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Float>> findPM25AverageByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Float>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm25AverageList = this.particleDataRepository
                .findPM25AverageByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm25Average : pm25AverageList)
        {       
            ParticleGraphDataByDate<Float> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm25Average[0]).toLocalDate());
            
            if(pm25Average[1] instanceof BigDecimal)
            {
                BigDecimal value = (BigDecimal) pm25Average[1];
                particleGraphDataByDate.setValue(value.floatValue());
            }
            else
            {
                particleGraphDataByDate.setValue((Float) pm25Average[1]);
            }
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Integer>> findPM25MaximumByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Integer>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm25MaximumList = this.particleDataRepository
                .findPM25MaximumByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm25Maximum : pm25MaximumList)
        {       
            ParticleGraphDataByDate<Integer> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm25Maximum[0]).toLocalDate());
            
            particleGraphDataByDate.setValue((Integer) pm25Maximum[1]);
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
    
    @Transactional(readOnly = false)
    public List<ParticleGraphDataByDate<Integer>> findPM25MinimumByDateRange(LocalDate startDate, LocalDate endDate)
    {
        List<ParticleGraphDataByDate<Integer>> particleGraphDataByDateList = new ArrayList<>();
        List<Object[]> pm25MinimumList = this.particleDataRepository
                .findPM25MinimumByDateRange(startDate.format(DateTimeFormatter.ISO_DATE), 
                        endDate.format(DateTimeFormatter.ISO_DATE));
        
        for (Object[] pm25minimum : pm25MinimumList)
        {       
            ParticleGraphDataByDate<Integer> particleGraphDataByDate = new ParticleGraphDataByDate<>();
            particleGraphDataByDate.setDate(((java.sql.Date) pm25minimum[0]).toLocalDate());

            particleGraphDataByDate.setValue((Integer) pm25minimum[1]);
            
            particleGraphDataByDateList.add(particleGraphDataByDate);
        }
        
        return particleGraphDataByDateList;
    }
}
