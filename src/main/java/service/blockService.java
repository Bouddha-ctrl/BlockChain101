package service;

import Entities.Block;

public interface blockService {

    public Block calculateBlockHash(Block block);

    public Block mineBlock(Block block, int prefix);
}
