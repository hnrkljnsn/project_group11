document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');
    if (!token) {
        console.error('Token not found, redirecting to login.');
        window.location.href = 'login.html';
        return;
    }
    console.log('Token found:', token);

    const userId = new URLSearchParams(window.location.search).get('userId');
    if (!userId) {
        console.error('User ID not found in URL, redirecting to login.');
        window.location.href = 'login.html';
        return;
    }
    console.log('User ID found:', userId);

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
