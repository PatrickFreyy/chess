package de.dhbw.ase.exceptions;

public class MoveException extends Exception {

	public MoveException() {
		super("invalid move");
	}
	
	public MoveException(String msg) {
		super(msg);
	}
}
