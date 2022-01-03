package pl.edu.pw.ee;

public class Element implements Comparable<Element>{
    
    private String first;
    private String second;
    private int value;

    Element(String first, String second, int value){
        this.first = first;
        this.second = second;
        this.value = value;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return first + "_" + String.valueOf(value) + "_" + second;
    }

    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.value, o.getValue());
    }
}
