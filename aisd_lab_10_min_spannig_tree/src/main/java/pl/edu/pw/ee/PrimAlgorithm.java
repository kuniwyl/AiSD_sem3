package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private ArrayList<Element> lista = new ArrayList<>();

    public String findMST(String pathToFile){
        try {
            File myObj = new File(pathToFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                createElement(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        }
        
        return null;
    }

    private void createElement(String data){
        String t[] = data.split(" ");
        lista.add(new Element(t[0], t[1], Integer.parseInt(t[2])));
    }
}
