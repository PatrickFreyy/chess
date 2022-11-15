package de.dhbw.ase.exceptions;

public class ParameterException extends Exception{

    public ParameterException(){
        super("Command false Input/Parameters");
    }

    public ParameterException(String msg){
        super(msg);
    }
}
