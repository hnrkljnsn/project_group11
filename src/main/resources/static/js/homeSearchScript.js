document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const query = document.getElementById('searchQuery').value;
            window.location.href = `/search.html?query=${encodeURIComponent(query)}`;
        });
    }
});
