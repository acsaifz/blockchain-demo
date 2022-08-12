package hu.acsaifz.blockchaindemo.service;

import hu.acsaifz.blockchaindemo.entity.Block;
import hu.acsaifz.blockchaindemo.entity.Transaction;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class BlockchainService {
    private static final int MINING_REWARD = 10;
    private SortedSet<Block> blockchain;
    private SortedSet<Transaction> openTransactions;

    public BlockchainService(){
        this.blockchain = new TreeSet<>(Comparator.comparingLong(Block::getId));
        this.openTransactions = new TreeSet<>(Comparator.comparing(Transaction::getTime));
        addBlock(getGenesisBlock());
    }

    public List<Block> getBlockchain() {
        List<Block> copyOfBlockChain = new java.util.ArrayList<>(List.copyOf(blockchain));
        copyOfBlockChain.sort((Block b1, Block b2) -> b2.getId() - b1.getId());
        return copyOfBlockChain;
    }

    public Block getLastBlock(){
        return blockchain.last();
    }

    public boolean addBlock(Block block){
        return blockchain.add(block);
    }

    public boolean addTransaction(Transaction transaction){
        return openTransactions.add(transaction);
    }

    public void mineBlock(String walletAddress){
        int proof = proofOfWork();
        Transaction transactionOfReward  = new Transaction("MINING", walletAddress,MINING_REWARD,"");

        if(addTransaction(transactionOfReward)){
            Block block = new Block(blockchain.size(),hashBlock(getLastBlock()),openTransactions,proof);
            if(addBlock(block)){
                System.out.println("Block found");
                openTransactions = new TreeSet<>(Comparator.comparing(Transaction::getTime));
            }else{
                System.out.println("Mining failed");
            }
        }
    }

    private Block getGenesisBlock(){
        return new Block(0,"",new TreeSet<>(),100);
    }

    private int proofOfWork(){
        int proof = 0;

        while(!validProof(proof)){
            proof++;
        }

        return proof;
    }
    private boolean validProof(int proof){
        String hashOfLastBlock = hashBlock(getLastBlock());
        String header = openTransactions.toString() + hashOfLastBlock + proof;
        String hash = DigestUtils.sha256Hex(header);
        return hash.startsWith("000");
    }

    private String hashBlock(Block block) {
        return DigestUtils.sha256Hex(block.toString());
    }


}
