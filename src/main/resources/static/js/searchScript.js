document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query');
    // Assume that an empty query should fetch all flights
    fetch(`/api/search?query=${encodeURIComponent(query || '')}`)  // Handle empty query as an empty string
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
        <div class="flight-info" data-header="Id">${flight.flightId}</div>
        <div class="flight-info" data-header="Name">${flight.flightName}</div>
        <div class="flight-info" data-header="Round-trip">${flight.roundTrip}</div>
        <div class="flight-info" data-header="Departure">${flight.departureDate}</div>
        <div class="flight-info" data-header="Return">${flight.returnDate}</div>
      </li>
    `;
}
