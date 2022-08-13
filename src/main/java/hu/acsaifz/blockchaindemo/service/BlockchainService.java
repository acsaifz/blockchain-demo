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
    private static final String DIFFICULTY = "00";
    private final SortedSet<Block> blockchain;
    private SortedSet<Transaction> openTransactions;

    public BlockchainService(){
        this.blockchain = new TreeSet<>(Comparator.comparingLong(Block::getId));
        this.openTransactions = new TreeSet<>(Comparator.comparing(Transaction::getTime));
        addBlock(getGenesisBlock());
        System.out.println(DigestUtils.sha256Hex(""));
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

    public boolean addTransaction(WalletService walletService, String recipient, double amount){
        String sender = walletService.getWallet().getAddress();
        String signature = walletService.signTransaction(recipient, amount);
        Transaction transaction = new Transaction(sender,recipient,amount,signature);

        if (amount < getBalance(sender) && walletService.verifyTransaction(transaction)){
            addTransaction(transaction);
            return true;
        }

        return false;
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

    public double getBalance(String walletAddress){
        double amountSent = 0;
        double amountReceived = 0;

        for (Block block: blockchain){
            if (block.getTransactionList() == null){
                continue;
            }
            for (Transaction transaction: block.getTransactionList()){
                if (transaction.getSender().equals(walletAddress)){
                    amountSent += transaction.getAmount();
                }
                if (transaction.getRecipient().equals(walletAddress)){
                    amountReceived += transaction.getAmount();
                }
            }
        }

        for(Transaction transaction: openTransactions){
            if (transaction.getSender().equals(walletAddress)){
                amountSent += transaction.getAmount();
            }
        }
        return amountReceived - amountSent;
    }

    private Block getGenesisBlock(){
        return new Block(0,"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",new TreeSet<>(),100);
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
        return hash.startsWith(DIFFICULTY);
    }

    private String hashBlock(Block block) {
        return DigestUtils.sha256Hex(block.toString());
    }


}
