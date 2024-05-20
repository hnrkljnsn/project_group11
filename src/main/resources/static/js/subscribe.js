async function subscribeNewsletter() {
    const email = document.getElementById('newsletter-email').value;

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