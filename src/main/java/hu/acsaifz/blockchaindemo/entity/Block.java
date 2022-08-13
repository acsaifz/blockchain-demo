package hu.acsaifz.blockchaindemo.entity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public class Block implements Comparable<Block>{
    private int id;
    private String previousHash;
    private SortedSet<Transaction> transactionList;
    private LocalDateTime time;
    private int proof;

    public Block(){

    }

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

    @Override
    public int compareTo(@NotNull Block o) {
        return id - o.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (id != block.id) return false;
        if (proof != block.proof) return false;
        if (!previousHash.equals(block.previousHash)) return false;
        if (!transactionList.equals(block.transactionList)) return false;
        return time.equals(block.time);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + previousHash.hashCode();
        result = 31 * result + transactionList.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + proof;
        return result;
    }
}
