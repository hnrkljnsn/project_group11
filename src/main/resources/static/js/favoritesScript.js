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
            flightList.innerHTML = ''; // Clear existing list items
            flights.forEach(flight => {
                const listItem = document.createElement('li');
                listItem.className = 'flight-card';

                const flightInfo = `
                    <div class="flight-image">
                        <img src='https://t4.ftcdn.net/jpg/04/38/64/95/360_F_438649569_DsSHTkasH6GqqQXwu7FbRG0OMHstAc2D.jpg' alt="Flight Image">
                    </div>
                    <div class="flight-info" data-header="Airline">${flight.airline}</div>
                    <div class="flight-info" data-header="Departure">${flight.departureCity}</div>
                    <div class="flight-info" data-header="Destination">${flight.returnCity}</div>
                    <div class="flight-info" data-header="Departure Date">${flight.departureDate}</div>
                    <div class="flight-info" data-header="Return Date">${flight.returnDate}</div>
                    <div class="flight-info" data-header="Price">${flight.price}</div>
                `;

                listItem.innerHTML = flightInfo;
                flightList.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
});