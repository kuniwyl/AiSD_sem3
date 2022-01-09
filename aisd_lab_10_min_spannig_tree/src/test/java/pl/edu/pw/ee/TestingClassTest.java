package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import pl.edu.pw.ee.Exceptions.FileIsEmptyException;
import pl.edu.pw.ee.Exceptions.GraphIsNotCompleteException;
import pl.edu.pw.ee.Exceptions.IllegalFormatDataException;
import pl.edu.pw.ee.services.MinSpanningTree;

public abstract class TestingClassTest {
    
    protected MinSpanningTree t;

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnException_RuntimeException_FileNameIsNull() {
        String a = t.findMST(null);
        assert false;
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnException_FileNotFoundException_FileNameNotExist() {
        String a = t.findMST("temporary.txt");
        assert false;
    }

    @Test(expected = IllegalFormatDataException.class)
    public void shouldReturnException_IllegalFormatDataException_FileContainBannedValued() {
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\bad_data.txt");
        assert false;
    }

    @Test(expected = FileIsEmptyException.class)
    public void shouldReturnException_FileIsEmptyException_FileIsEmpty() {
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\empty.txt");
        assert false;
    }

    @Test(expected = GraphIsNotCompleteException.class)
    public void shouldReturnException_GraphIsNotCompleteException_FileContainNotCompletedGraph(){
        String a = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\not_complete.txt");
        assert false;
    }

    @Test
    public void shouldReturnTrue_FunnyDataOnlyOne(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\funny_data.txt");
        String expected = "A_1_B";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_LastData(){
        String actual = t.findMST("src\\test\\java\\pl\\edu\\pw\\ee\\testData\\last_data.txt");
        // String expected = "A_1_B";
        // assertEquals(expected, actual);
        System.out.println(actual);
    }

}
