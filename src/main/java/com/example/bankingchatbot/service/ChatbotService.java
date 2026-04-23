package com.example.bankingchatbot.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ChatbotService {

    private double accountBalance = 15243.50;
    
    public String handleMessage(String message) {
        message = message.toLowerCase().trim();
        
        if (message.contains("balance")) {
            return "Your current account balance is $" + String.format("%.2f", accountBalance) + ".";
        } else if (message.contains("transfer") || message.contains("send money")) {
            return "I can help you transfer funds. Please specify the recipient and amount in the format: 'Transfer $X to Y'.";
        } else if (message.matches(".*transfer \\$?\\d+(\\.\\d{1,2})? to .*")) {
             return "Transfer simulated successfully. Your request is being processed.";
        } else if (message.contains("transaction") || message.contains("history")) {
            return "Here are your last 3 transactions:\n1. Amazon - $45.99\n2. Starbucks - $5.40\n3. Payroll Deposit + $3,200.00";
        } else if (message.contains("loan") || message.contains("mortgage")) {
            return "We offer personal loans starting at 4.5% APR and mortgages at 5.2% APR. Would you like me to connect you to a loan specialist?";
        } else if (message.contains("hi") || message.contains("hello") || message.contains("hey")) {
            return "Hello! Welcome to OpenCloud Banking. I can help you check your balance, review transactions, or apply for loans. How can I assist you today?";
        } else if (message.contains("bye") || message.contains("exit")) {
            return "Thank you for using OpenCloud Banking. Have a great day!";
        } else {
             String[] prompts = {
                "I'm not completely sure I understood that. Could you try asking about 'balance', 'transactions', or 'loans'?",
                "My apologies, I can only help with things like balance inquiries, fund transfers, and review history right now.",
                "I didn't quite catch that. Try asking 'What's my balance?'"
             };
             return prompts[new Random().nextInt(prompts.length)];
        }
    }
}
