package hu.acsaifz.blockchaindemo.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public class Block {
    private final int id;
    private final String previousHash;
    private final SortedSet<Transaction> transactionList;
    private final LocalDateTime time;
    private final int proof;

    public Block(int id, String previousHash, SortedSet<Transaction> transactionList, int proof) {
        this.id = id;
        this.previousHash = previousHash;
        this.transactionList = transactionList;
        this.time = LocalDateTime.now();
        this.proof = proof;
    }

    public int getId() {
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

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", previousHash='" + previousHash + '\'' +
                ", transactionList=" + transactionList +
                ", time=" + time +
                ", proof=" + proof +
                '}';
    }
}
