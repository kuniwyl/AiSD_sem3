package pl.edu.pw.ee;

import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    @Test
    public void test(){
        LongestCommonSubsequence t = new LongestCommonSubsequence("pilowalem", "pilem");
        System.out.println(t.findLCS());
        String[][] tab = t.display();
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab[i].length; j++){
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }
    }
}
