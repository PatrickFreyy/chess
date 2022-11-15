package de.dhbw.ase.exceptions;

public class ScoreException extends Exception{

	public ScoreException() {
		super("score can't be decreased");
	}
	
	public ScoreException(String msg) {
		super(msg);
	}

}
