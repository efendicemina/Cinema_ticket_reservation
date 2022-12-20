package ba.unsa.etf.rpr.exception;

public class MovieException extends Exception{

    public MovieException(String msg, Exception reason){
        super(msg, reason);
    }

    public MovieException(String msg){
        super(msg);
    }
}
