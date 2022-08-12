package hu.acsaifz.blockchaindemo.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Block {
    private final long id;
    private final String previousHash;
    private final List<Transaction> transactionList;
    private final LocalDateTime time;
    private final int proof;

    public Block(long id, String previousHash, List<Transaction> transactionList, LocalDateTime time, int proof) {
        this.id = id;
        this.previousHash = previousHash;
        this.transactionList = transactionList;
        this.time = time;
        this.proof = proof;
    }

    public long getId() {
        return id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public List<Transaction> getTransactionList() {
        return List.copyOf(transactionList);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getProof() {
        return proof;
    }
}
