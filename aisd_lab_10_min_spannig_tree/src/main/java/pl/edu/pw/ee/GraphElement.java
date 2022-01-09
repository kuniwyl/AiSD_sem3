package pl.edu.pw.ee;

import java.util.ArrayList;

public class GraphElement {
    
    private String name;
    private ArrayList<GraphSubElement> list = new ArrayList<>();

    GraphElement(String name){
        this.name = name;
    }

    public void addElement(String subElementName, int connectionValue){
        list.add(new GraphSubElement(subElementName, connectionValue));
    }

    public Element getElements(){
        if(!isEmpty()){
            String subName = list.get(0).getSubElementName();
            int value = list.get(0).getConnectionValue();
            list.remove(0);
            return new Element(name, subName, value);
        }
        return null;
    }

    public ArrayList<GraphSubElement> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public String getSubString(int i){
        return list.get(i).getSubElementName();
    }

    @Override
    public String toString() {
        return name;
    }

    private class GraphSubElement{
        private String subElementName;
        private int connectionValue;

        GraphSubElement(String subElementName, int connectionValue){
            this.subElementName = subElementName;
            this.connectionValue = connectionValue;
        }

        public String getSubElementName() {
            return subElementName;
        }

        public int getConnectionValue() {
            return connectionValue;
        }
    }
}
