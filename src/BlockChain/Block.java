package BlockChain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class Block {

    private String version;
    private Date timestamp;
    private String Hash;
    private String previousHash;
    private String data;
    private int nonce;


    // constructor for new blocks
    public Block(String version, Date timestamp, String data) {

        this.version = version;
        this.timestamp = timestamp;
        this.data = data;
        this.Hash = computeHash();

    }

    // method to compute hash for a block
    public String computeHash() {
        String DataToHash = "" + this.version + this.timestamp + this.previousHash + this.data;

        MessageDigest digest;
        String encoded = null;

        try {

            digest = MessageDigest.getInstance("SHA-256");
            byte [] hash = digest.digest(DataToHash.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.Hash = encoded;
        return encoded;

    }

    // method for mining a block

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(! (Hash.substring(0, difficulty).equals(target))) {

            nonce++;
            this.Hash = computeHash();

        }

        System.out.println("Block mined " + Hash);
    }


    // generated getters and setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() { return data; }

    public void setData(String data) {
        this.data = data;
    }

    public int getNonce() { return nonce; }

    public void setNonce(int nonce) { this.nonce = nonce; }
}
