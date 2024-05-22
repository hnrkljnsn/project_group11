// In loginScript.js
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
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                if (data.status === 'Login successful') {
                    localStorage.setItem('token', data.token);
                    window.location.href = `account.html?userId=${data.userId}`;
                } else {
                    alert('Login failed: ' + data.status);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Login failed: ' + error.message);
            });
    });

    const token = localStorage.getItem('token');
    if (token) {
        const userId = new URLSearchParams(window.location.search).get('userId');
        if (userId) {
            fetch(`/account.html?userId=${userId}`, {
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
                    return response.text();
                })
                .then(html => {
                    document.open();
                    document.write(html);
                    document.close();
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error loading account page: ' + error.message);
                });
        } else {
            window.location.href = 'login.html';
        }
    }
});
