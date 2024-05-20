document.addEventListener('DOMContentLoaded', function() {
    console.log("Login script loaded");

    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        console.log("Login form submitted");

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'home.html';
                } else {
                    alert('Login failed');
                }
            })
            .catch(error => console.error('Error:', error));
    });
});