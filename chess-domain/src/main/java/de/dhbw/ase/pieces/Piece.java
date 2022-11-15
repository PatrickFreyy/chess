package de.dhbw.ase.pieces;

import de.dhbw.ase.exceptions.MoveException;
import de.dhbw.ase.field.Square;
import java.util.Set;

public abstract class Piece {

	protected final Color color;
	protected final int value;
	
	public Piece(Color color, int value) {
		super();
		this.color = color;
		this.value = value;
	}
	
	//Implementation must handle SquareException on creation of List<Square>
	public abstract Set<Square> possibleMoves(Square[][] squares, Square currentSquare);

	public Square[][] move(Square[][] squares, Square currentSquare, Square targetSquare) throws MoveException {
		if (possibleMoves(squares, currentSquare).contains(targetSquare)) {
			squares[targetSquare.getX()][targetSquare.getY()].putPiece(this);
			squares[currentSquare.getX()][currentSquare.getY()].removePiece();
		} else {
			throw new MoveException();
		}
		return squares;
	}

//	---getter/setter---
	public Color getColor(){
		return this.color;
	}
	public int getValue() {
		return value;
	}
}
