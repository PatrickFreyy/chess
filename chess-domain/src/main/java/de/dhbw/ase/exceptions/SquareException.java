package de.dhbw.ase.exceptions;

public class SquareException extends ArrayIndexOutOfBoundsException {

	public SquareException() {
		super("Square not on Board");
	}
	
	public SquareException(String msg) {
		super(msg);
	}
}
