async function createAccount() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const data = { username, password };

    try {
        const response = await fetch('/api/create-account', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Account created successfully!');
            window.location.href = 'home.html';
        } else {
            alert('Failed to create account');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while creating the account');
    }
}
