package snsinternaltransfer.sns.utility.dictionaryAttack;

public class Passwords {

    private int id;
    private String hash;
    private String value;


    public Passwords() {
    }

    public Passwords(int id, String hash, String value) {
        this.id = id;
        this.hash = hash;
        this.value = value;
    }

    public Passwords(String hash, String value) {
        this.hash = hash;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
