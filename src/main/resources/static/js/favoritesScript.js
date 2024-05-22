document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('userId');
    console.log("User ID:", userId);

    const apiUrl = `/api/${userId}/favorite-flights`;
    console.log("API URL:", apiUrl);

    fetch(apiUrl)
        .then(response => response.json())
        .then(flights => {
            const flightList = document.getElementById('favoriteFlightsList');
            flights.forEach(flight => {
                const listItem = document.createElement('li');
                listItem.textContent = `Airline: ${flight.airline}, Departure: ${flight.departureCity}, Destination: ${flight.returnCity}, Departure Date: ${flight.departureDate}, Return Date: ${flight.returnDate}, Price: ${flight.price}`;
                flightList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
});