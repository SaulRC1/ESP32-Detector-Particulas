package com.uhu.esp32.data.enums;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum GraphDataMetric
{
    AVERAGE("average"),
    MAXIMUM("maximum"),
    MINIMUM("minimum");
    
    private String metric;
    
    private GraphDataMetric(String metric)
    {
        this.metric = metric;
    }
    
    public String getMetricLiteral()
    {
        return this.metric;
    }
    
    public static GraphDataMetric fromLiteral(String metricLiteral)
    {
        if(metricLiteral.equals(GraphDataMetric.AVERAGE.getMetricLiteral()))
        {
            return GraphDataMetric.AVERAGE;
        }
        
        if(metricLiteral.equals(GraphDataMetric.MAXIMUM.getMetricLiteral()))
        {
            return GraphDataMetric.MAXIMUM;
        }
        
        if(metricLiteral.equals(GraphDataMetric.MINIMUM.getMetricLiteral()))
        {
            return GraphDataMetric.MINIMUM;
        }
        
        return null;
    }
}
