document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');

    if (!token || !userId) {
        console.error('Token or User ID not found, redirecting to login.');
        window.location.href = 'login.html';
        return;
    }
    console.log('Token and User ID found:', token, userId);

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
            if (!Array.isArray(flights)) {
                throw new Error('Invalid response format');
            }
            const favoritesList = document.getElementById('favoritesList');
            if (!favoritesList) {
                console.error('Favorites list element not found');
                return;
            }
            console.log('Favorites list element found:', favoritesList);

            flights.forEach(favoriteFlight => {
                const flight = favoriteFlight.flight;
                const listItem = document.createElement('li');
                listItem.classList.add('flight-card');

                let imageSrc = 'https://t4.ftcdn.net/jpg/04/38/64/95/360_F_438649569_DsSHTkasH6GqqQXwu7FbRG0OMHstAc2D.jpg';
                let flightInfo = `
                    <div class="flight-image"><img src="${imageSrc}" alt="Flight Image" /></div>
                    <div class="flight-info" data-header="Airline">${flight.airline}</div>
                    <div class="flight-info" data-header="Departure">${flight.departureCity}</div>
                    <div class="flight-info" data-header="Destination">${flight.returnCity}</div>
                    <div class="flight-info" data-header="Departure Date">${flight.departureDate}</div>
                    <div class="flight-info" data-header="Return Date">${flight.returnDate}</div>
                    <div class="flight-info" data-header="Price">${flight.price}</div>
                    <div class="flight-card">
                    </div>
                `;

                listItem.innerHTML = flightInfo;
                favoritesList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error loading favorite flights: ' + error.message);
        });
});
