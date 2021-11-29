package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Huffman {

    public ArrayList<Element> tab = new ArrayList<Element>();

    // PAMIETAĆ ŻE TO JEST ŚCIEŻKA DO KATALOGU WIĘC WSZYSTKIE PLIKI W KATALOGU
    public int huffman(String pathToRootDir, boolean compress){
    	BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(pathToRootDir));
            String line = reader.readLine();
            while(line != null){
                addElementsToTab(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e){
            //throw new MyException() to do
        }
    	return -1;
    }

    private void addElementsToTab(String line){
        boolean isInList = false;
        int index = -1;

        for(int i = 0; i < line.length(); i++){
            for(int j = 0; j < tab.size() - 1; j++){
                if (tab.get(j).getKey() == line.charAt(i)){
                    isInList = true;
                    index = j;
                    break;
                }
            }
            if(isInList){
                tab.get(index).setValue(tab.get(index).getValue() + 1);
                index = -1;
                isInList = false;
            } else {
                tab.add(new Element(line.charAt(i), 1));
            }
        }
    }

    private class Element{
        private char key;
        private int value;
        
        Element(char key, int value){
            this.key = key;
            this.value = value;
        }

        public char getKey() {
            return key;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
