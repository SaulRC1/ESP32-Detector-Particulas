function representBarGraph(data, field)
{
    let container = document.getElementById("data_graph_viewport");
    
    //clean previous content
    container.innerHTML = '';

    // Declare the chart dimensions and margins.
    const width = container.offsetWidth - 50;
    const height = container.offsetHeight - 50;
    const marginTop = 30;
    const marginRight = 0;
    const marginBottom = 30;
    const marginLeft = 40;

// Declare the x (horizontal position) scale.
    const x = d3.scaleBand()
            .domain(data.map((d) => d.hour)) // Mantener el orden original
            .range([marginLeft, width - marginRight])
            .padding(0.1);

// Declare the y (vertical position) scale.
    const y = d3.scaleLinear()
            .domain([0, d3.max(data, (d) => d.value)])
            .range([height - marginBottom, marginTop]);

// Create the SVG container.
    const svg = d3.create("svg")
            .attr("width", width)
            .attr("height", height)
            .attr("viewBox", [0, 0, width, height])
            .attr("style", "max-width: 100%; height: auto;");

// Add a rect for each bar.
    svg.append("g")
            .attr("fill", "steelblue")
            .selectAll()
            .data(data)
            .join("rect")
            .attr("x", (d) => x(d.hour))
            .attr("y", (d) => y(d.value))
            .attr("height", (d) => y(0) - y(d.value))
            .attr("width", x.bandwidth());

// Add the x-axis and label.
    svg.append("g")
            .attr("transform", `translate(0,${height - marginBottom})`)
            .call(d3.axisBottom(x).tickSizeOuter(0));

// Add the y-axis and label, and remove the domain line.
    svg.append("g")
            .attr("transform", `translate(${marginLeft},0)`)
            .call(d3.axisLeft(y).tickFormat((y) => (y).toFixed()))
            .call(g => g.select(".domain").remove())
            .call(g => g.append("text") 
                        .attr("x", -marginLeft)
                        .attr("y", 20)
                        .attr("fill", "currentColor")
                        .attr("text-anchor", "start")
                        .text(field));

// Append the SVG element.
    container.append(svg.node());
}

function representBarGraphByDateRange(data, field)
{
    let container = document.getElementById("data_graph_viewport");
    
    //clean previous content
    container.innerHTML = '';

    // Declare the chart dimensions and margins.
    const width = container.offsetWidth - 50;
    const height = container.offsetHeight - 50;
    const marginTop = 30;
    const marginRight = 0;
    const marginBottom = 30;
    const marginLeft = 40;

// Declare the x (horizontal position) scale.
    const x = d3.scaleBand()
            .domain(data.map((d) => d.date)) // Mantener el orden original
            .range([marginLeft, width - marginRight])
            .padding(0.1);

// Declare the y (vertical position) scale.
    const y = d3.scaleLinear()
            .domain([0, d3.max(data, (d) => d.value)])
            .range([height - marginBottom, marginTop]);

// Create the SVG container.
    const svg = d3.create("svg")
            .attr("width", width)
            .attr("height", height)
            .attr("viewBox", [0, 0, width, height])
            .attr("style", "max-width: 100%; height: auto;");

// Add a rect for each bar.
    svg.append("g")
            .attr("fill", "steelblue")
            .selectAll()
            .data(data)
            .join("rect")
            .attr("x", (d) => x(d.date))
            .attr("y", (d) => y(d.value))
            .attr("height", (d) => y(0) - y(d.value))
            .attr("width", x.bandwidth());

// Add the x-axis and label.
    svg.append("g")
            .attr("transform", `translate(0,${height - marginBottom})`)
            .call(d3.axisBottom(x).tickSizeOuter(0));

// Add the y-axis and label, and remove the domain line.
    svg.append("g")
            .attr("transform", `translate(${marginLeft},0)`)
            .call(d3.axisLeft(y).tickFormat((y) => (y).toFixed()))
            .call(g => g.select(".domain").remove())
            .call(g => g.append("text") 
                        .attr("x", -marginLeft)
                        .attr("y", 20)
                        .attr("fill", "currentColor")
                        .attr("text-anchor", "start")
                        .text(field));

// Append the SVG element.
    container.append(svg.node());
}

document.addEventListener("DOMContentLoaded", function () 
{
    const todayISODate = new Date().toISOString().split('T')[0];
    
    console.log("Today is: " + todayISODate);
    
    fetchURL = 'get-esp32-graph-data?start_date=' + todayISODate + '&end_date='
    + todayISODate + '&metric=average&field=pm10';
    fetch(fetchURL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos:', data);

            representBarGraph(data, "PM10 (µg/m3)");
        })
        .catch(error => {
            console.error('Error al realizar la petición:', error);
        });
});

function applyFilter()
{
    let startDateInput = document.getElementById("start-date-input");
    let endDateInput = document.getElementById("end-date-input");
    let metricCombobox = document.getElementById("metric-combobox");
    let fieldCombobox = document.getElementById("field-combobox");
    
    console.log("Start date: " + startDateInput.value);
    console.log("End date: " + endDateInput.value);
    
    const selectedMetricOption = metricCombobox.options[metricCombobox.selectedIndex];
    console.log("Selected metric: " + selectedMetricOption.value);
    
    const selectedFieldOption = fieldCombobox.options[fieldCombobox.selectedIndex];
    console.log("Selected field: " + selectedFieldOption.value);
    
    fetchURL = 'get-esp32-graph-data?start_date=' + startDateInput.value + '&end_date='
    + endDateInput.value + '&metric=' + selectedMetricOption.value + '&field=' + selectedFieldOption.value;
    
    const startDate = new Date(startDateInput.value);
    const endDate = new Date(endDateInput.value);

    if (startDate.getTime() === endDate.getTime())
    {
        fetch(fetchURL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos:', data);

            if(selectedFieldOption.value === "pm10")
            {
                representBarGraph(data, "PM10 (µg/m3)");
            }
            else if(selectedFieldOption.value === "pm25")
            {
                representBarGraph(data, "PM2.5 (µg/m3)");
            }
        })
        .catch(error => {
            console.error('Error al realizar la petición:', error);
        });
    }
    else
    {
        fetch(fetchURL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos:', data);

            if(selectedFieldOption.value === "pm10")
            {
                representBarGraphByDateRange(data, "PM10 (µg/m3)");
            }
            else if(selectedFieldOption.value === "pm25")
            {
                representBarGraphByDateRange(data, "PM2.5 (µg/m3)");
            }
        })
        .catch(error => {
            console.error('Error al realizar la petición:', error);
        });
    }
}
