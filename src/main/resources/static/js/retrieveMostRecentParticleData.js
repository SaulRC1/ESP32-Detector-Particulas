function retrieveMostRecentParticleData()
{
    fetch('/get-recent-particle-data')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('Datos recibidos:', data);
                
                particleDataTable = document.getElementById("particle_data_table");
                particleDataTableBody = particleDataTable.getElementsByTagName("tbody")[0];
                
                //We first delete all child nodes of particle data table
                particleDataTableBody.innerHTML = '';
                
                for (let i = 0; i < data.length; i++) 
                {
                    tableRow = createTableRow(data[i]);
                    
                    if(i % 2 !== 0)
                    {
                        tableRow.classList.add("particle_data_table_odd_row");
                    }
                    
                    particleDataTableBody.appendChild(tableRow);
                }
            })
            .catch(error => {
                console.error('Error al realizar la petición:', error);
            });
}

document.addEventListener("DOMContentLoaded", function () {
    retrieveMostRecentParticleData();
    setInterval(retrieveMostRecentParticleData, 10000);
});

function createTableRow(data)
{
    row = document.createElement("tr");
    
    id = document.createElement("td");
    id.textContent = data.id;
    
    timestamp = document.createElement("td");
    timestamp.textContent = data.measurementTimestamp;
    
    pm10 = document.createElement("td");
    pm10.textContent = data.pm10;
    
    pm2_5 = document.createElement("td");
    pm2_5.textContent = data.pm25;
    
    latitude = document.createElement("td");
    latitude.textContent = data.latitude;
    
    longitude = document.createElement("td");
    longitude.textContent = data.longitude;
    
    row.appendChild(id);
    row.appendChild(timestamp);
    row.appendChild(pm10);
    row.appendChild(pm2_5);
    row.appendChild(latitude);
    row.appendChild(longitude);
    
    return row;
}





