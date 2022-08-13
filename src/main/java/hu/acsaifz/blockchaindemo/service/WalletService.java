package hu.acsaifz.blockchaindemo.service;

import hu.acsaifz.blockchaindemo.entity.Transaction;
import hu.acsaifz.blockchaindemo.entity.Wallet;
import org.apache.tuweni.bytes.Bytes;
import org.apache.tuweni.bytes.Bytes32;
import org.hyperledger.besu.crypto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.hyperledger.besu.crypto.Hash.keccak256;

@Service
@SessionScope
public class WalletService {
    private Wallet wallet;
    private final SECP256K1 secp256K1;

    public WalletService() {
        secp256K1 = new SECP256K1();
    }

    public String signTransaction(String recipient, double amount){
        String sender = wallet.getAddress();
        String signContext = sender + recipient + amount;
        Bytes signData = Bytes.wrap(signContext.getBytes(StandardCharsets.UTF_8));
        Bytes32 signHash = keccak256(signData);
        SECPSignature signature = secp256K1.sign(signHash,wallet.getKeyPair());
        return signature.encodedBytes().toHexString();
    }

    public boolean verifyTransaction(Transaction transaction){
        String signContext = transaction.getSender() + transaction.getRecipient() + transaction.getAmount();
        Bytes signData = Bytes.wrap(signContext.getBytes(StandardCharsets.UTF_8));
        Bytes32 signHash = keccak256(signData);

        Bytes signBytes = Bytes.fromHexString(transaction.getSignature());

        SECPSignature signature = secp256K1.decodeSignature(signBytes);
        SECPPublicKey pubKey = secp256K1.recoverPublicKeyFromSignature(signHash,signature).orElseThrow();
        return secp256K1.verify(signData,signature,pubKey, Hash::keccak256);
    }



    public void generateWallet(){
        if (wallet == null) {
            KeyPair keyPair = secp256K1.generateKeyPair();
            wallet = new Wallet(keyPair);
            KeyPairUtil.storeKeyFile(keyPair, Paths.get("./"));
        }
    }

    public Wallet getWallet() {
        return wallet;
    }
}
