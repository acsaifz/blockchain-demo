package hu.acsaifz.blockchaindemo.entity;

import org.hyperledger.besu.crypto.KeyPair;
import org.hyperledger.besu.crypto.SECPPublicKey;
import org.hyperledger.besu.datatypes.Address;

public class Wallet {
    private final KeyPair keyPair;

    public Wallet(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public SECPPublicKey getPublicKey(){
        return keyPair.getPublicKey();
    }

    public String getAddress(){
        Address address = Address.extract(getPublicKey());
        return address.toString();
    }
}
