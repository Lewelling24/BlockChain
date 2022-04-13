package Driver;

import BlockChain.*;

import java.util.Date;


public class Driver {

    public static void main(String [] args) {

        BlockChain funCoin = new BlockChain();

        Block one = new Block("0x200", new Date(), "<transaction");
        Block two = new Block("0x200", new Date(), "<transaction");
        Block three = new Block("0x200", new Date(), "<transaction");

        funCoin.addBlock(one);
        funCoin.addBlock(two);
        funCoin.addBlock(three);

        funCoin.displayChain();

        System.out.println(funCoin.isValid());

    }

}
