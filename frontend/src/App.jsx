import { useState, useRef, useEffect } from 'react'
import axios from 'axios'
import './App.css'

function App() {
  const [messages, setMessages] = useState([])
  const [input, setInput] = useState('')
  const [userId] = useState('user-' + Math.floor(Math.random() * 10000))
  const messagesEndRef = useRef(null)
  const BASE_URL = import.meta.env.VITE_BASE_URL || 'http://localhost:8080'
  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' })
  }

  useEffect(() => {
    scrollToBottom()
  }, [messages])

  const sendMessage = async (e) => {
    e.preventDefault()
    if (!input.trim()) return

    const newMessages = [...messages, { sender: 'user', text: input }]
    setMessages(newMessages)
    setInput('')

    try {
      const response = await axios.post(`${BASE_URL}/webhook`, {
        userId,
        message: input
      })
      
      setMessages(prev => [...prev, { sender: 'bot', text: response.data.reply }])
    } catch (error) {
      setMessages(prev => [...prev, { sender: 'bot', text: 'Error connecting to server.' }])
    }
  }

  return (
    <div className="app-container">
      <div className="chat-box">
        <div className="header">
          <h2>ChatBot</h2>
          
        </div>
        
        <div className="messages-container">
          {messages.length === 0 && (
            <div className="welcome-text">Send "Hi" to start chatting.</div>
          )}
          {messages.map((msg, idx) => (
            <div key={idx} className={`message-row ${msg.sender}`}>
              <div className="message-bubble">
                {msg.text}
              </div>
            </div>
          ))}
          <div ref={messagesEndRef} />
        </div>

        <form className="input-container" onSubmit={sendMessage}>
          <input 
            type="text" 
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Type your message..."
            autoComplete="off"
          />
          <button type="submit" disabled={!input.trim()}>
            Send
          </button>
        </form>
      </div>
    </div>
  )
}

export default App
