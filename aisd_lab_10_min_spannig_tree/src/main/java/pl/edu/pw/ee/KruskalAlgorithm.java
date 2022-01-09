package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pl.edu.pw.ee.Exceptions.FileIsEmptyException;
import pl.edu.pw.ee.Exceptions.GraphIsNotCompleteException;
import pl.edu.pw.ee.Exceptions.IllegalFormatDataException;
import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {

    private Heap heap = new Heap();
    private ArrayList<ArrayList<String>> lista = new ArrayList<>();

    @Override
    public String findMST(String pathToFile) {
        if(pathToFile == null){
            throw new IllegalArgumentException("path to file cannot be null");
        }
        readData(pathToFile);
        return kruskal();
    }

    private void readData(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            if(line == null){
                reader.close();
                throw new FileIsEmptyException("FIle is empty");
            }
            while (line != null) {
                createData(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
        }
    }

    private void createData(String data){
        String[] d = data.split(" ");
        validateData(d);
        heap.put(new Element(d[0], d[1], Integer.valueOf(d[2])));
        putIfNotContaintToList(d[0]);
        putIfNotContaintToList(d[1]);
    }

    private void putIfNotContaintToList(String a){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).contains(a)){
                return; 
            }
        }
        lista.add(new ArrayList<>());
        lista.get(lista.size() - 1).add(a);
    }

    private String kruskal(){
        Element top = heap.pop();
        int first = -1;
        int second = -1;
        ArrayList<String> toDelete;
        String ans = "";

        while(lista.size() > 1 && top != null){

            for(int i = 0; i < lista.size(); i++){
                if(lista.get(i).contains(top.getFirst())){
                    first = i;
                }
                if(lista.get(i).contains(top.getSecond())){
                    second = i;
                }
            }

            if(first == -1 || second == -1){
                throw new IllegalArgumentException();
            }
            
            if(first != second){
                ArrayList<String> temp = new ArrayList<>();
                for(int i = 0; i < lista.get(first).size(); i++){
                    temp.add(lista.get(first).get(i));
                }
                for(int i = 0; i < lista.get(second).size(); i++){
                    temp.add(lista.get(second).get(i));
                }

                toDelete = lista.get(second);
                lista.remove(lista.get(first));
                lista.remove(toDelete);
                lista.add(temp);
                ans += top.toString() + "|";
            }
            
            top = heap.pop();
        }

        if(lista.size() > 1){
            throw new GraphIsNotCompleteException("Graph is not Completed");
        }

        return ans.substring(0, ans.length() - 1);
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
