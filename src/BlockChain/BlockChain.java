package BlockChain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private List<Block> chain;
    private int difficulty = 3;

    // create new blockchain containing genesis block
    public BlockChain() {

        chain = new ArrayList<Block>();
        chain.add(generateGenesis());

    }

    // method for creating a genesis block
    private Block generateGenesis() {

        Block genesis = new Block("0x200", new java.util.Date(), "<transactions");
        genesis.setPreviousHash(null);
        genesis.computeHash();
        return genesis;

    }

    // method for adding a block to the chain
    public void addBlock(Block block){

        Block newBlock = block;
        newBlock.setPreviousHash(chain.get(chain.size() - 1).getHash());
        newBlock.computeHash();
        this.chain.add(newBlock);
    }

    // method for displaying the contents of the chain
    public void displayChain() {

        for(int i = 0; i < chain.size() - 1; i++) {
            System.out.println("Block: " + i);
            System.out.println("Version: " + chain.get(i).getVersion());
            System.out.println("Timestamp: " + chain.get(i).getTimestamp());
            System.out.println("Previous Hash: " + chain.get(i).getPreviousHash());
            System.out.println("Hash: " + chain.get(i).getHash());
            System.out.println();
        }

    }

    // method for determining if the blockchain has been tampered with
    public boolean isValid() {

        boolean validity = true;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i = chain.size() - 1; i > 0; i--) {

            // check current block hash
            if( !(chain.get(i).getHash().equals(chain.get(i).computeHash()) )) {
                System.out.println("Chain is invalid");
                validity = false;
                break;
            }

            // check previous block hash
            if( !(chain.get(i).getPreviousHash().equals(chain.get(i - 1).computeHash())) ){
                System.out.println("Chain is invalid");
                validity = false;
                break;
            }

            // check if block has been mined
            if(!( chain.get(i).getHash().substring(0, difficulty).equals(hashTarget))) {
                System.out.println("This block has not been mined");
                validity = false;
            }
        }
        return validity;

    }



}
