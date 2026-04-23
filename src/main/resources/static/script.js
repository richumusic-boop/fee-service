document.addEventListener('DOMContentLoaded', () => {
    const chatWindow = document.getElementById('chat-window');
    const userInput = document.getElementById('user-input');
    const sendBtn = document.getElementById('send-btn');
    const actionBtns = document.querySelectorAll('.action-btn');

    function addMessage(text, type) {
        const msgDiv = document.createElement('div');
        msgDiv.className = `message ${type}`;
        
        const contentDiv = document.createElement('div');
        contentDiv.className = 'msg-content';
        contentDiv.innerHTML = text.replace(/\n/g, '<br>');
        
        msgDiv.appendChild(contentDiv);
        chatWindow.appendChild(msgDiv);
        scrollToBottom();
    }

    function addTypingIndicator() {
        const msgDiv = document.createElement('div');
        msgDiv.className = 'message system typing';
        msgDiv.id = 'typing-indicator';
        
        const contentDiv = document.createElement('div');
        contentDiv.className = 'msg-content typing-indicator';
        contentDiv.innerHTML = `
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
        `;
        
        msgDiv.appendChild(contentDiv);
        chatWindow.appendChild(msgDiv);
        scrollToBottom();
    }

    function removeTypingIndicator() {
        const indicator = document.getElementById('typing-indicator');
        if (indicator) {
            indicator.remove();
        }
    }

    function scrollToBottom() {
        chatWindow.scrollTop = chatWindow.scrollHeight;
    }

    async function sendMessage(message) {
        if (!message.trim()) return;

        addMessage(message, 'user');
        userInput.value = '';

        addTypingIndicator();

        try {
            const response = await fetch('/api/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ message: message })
            });

            const data = await response.json();
            
            setTimeout(() => {
                removeTypingIndicator();
                addMessage(data.reply, 'system');
            }, 600 + Math.random() * 500);

        } catch (error) {
            console.error('Error:', error);
            removeTypingIndicator();
            addMessage("Sorry, I'm having trouble connecting to the server.", 'system');
        }
    }

    sendBtn.addEventListener('click', () => {
        sendMessage(userInput.value);
    });

    userInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            sendMessage(userInput.value);
        }
    });

    actionBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const query = this.getAttribute('data-query');
            sendMessage(query);
        });
    });
});
