function createFlightInfoString(flight) {
    return Object.keys(flight).map(key => flight[key]).join(' - ');
}

document.getElementById('searchForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const query = document.getElementById('searchQuery').value;
    fetch('/api/search?query=' + encodeURIComponent(query))
        .then(response => response.json())
        .then(flights => {
            const resultsContainer = document.querySelector('.result-list');
            resultsContainer.innerHTML = ""; // Clear previous results
            flights.forEach(flight => {
                resultsContainer.innerHTML += '<div>' + createFlightInfoString(flight) + '</div>';
            });
        })
        .catch(error => console.error('Error', error));
});