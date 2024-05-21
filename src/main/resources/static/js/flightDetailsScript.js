document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const airline = urlParams.get('airline');
    const departureCity = urlParams.get('departureCity');
    const returnCity = urlParams.get('returnCity');
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');
    const price = urlParams.get('price');

    const flightDetails = document.getElementById('flightDetails');
    let imageSrc = 'https://t4.ftcdn.net/jpg/04/38/64/95/360_F_438649569_DsSHTkasH6GqqQXwu7FbRG0OMHstAc2D.jpg';
    const flightInfo = `

        <div class ="flight-card">
        <div class="flight-image"><img src="${imageSrc}" alt="Flight Image" /></div>
        <div class="flight-info" data-header="Airline">${airline}</div>
        <div class="flight-info" data-header="Departure">${departureCity}</div>
        <div class="flight-info" data-header="Destination">${returnCity}</div>
        <div class="flight-info" data-header="Departure Date">${departureDate}</div>
        <div class="flight-info" data-header="Return Date">${returnDate}</div>
        <div class="flight-info" data-header="Price">${price}</div>
        <br>
        </div>
        <div class ="flight-card">
        <H3>This flight is provided by ${airline}.</H3>
        </div>
        <div class="flight-card">
        <button style=" width: 100px;
                        padding: 10px;
                        background-color: #007bff;
                        color: #fff;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;" 
                        >Book flight</button>
</div>
        
    `;

    flightDetails.innerHTML = flightInfo;
});
