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
                        if (favoritesList) {
                            flights.forEach(favoriteFlight => {
                                const listItem = document.createElement('li');
                                listItem.textContent = `Flight from ${favoriteFlight.flight.departureCity} to ${favoriteFlight.flight.returnCity} with ${favoriteFlight.flight.airline}`;
                                favoritesList.appendChild(listItem);
                            });
                        } else {
                            console.error('Favorites list element not found');
                        }
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
    } else {
        window.location.href = 'login.html';
    }
});
