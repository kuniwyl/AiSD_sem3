package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private ArrayList<GraphElement> graphElements = new ArrayList<>();

    @Override
    public String findMST(String pathToFile) {
        readFile(pathToFile);
        String a = addToHeap();
        validateIfGraphIsConnected(a);
        return a;
    }

    private void readFile(String filePath){
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            validateMyReader(myReader);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                createElement(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            if(filePath == null){
                throw new RuntimeException("filename cannot be null");
            }
            throw new RuntimeException("File not found");
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
        Heap heap = new Heap();
        String ans = "";
        ArrayList<String> added = new ArrayList<>();
        Element temp = new Element("", "", -1);
        Element a = new Element("", "", -1);

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
                    while(!graphElements.get(i).isEmpty()){
                        a = graphElements.get(i).getElements();
                        if(!added.contains(a.getSecond())){
                            heap.put(a);
                        }
                    }
                }
            }
            added.add(temp.getSecond());
            while(temp != null && added.contains(temp.getSecond())){
                temp = heap.pop();
            }
        }
        return ans.substring(0, ans.length() - 1); 
    }

    private void validateData(String[] t){
        if(t.length != 3){
            throw new IllegalArgumentException("Bad data");
        }
        if(t[0].equals(t[1]) || Integer.valueOf(t[2]) < 0){
            throw new IllegalArgumentException("Data in file are incorrect");
        }
    }

    private void validateIfGraphIsConnected(String a){
        ArrayList<String> lista = new ArrayList<>();
        for(int i = 0; i < a.length(); i += 2){
            if((i - 2) % 6 != 0){
                if(!lista.contains(a.substring(i, i + 1))){
                    lista.add(a.substring(i, i + 1));
                }
            }
        }
        if(lista.size() != graphElements.size()){
            throw new IllegalArgumentException("Graph in not connected");
        }
    }

    private void validateMyReader(Scanner t){
        if(t == null){
            throw new IllegalArgumentException("File is empty");
        }
    }
}
