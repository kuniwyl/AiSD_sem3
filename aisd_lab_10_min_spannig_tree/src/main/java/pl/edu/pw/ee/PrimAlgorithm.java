package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pl.edu.pw.ee.Exceptions.FileIsEmptyException;
import pl.edu.pw.ee.Exceptions.GraphIsNotCompleteException;
import pl.edu.pw.ee.Exceptions.IllegalFormatDataException;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private ArrayList<GraphElement> graphElements = new ArrayList<>();
    private ArrayList<String> added = new ArrayList<>();
    private Heap heap = new Heap();

    @Override
    public String findMST(String pathToFile) {
        if(pathToFile == null){
            throw new IllegalArgumentException("path to file cannot be null");
        }
        readFile(pathToFile);
        String a = addToHeap();
        return a;
    }

    private void readFile(String filePath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            if(line == null){
                reader.close();
                throw new FileIsEmptyException("File is empty");
            }
            while (line != null) {
                createElement(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
        }
    }

    private void createElement(String data){
        String t[] = data.split(" ");
        validateData(t);
        addInGraphElements(t[0], t[1], Integer.parseInt(t[2]));
        addInGraphElements(t[1], t[0], Integer.parseInt(t[2]));
    }

    private void addInGraphElements(String a, String b, int t){
        String x;
        for(int i = 0; i < graphElements.size(); i++){
            x = graphElements.get(i).getName();
            if(x.equals(a)){
                graphElements.get(i).addElement(b, t);
                return;
            }
        }
        graphElements.add(new GraphElement(a));
        graphElements.get(graphElements.size() - 1).addElement(b, t);
    }

    private String addToHeap(){
        String ans = "";
        Element temp = new Element("", "", -1);

        while(!graphElements.get(0).isEmpty()){
            temp = graphElements.get(0).getElements();
            heap.put(temp);
        }
        added.add(temp.getFirst());

        temp = heap.pop();
        while(temp != null){
            ans += temp.toString() + "|";
            for(int i = 0; i < graphElements.size(); i++){
                if(graphElements.get(i).getName().equals(temp.getSecond())){
                    checkAndAddToHeapElemntIfNotInHeap(i);
                }
            }
            added.add(temp.getSecond());
            while(temp != null && added.contains(temp.getSecond())){
                temp = heap.pop();
            }
        }

        if(added.size() != graphElements.size()){
            throw new GraphIsNotCompleteException("Graph is not Completed");
        }

        return ans.substring(0, ans.length() - 1); 
    }

    private void checkAndAddToHeapElemntIfNotInHeap(int i){
        Element a = new Element("", "", -1);
        while(!graphElements.get(i).isEmpty()){
            a = graphElements.get(i).getElements();
            if(!added.contains(a.getSecond())){
                heap.put(a);
            }
        }
    }

    private void validateData(String[] t){
        if(t.length != 3){
            throw new IllegalFormatDataException("File contain illegal line");
        }
        if(t[0].equals(t[1]) || Integer.valueOf(t[2]) < 0){
            throw new IllegalFormatDataException("File contain illegal line");
        }
    }
}
