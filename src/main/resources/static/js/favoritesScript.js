document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');
    if (token) {
        const userId = new URLSearchParams(window.location.search).get('userId');
        if (userId) {
            fetch(`/api/${userId}/favorite-flights`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(response => {
                    if (response.status === 403) {
                        window.location.href = '403.html';
                    } else if (!response.ok) {
                        throw new Error('Network response was not ok: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(flights => {
                    if (Array.isArray(flights)) {
                        const favoritesList = document.getElementById('favoritesList');
                        flights.forEach(favoriteFlight => {
                            const listItem = document.createElement('li');
                            listItem.textContent = `Flight from ${favoriteFlight.flight.departureCity} to ${favoriteFlight.flight.returnCity} with ${favoriteFlight.flight.airline}`;
                            favoritesList.appendChild(listItem);
                        });
                    } else {
                        throw new Error('Invalid response format');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error loading favorite flights: ' + error.message);
                });
        } else {
            window.location.href = 'login.html';
        }
    }
});

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