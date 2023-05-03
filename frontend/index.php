<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Message Board</title>
</head>
<body>
    <h1>Message Board</h1>

    <div id="message-list">
        <!-- Fetch and display messages using JavaScript -->
    </div>

    <form id="message-form">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="content">Message:</label>
        <textarea id="content" name="content" required></textarea>

        <button type="submit">Submit</button>
    </form>

    <script>
        // Fetch messages from the Java API and display them
function fetchMessages() {
    fetch('/messages')
        .then(response => response.json())
        .then(messages => {
            let messageList = document.getElementById('message-list');
            messageList.innerHTML = '';

            messages.forEach(message => {
                let messageDiv = document.createElement('div');
                messageDiv.innerHTML = `
                    <h3>${message.username}</h3>
                    <p>${message.content}</p>
                    <p>${message.timestamp}</p>
                `;

                messageList.appendChild(messageDiv);
            });
        });
}

// Handle form submission and send a new message to the Java API
document.getElementById('message-form').addEventListener('submit', (event) => {
    event.preventDefault();

    let username = document.getElementById('username').value;
    let content = document.getElementById('content').value;

    fetch('/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: username, content: content})
    }).then(() => {
        fetchMessages();
        document.getElementById('message-form').reset();
    });
});

// Fetch messages when the page loads
fetchMessages();

    </script>
</body>
</html>
