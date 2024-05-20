document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const departureCity = urlParams.get('departureCity');
    const returnCity = urlParams.get('returnCity');
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');

    let query = '';

    if (departureCity && returnCity && departureDate && returnDate) {
        query = `departureCity=${encodeURIComponent(departureCity)}&returnCity=${encodeURIComponent(returnCity)}&departureDate=${encodeURIComponent(departureDate)}&returnDate=${encodeURIComponent(returnDate)}`;
    }

    fetch(`/api/search?${query}`)
        .then(response => response.json())
        .then(displayFlights)
        .catch(handleError);
});

function displayFlights(flights) {
    const resultsContainer = document.querySelector('.flight-result-list ul');
    resultsContainer.innerHTML = "";
    if (flights.length === 0) {
        resultsContainer.innerHTML = "<li>No flights found</li>";
    } else {
        flights.forEach(flight => {
            const newListItem = document.createElement('li');
            newListItem.innerHTML = createFlightInfoString(flight);
            resultsContainer.appendChild(newListItem);
        });
    }
}

function handleError(error) {
    console.error('Error:', error);
    const resultsContainer = document.querySelector('.flight-result-list ul');
    resultsContainer.innerHTML = "<li>Error loading results</li>";
}


function createFlightInfoString(flight) {
    let imageSrc = 'https://t4.ftcdn.net/jpg/04/38/64/95/360_F_438649569_DsSHTkasH6GqqQXwu7FbRG0OMHstAc2D.jpg';
    return `
      <li class="flight-card">
        <div class="flight-image">
          <img src="${imageSrc}" alt="Flight Image" />
        </div>
        <div class="flight-info" data-header="Departure City">${flight.departureCity}</div>
        <div class="flight-info" data-header="Return City">${flight.returnCity}</div>
        <div class="flight-info" data-header="Departure Date">${flight.departureDate}</div>
        <div class="flight-info" data-header="Return Date">${flight.returnDate}</div>
        <div class="flight-info" data-header="Round-trip">${flight.roundTrip}</div>
        <div class="flight-info" data-header="Price">${flight.price}</div>
        <div class="flight-info" data-header="Airline">${flight.airline}</div>
      </li>
    `;
}
