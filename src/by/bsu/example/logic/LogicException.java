package by.bsu.example.logic;



public class LogicException extends Exception{
    private static final long serialVersionUID = 2L;
    public LogicException(String message, Exception e){
        super(message, e);
    }
    public LogicException(String message){
        super(message);
    }
}

