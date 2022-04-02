package Entities;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Block implements PropertyChangeListener{
    private String hash;
    private Block prevBlock;
    private String data;
    private int nonce;
    private final int prefix = 4;
    
    private PropertyChangeSupport support;



    public Block(Block prevBlock, String data ){
    	support = new PropertyChangeSupport(this);
        this.data = data;
        this.prevBlock = prevBlock;
        this.nonce = 0;
        
        this.prevBlock.support.addPropertyChangeListener(this);
        this.calculateBlockHash();
        this.mineBlock();
        //save state
    }

    public Block(){
    	support = new PropertyChangeSupport(this);
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

    private void fireChange() {
        support.firePropertyChange("data", this.data, "what");
    }
    
    public void setData(String data) {
        this.data = data;
        this.calculateBlockHash();
        fireChange();
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
        this.fireChange();
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		calculateBlockHash();
		fireChange();
	}

}
