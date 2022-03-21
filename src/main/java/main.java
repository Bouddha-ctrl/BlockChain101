import Entities.Block;
import service.*;
import service.impl.blockServiceImp;

public class main {

    public static void main (String[] args){

        blockService srv= new blockServiceImp();

        Block b = new Block("hh Ok");
        System.out.println(b);
        b = srv.calculateBlockHash(b);
        System.out.println(b);
        b = srv.mineBlock(b,4);
        System.out.println(b);

    }
}
