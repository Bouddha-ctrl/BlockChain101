package service.impl;

import Entities.Block;
import service.blockService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class blockServiceImp implements blockService {
   /* @Override
    public Block calculateBlockHash(Block block) {

        String dataToHash = block.getPrevHash() + block.getData() + block.getNonce();
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

        block.setHash(buffer.toString());

        return block;
    }

    @Override
    public Block mineBlock(Block block, int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!block.getHash().substring(0, prefix).equals(prefixString)) {
            block.setNonce(block.getNonce()+1);
            block = calculateBlockHash(block) ;
        }
        return block;
    }*/
}
