package hu.acsaifz.blockchaindemo.entity;

import org.hyperledger.besu.crypto.KeyPair;

public class Wallet {
    private final KeyPair keyPair;

    public Wallet(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }
}
