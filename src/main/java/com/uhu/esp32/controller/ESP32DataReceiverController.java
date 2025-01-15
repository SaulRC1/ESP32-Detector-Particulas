package com.uhu.esp32.controller;

import com.uhu.esp32.data.ESP32ConnectionStatus;
import com.uhu.esp32.data.ParticleData;
import com.uhu.esp32.data.ParticleGraphDataByDate;
import com.uhu.esp32.data.ParticleGraphDataByHourFromDay;
import com.uhu.esp32.data.enums.GraphDataMetric;
import com.uhu.esp32.services.ESP32ConnectionStatusService;
import com.uhu.esp32.services.ParticleDataService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@RestController
public class ESP32DataReceiverController
{

    @Autowired
    private ParticleDataService particleDataService;

    @Autowired
    private ESP32ConnectionStatusService esp32ConnectionStatusService;

    @PostMapping("/save-particle-data")
    public String saveParticleData(@RequestBody ParticleData particleData)
    {
        particleDataService.saveParticleData(particleData);
        return "Particle data saved";
    }

    @GetMapping("/get-recent-particle-data")
    public List<ParticleData> getMostRecentParticleData()
    {
        return particleDataService.findMostRecentData();
    }

    @GetMapping("/get-paginated-particle-data/{page}/{sortingField}")
    public List<ParticleData> getPaginatedParticleData(@PathVariable String page, @PathVariable String sortingField)
    {
        int numericPage = Integer.parseInt(page);
        Pageable pageable;

        switch (sortingField)
        {
            case "date_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").descending());
                return particleDataService.findPaginatedData(pageable);
            case "date_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").ascending());
                return particleDataService.findPaginatedData(pageable);
            case "pm10_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm10").descending());
                return particleDataService.findPaginatedData(pageable);
            case "pm10_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm10").ascending());
                return particleDataService.findPaginatedData(pageable);
            case "pm2_5_descending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm25").descending());
                return particleDataService.findPaginatedData(pageable);
            case "pm2_5_ascending":
                pageable = PageRequest.of(numericPage, 30, Sort.by("pm25").ascending());
                return particleDataService.findPaginatedData(pageable);
            default:
                pageable = PageRequest.of(numericPage, 30, Sort.by("measurementTimestamp").descending());
                return particleDataService.findPaginatedData(pageable);
        }
    }

    @GetMapping("/get-paginated-particle-data-number-of-pages")
    public int getPaginatedParticleData()
    {
        Pageable pageable = PageRequest.of(0, 30, Sort.by("measurementTimestamp").descending());

        return particleDataService.findNumberOfPagesForPaginatedData(pageable);
    }

    @PostMapping("/set-esp32-status")
    public String setEsp32Status(@RequestBody ESP32ConnectionStatus esp32ConnectionStatus)
    {
        this.esp32ConnectionStatusService.setESP32ConnectionStatus(esp32ConnectionStatus.getStatus());
        return "ESP32 status has been set to '" + esp32ConnectionStatus.getStatus() + "'";
    }

    @GetMapping("/get-esp32-graph-data")
    public List<?> getESP32GraphDataDay(@RequestParam("start_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, @RequestParam("metric") String metric,
            @RequestParam("field") String field)
    {
        GraphDataMetric dataMetric = GraphDataMetric.fromLiteral(metric);

        if (startDate.isEqual(endDate))
        {
            if (field.equals("pm10"))
            {
                switch (dataMetric)
                {
                    case AVERAGE:
                        List<ParticleGraphDataByHourFromDay<Float>> pm10AverageList 
                                = this.particleDataService.findPM10AverageByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm10AverageList;
                    case MAXIMUM:
                        List<ParticleGraphDataByHourFromDay<Integer>> pm10MaximumList 
                                = this.particleDataService.findPM10MaximumByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm10MaximumList;
                    case MINIMUM:
                        List<ParticleGraphDataByHourFromDay<Integer>> pm10MinimumList 
                                = this.particleDataService.findPM10MinimumByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm10MinimumList;
                    default:
                        return null;
                }
            } 
            else if (field.equals("pm25"))
            {
                switch (dataMetric)
                {
                    case AVERAGE:
                        List<ParticleGraphDataByHourFromDay<Float>> pm25AverageList 
                                = this.particleDataService.findPM25AverageByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm25AverageList;
                    case MAXIMUM:
                        List<ParticleGraphDataByHourFromDay<Integer>> pm25MaximumList 
                                = this.particleDataService.findPM25MaximumByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm25MaximumList;
                    case MINIMUM:
                        List<ParticleGraphDataByHourFromDay<Integer>> pm25MinimumList 
                                = this.particleDataService.findPM25MinimumByHourFromDay(startDate.format(DateTimeFormatter.ISO_DATE));
                        return pm25MinimumList;
                    default:
                        return null;
                }
            }
        } 
        else
        {
            if (field.equals("pm10"))
            {
                switch (dataMetric)
                {
                    case AVERAGE:
                        List<ParticleGraphDataByDate<Float>> pm10AverageList 
                                = this.particleDataService.findPM10AverageByDateRange(startDate, endDate);
                        return pm10AverageList;
                    case MAXIMUM:
                        List<ParticleGraphDataByDate<Integer>> pm10MaximumList 
                                = this.particleDataService.findPM10MaximumByDateRange(startDate, endDate);
                        return pm10MaximumList;
                    case MINIMUM:
                        List<ParticleGraphDataByDate<Integer>> pm10MinimumList 
                                = this.particleDataService.findPM10MinimumByDateRange(startDate, endDate);
                        return pm10MinimumList;
                    default:
                        return null;
                }
            } 
            else if (field.equals("pm25"))
            {
                switch (dataMetric)
                {
                    case AVERAGE:
                        List<ParticleGraphDataByDate<Float>> pm25AverageList 
                                = this.particleDataService.findPM25AverageByDateRange(startDate, endDate);
                        return pm25AverageList;
                    case MAXIMUM:
                        List<ParticleGraphDataByDate<Integer>> pm25MaximumList 
                                = this.particleDataService.findPM25MaximumByDateRange(startDate, endDate);
                        return pm25MaximumList;
                    case MINIMUM:
                        List<ParticleGraphDataByDate<Integer>> pm25MinimumList 
                                = this.particleDataService.findPM25MinimumByDateRange(startDate, endDate);
                        return pm25MinimumList;
                    default:
                        return null;
                }
            }
        }

        return null;
    }
}
