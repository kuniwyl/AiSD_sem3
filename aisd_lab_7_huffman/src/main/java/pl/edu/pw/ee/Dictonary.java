package pl.edu.pw.ee;

public class Dictonary {

    private String code;
    private int znak;

    Dictonary(String code, int znak) {
        this.code = code;
        this.znak = znak;
    }

    public int getZnak() {
        return znak;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ":" + znak + " " + (char) znak;
    }

}
