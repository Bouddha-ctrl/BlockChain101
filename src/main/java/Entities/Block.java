package Entities;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Block {
    private String hash;
    private String prevHash;
    private String data;
    private int nonce;

    public Block(String prevHash, String data ){
        this.data = data;
        this.prevHash = prevHash;
        this.nonce = 0;
    }

    public Block(String data){
        this.data = data;
        this.prevHash = "00000";
        this.nonce = 0;
    }



    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", data='" + data + '\'' +
                ", nonce=" + nonce +
                '}';
    }
}
