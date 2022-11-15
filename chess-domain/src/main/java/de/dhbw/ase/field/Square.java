package de.dhbw.ase.field;

import de.dhbw.ase.pieces.Color;
import de.dhbw.ase.pieces.Piece;
import de.dhbw.ase.exceptions.SquareException;

public class Square {

	private final int x;
	private final int y;
	private Piece piece;

	public Square(int x, int y) throws SquareException{
		super();
		if (0 <= x && x < 8) {
			this.x = x;
		} else {
			throw new SquareException();
		}
		if (0 <= y && y < 8) {
			this.y = y;
		} else {
			throw new SquareException();
		}
	}
	
	public boolean isOccupied() {
		return piece != null;
	}

	public boolean putPiece(Piece piece) {
		if (isOccupied()) {
			this.piece = piece;
			return true;
		} else {
			return false;
		}
	}

	public Piece removePiece() {
		Piece piece = this.piece;
		this.piece = null;
		return piece;
	}

	public Color getColor(){
		if (piece != null) {
			return piece.getColor();
		}
		return null;
	}

	//---getter/setter

	public Piece getPiece() throws NullPointerException{
		return piece;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
