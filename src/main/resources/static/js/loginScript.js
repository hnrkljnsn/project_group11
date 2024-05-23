document.addEventListener('DOMContentLoaded', function() {
    console.log("Login script loaded");

    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
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
                    if (!response.ok) {
                        throw new Error('Network response was not ok: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.status === 'Login successful') {
                        localStorage.setItem('token', data.token);
                        localStorage.setItem('userId', data.userId);
                        window.location.href = `favorites.html`;
                    } else {
                        alert('Login failed: ' + data.status);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Login failed: ' + error.message);
                });
        });
    } else {
        console.error('Login form not found');
    }
});
