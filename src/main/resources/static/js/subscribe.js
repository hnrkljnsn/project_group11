async function subscribeNewsletter() {
    const emailInput = document.getElementById('newsletter-email');
    const email = emailInput.value;

    // Simple email regex for basic validation created with the help of ChatGPT 3.5
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!emailPattern.test(email)) {
        alert('Please enter a valid email address.');
        return;
    }

    const data = { email };

    try {
        const response = await fetch('/api/subscribe-newsletter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Subscribed successfully!');
        } else {
            const errorText = await response.text();
            alert(`Failed to subscribe: ${errorText}`);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while subscribing');
    }
}