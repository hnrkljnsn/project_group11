
document.getElementById('searchForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const query = document.getElementById('searchQuery').value;
    fetch('/api/search?query=' + encodeURIComponent(query))
        .then(response => response.json())
        .then(flights => {
            // for testing
            flights.forEach(({ flightName, departureDate }) => {
                console.log(flightName); // Directly accessing the property via destructuring
            });

            const resultsContainer = document.querySelector('.result-list');
            resultsContainer.innerHTML = ""; //for clearing previous results
            flights.forEach(flight => {
                resultsContainer.innerHTML += '<div>' + flight.flightName + ' - ' + flight.departureDate + '</div>';
            });
        })
        .catch(error => console.error('Error', error));
});