package pl.edu.pw.ee;

import org.junit.Test;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearchTest {

    @Test
    public void test1(){
        String t = "BCDC";
        PatternSearch search = new DeterministicFiniteAutomatonTextSearch(t);
        String p = "ABCDCBH";
        System.out.println(search.findFirst(p));
    }

}
