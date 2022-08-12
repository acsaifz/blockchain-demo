package hu.acsaifz.blockchaindemo.service;

import hu.acsaifz.blockchaindemo.entity.Block;
import hu.acsaifz.blockchaindemo.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlockchainService {
    SortedSet<Block> blockchain;
    SortedSet<Transaction> openTransactions;

    public BlockchainService(){
        this.blockchain = new TreeSet<>(Comparator.comparing(Block::getId));
        this.openTransactions = new TreeSet<>(Comparator.comparing(Transaction::getTime));
        addBlock(getGenesisBlock());
        System.out.println("Blockchain létrehozva");
    }

    public Set<Block> getBlockchain() {
        return Set.copyOf(blockchain);
    }

    public Block getLastBlock(){
        return blockchain.last();
    }

    public boolean addBlock(Block block){
        return blockchain.add(block);
    }

    private Block getGenesisBlock(){
        return new Block(0,"",new ArrayList<Transaction>(),100);
    }

}
