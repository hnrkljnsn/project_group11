document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const flightId = urlParams.get('flightId');
    const airline = urlParams.get('airline');
    const departureCity = urlParams.get('departureCity');
    const returnCity = urlParams.get('returnCity');
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');
    const price = urlParams.get('price');

    const flightDetails = document.getElementById('flightDetails');
    if (flightDetails) {
        let imageSrc = 'https://t4.ftcdn.net/jpg/04/38/64/95/360_F_438649569_DsSHTkasH6GqqQXwu7FbRG0OMHstAc2D.jpg';
        const flightInfo = `
            <div class="flight-card">
                <div class="flight-image"><img src="${imageSrc}" alt="Flight Image" /></div>
                <div class="flight-info" data-header="Airline">${airline}</div>
                <div class="flight-info" data-header="Departure">${departureCity}</div>
                <div class="flight-info" data-header="Destination">${returnCity}</div>
                <div class="flight-info" data-header="Departure Date">${departureDate}</div>
                <div class="flight-info" data-header="Return Date">${returnDate}</div>
                <div class="flight-info" data-header="Price">${price}</div>
                <button id="deleteFlightButton" 
                    style="width: 100px; padding: 10px; background-color: darkred; color: #fff; border: none; border-radius: 5px; cursor: pointer;">
                    Delete
                </button>
                <br>
            </div>
            <div class="flight-card">
                <h3>This flight is provided by ${airline}.</h3>
            </div>
            <div class="flight-card">
                <button style="width: 100px; padding: 10px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer;">
                    Book flight
                </button>
            </div>
        `;

        flightDetails.innerHTML = flightInfo;

        const deleteButton = document.getElementById('deleteFlightButton');
        if (deleteButton) {
            deleteButton.addEventListener('click', function() {
                if (confirm('Are you sure you want to delete this flight?')) {
                    fetch(`/api/flights/${flightId}`, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                        .then(response => {
                            if (response.ok) {
                                alert('Flight deleted successfully.');
                                window.location.href = 'search.html';
                            } else if (response.status === 404) {
                                alert('Flight not found.');
                            } else {
                                alert('Failed to delete flight.');
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            alert('Failed to delete flight.');
                        });
                }
            });
        } else {
            console.error('Delete button not found');
        }
    } else {
        console.error('Flight details element not found');
    }
});
