package Exceptions;

public class NoWorkerException extends Exception{
    public NoWorkerException(int number) { super("No worker at Cash Register: " + number); }

}

