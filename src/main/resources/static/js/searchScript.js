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
    return Object.keys(flight)
        .map(key => `<span class="flight-info">${flight[key]}</span>`)
        .join('');
}
