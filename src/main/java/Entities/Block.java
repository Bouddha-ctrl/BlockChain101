package Entities;


import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Block {
    private String hash;
    private Block prevBlock;
    private String data;
    private int nonce;
    private final int prefix = 4;


    public Block(Block prevBlock, String data ){
        this.data = data;
        this.prevBlock = prevBlock;
        this.nonce = 0;
        this.calculateBlockHash();
        this.mineBlock();
        //save state
    }

    public Block(){
        this.hash = "0".repeat(64);
        this.prevBlock = null;
    }


    public String getHash() {
        return hash;
    }

    public Block getPrevBlock() {
        return prevBlock;
    }

    public String getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }


    public void setData(String data) {
        this.data = data;
        this.calculateBlockHash();
        //set state
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hashFormation(hash) + '\'' +
                ", prevBlock=" + hashFormation(prevBlock.getHash()) +
                ", data='" + data + '\'' +
                ", nonce=" + nonce +
                '}';
    }

    ///////////////
    private String hashFormation(String hash){
        StringBuilder output = new StringBuilder();
        int len = hash.length();

        output.append(hash.substring(0,prefix+2));
        output.append("...");
        output.append(hash.substring(len-4,len));
        return output.toString();
    }
    ///////////////
    public String getPrevHash(){
        return this.prevBlock.getHash();
    }

    private void calculateBlockHash() {

        String dataToHash = this.getPrevHash() + this.data + this.nonce;
        MessageDigest digest = null;
        byte[] bytes = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        }catch(Exception ex){
            System.out.println("oupsi");
        }

        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }

        this.hash = buffer.toString();
    }

    public void mineBlock() {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.hash.substring(0, prefix).equals(prefixString)) {
            this.nonce++;
            calculateBlockHash() ;
        }
    }
/*
    public  void update(){
        Class<?> blockClass = this.getClass();
        Field[] declaredFields = blockClass.getDeclaredFields();

        Arrays.stream(declaredFields).forEach(e->{
            e.setAccessible(true);
            //call state != save stat
            //maj
            //java reflexi
        });

    }
    */

}
