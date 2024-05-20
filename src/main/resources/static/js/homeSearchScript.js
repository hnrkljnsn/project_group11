document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const departureCity = document.getElementById('departureCity').value;
            const returnCity = document.getElementById('returnCity').value;
            const departureDate = document.getElementById('departureDate').value;
            const returnDate = document.getElementById('returnDate').value;
            const queryString = `departureCity=${encodeURIComponent(departureCity)}&returnCity=${encodeURIComponent(returnCity)}&departureDate=${encodeURIComponent(departureDate)}&returnDate=${encodeURIComponent(returnDate)}`;
            window.location.href = `/search.html?${queryString}`;
        });
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const gridItems = document.querySelectorAll('.grid-item');

    gridItems.forEach(item => {
        item.addEventListener('click', function() {
            const flightId = this.getAttribute('data-flight-id');
            window.location.href = `/search.html?query=${encodeURIComponent(flightId)}`;
        });
    });
});
