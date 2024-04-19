function createFlightInfoString(flight) {
    return Object.keys(flight).map(key => flight[key]).join(' - ');
}

document.getElementById('searchForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const query = document.getElementById('searchQuery').value;
    fetch('/api/search?query=' + encodeURIComponent(query))
        .then(response => response.json())
        .then(flights => {
            const resultsContainer = document.querySelector('.flight-result-list ul');
            resultsContainer.innerHTML = ""; // Clear previous results
            flights.forEach(flight => {
                const newListItem = document.createElement('li');
                newListItem.innerHTML = createFlightInfoString(flight);
                resultsContainer.appendChild(newListItem);
            });
        })
        .catch(error => console.error('Error', error));
});