package ba.unsa.etf.rpr.exception;

/**
 * MovieException is a custom exception class for handling errors related to movie operations.
 * It extends the base Exception class and provides two constructors for creating instances of the exception.
 * @author Emina Efendic
 */
public class MovieException extends Exception{

    public MovieException(String msg, Exception reason){
        super(msg, reason);
    }
    public MovieException(String msg){
        super(msg);
    }
}
