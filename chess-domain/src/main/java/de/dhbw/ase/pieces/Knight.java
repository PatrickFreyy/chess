package de.dhbw.ase.pieces;

import de.dhbw.ase.field.Square;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color, 3);
	}

	@Override
	public Set<Square> possibleMoves(Square[][] squares, Square currentSquare) {
		Set<Square> possibleSquares = new HashSet<>();
		//in contrast to other pieces, no break, since Knight does jump over other pieces, so he can move on any square in range
		try {
			possibleSquares.add(squares[currentSquare.getX() + 2][currentSquare.getY() - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() + 2][currentSquare.getY() + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() + 1][currentSquare.getY() + 2]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() - 1][currentSquare.getY() + 2]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() - 2][currentSquare.getY() + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() - 2][currentSquare.getY() - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() - 1][currentSquare.getY() - 2]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			possibleSquares.add(squares[currentSquare.getX() + 1][currentSquare.getY() - 2]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return possibleSquares;
	}

}
