package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {

    private Heap heap = new Heap();
    private ArrayList<KruskalElement> lista = new ArrayList<>();

    @Override
    public String findMST(String pathToFile) {
        readData(pathToFile);
        return kruskal();
    }

    private void readData(String path){
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                createData(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            if(path == null){
                throw new RuntimeException("filename cannot be null");
            }
            throw new RuntimeException("File not found");
        }
    }

    private void createData(String data){
        String[] d = data.split(" ");
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
        lista.add(new KruskalElement());
        lista.get(lista.size() - 1).put(a);
    }

    private String kruskal(){
        Element top = heap.pop();
        int first = -1;
        int second = -1;
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
                KruskalElement temp = new KruskalElement();
                System.out.println(lista.get(first));
                System.out.println(lista.get(second));
                System.out.println();
                for(int i = 0; i < lista.get(first).size(); i++){
                    temp.put(lista.get(first).get(i));
                }
                for(int i = 0; i < lista.get(second).size(); i++){
                    temp.put(lista.get(second).get(i));
                }
                lista.remove(first);
                lista.remove(second);
                lista.add(temp);
            }
            top = heap.pop();
        }
        return lista.get(0).toString();
    }

    private class KruskalElement{
        private ArrayList<String> elements = new ArrayList<>();

        public void put(String x){
            elements.add(x);
        }

        public String get(int index){
            return elements.get(index);
        }
        
        public boolean contains(String a){
            return elements.contains(a);
        }

        public void remove(String a){
            lista.remove(a);
        }

        public int size(){
            return elements.size();
        }

        @Override
        public String toString() {
            String a = "";
            for(int i = 0; i < elements.size(); i++){
                a += elements.get(i);
            }
            return a;
        }
    }
    
}
