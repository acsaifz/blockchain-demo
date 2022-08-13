package hu.acsaifz.blockchaindemo.entity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction>{
    private String sender;
    private String recipient;
    private double amount;
    private String signature;
    private LocalDateTime time;

    public Transaction(){

    }

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

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", amount=" + amount +
                ", signature='" + signature + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(@NotNull Transaction o) {
        return time.compareTo(o.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (!sender.equals(that.sender)) return false;
        if (!recipient.equals(that.recipient)) return false;
        if (!signature.equals(that.signature)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sender.hashCode();
        result = 31 * result + recipient.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + signature.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }
}
