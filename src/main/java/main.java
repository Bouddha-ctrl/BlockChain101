import Entities.Block;
import service.*;
import service.impl.blockServiceImp;

public class main {

    public static void main (String[] args){


        Block firstBlock = new Block();

        Block b1 = new Block(firstBlock,"data1");
        Block b2 = new Block(b1,"data2");
        Block b3 = new Block(b2,"data3");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println("___________________");

        b2.setData("new data");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

    }
}
