package hu.acsaifz.blockchaindemo.entity;

import java.time.LocalDateTime;

public class Transaction {
    private final String sender;
    private final String recipient;
    private final double amount;
    private final String signature;
    private final LocalDateTime time;

    public Transaction(String sender, String recipient, double amount, String signature) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.signature = signature;
        this.time = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public String getSignature() {
        return signature;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
