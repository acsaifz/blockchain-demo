package hu.acsaifz.blockchaindemo.service;

import hu.acsaifz.blockchaindemo.entity.Wallet;
import org.hyperledger.besu.crypto.KeyPair;
import org.hyperledger.besu.crypto.SECP256K1;

public class WalletService {
    private Wallet wallet;
    private static final SECP256K1 secp256K1 = new SECP256K1();

    public void generateWallet(){
        if (wallet == null) {
            KeyPair keyPair = secp256K1.generateKeyPair();
            wallet = new Wallet(keyPair);
        }
    }

    public Wallet getWallet() {
        return wallet;
    }
}
