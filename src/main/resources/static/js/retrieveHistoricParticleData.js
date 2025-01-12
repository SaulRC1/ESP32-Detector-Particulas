var currentPageNumber = 0;
var totalNumberOfPages = 0;
var currentSortingField = "date_descending";

function retrievePaginatedParticleData(page, sortingField)
{
    fetchURL = '/get-paginated-particle-data/' + page + '/' + sortingField;
    fetch(fetchURL)
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

function retrieveParticleTableData(page, sortingField)
{
    fetch("/get-paginated-particle-data-number-of-pages")
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                totalNumberOfPages = data;

                if(page === "last")
                {
                    currentPageNumber = data - 1;
                    pageNumberElement = document.getElementById("paginated_table_page_number_p");
                    pageNumberElement.textContent = totalNumberOfPages + " / " + totalNumberOfPages;
                
                    retrievePaginatedParticleData(currentPageNumber, sortingField);
                }
                else
                {
                    pageNumberElement = document.getElementById("paginated_table_page_number_p");
                    pageNumberElement.textContent = (page + 1) + " / " + totalNumberOfPages;
                
                    retrievePaginatedParticleData(page, sortingField);
                }
            })
            .catch(error => {
                console.error('Error al realizar la petición:', error);
            });
}

document.addEventListener("DOMContentLoaded", function () 
{
    moveToFirstPage();
    
    paginatedTableCombobox = document.getElementById("particle_data_paginated_table_combobox");

    paginatedTableCombobox.addEventListener('change', function () 
    {
        const selectedOption = paginatedTableCombobox.options[paginatedTableCombobox.selectedIndex];

        console.log("Selected option: " + selectedOption.value);
        currentSortingField = selectedOption.value;
        moveToFirstPage();
    });
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

function moveToFirstPage()
{
    currentPageNumber = 0;
    retrieveParticleTableData(0, currentSortingField);
}

function moveToBackwardsPage()
{
    if(currentPageNumber > 0)
    {
        currentPageNumber = currentPageNumber - 1;    
        retrieveParticleTableData(currentPageNumber, currentSortingField);
    }
}

function moveToForwardPage()
{
    fetch("/get-paginated-particle-data-number-of-pages")
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                totalNumberOfPages = data;

                if(currentPageNumber !== (totalNumberOfPages - 1))
                {
                    currentPageNumber = currentPageNumber + 1;
                    pageNumberElement = document.getElementById("paginated_table_page_number_p");
                    pageNumberElement.textContent = (currentPageNumber + 1) + " / " + totalNumberOfPages;

                    retrievePaginatedParticleData(currentPageNumber, currentSortingField);
                }
            })
            .catch(error => {
                console.error('Error al realizar la petición:', error);
            });
}

function moveToLastPage()
{
    retrieveParticleTableData("last", currentSortingField);
}





