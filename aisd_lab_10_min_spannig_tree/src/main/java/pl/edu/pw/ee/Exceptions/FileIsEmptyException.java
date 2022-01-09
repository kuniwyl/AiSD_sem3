package pl.edu.pw.ee.Exceptions;

public class FileIsEmptyException extends IllegalArgumentException {
    public FileIsEmptyException(String message){
        super(message);
    }
}
