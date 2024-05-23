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
                const listItem = document.createElement('li');
                listItem.textContent = `Flight from ${favoriteFlight.flight.departureCity} to ${favoriteFlight.flight.returnCity} with ${favoriteFlight.flight.airline}`;
                favoritesList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error loading favorite flights: ' + error.message);
        });
});
