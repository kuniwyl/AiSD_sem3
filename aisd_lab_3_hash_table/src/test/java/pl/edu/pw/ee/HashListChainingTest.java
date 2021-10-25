package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashListChainingTest {

    private HashTable hashTable;

    @Before
    public void setUp(){
        hashTable = new HashListChaining(2);
    }

    @Test
    public void add_Test(){
        String[] str = {"a","aa","aaa","aaaa","aaacn","aaah","aaai", "a"};
        for(int i = 0; i < str.length; i++){
            hashTable.add(str[i]);
        }
        Object[][] tab = hashTable.get_table();
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab[i].length; j++)
                System.out.print(tab[i][j] + "\t");
            System.out.println("");
        }
    }

}
