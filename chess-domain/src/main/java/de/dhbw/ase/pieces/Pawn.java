package de.dhbw.ase.pieces;

import de.dhbw.ase.exceptions.ParameterException;
import de.dhbw.ase.field.Square;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

	private boolean firstMove;

	public Pawn(Color color) {
		super(color, 1);
		this.firstMove = true;
	}

	@Override
	public Square[][] move(Square[][] squares, Square currentSquare, Square targetSquare) {
		if (possibleMoves(squares, currentSquare).contains(targetSquare)) {
			squares[currentSquare.getX()][currentSquare.getY()].removePiece();
			squares[targetSquare.getX()][targetSquare.getY()].putPiece(this);
			setFirstMove();
			return squares;
		} else {
			return squares;
		}
	}

	//TODO: En Passant - gerninge Priorität
	@Override
	public Set<Square> possibleMoves(Square[][] squares, Square currentSquare) {
		Set<Square> possibleSquares = new HashSet<>();
		int colorDirection = 0;
		if (color.equals(Color.WHITE)) {
			colorDirection = 1;
		} else if (color.equals(Color.BLACK))
			colorDirection = -1;
		//move one Square
			int x = currentSquare.getX();
			int y = currentSquare.getY() + colorDirection;
			if (!squares[x][y].isOccupied()) {
				possibleSquares.add(squares[x][y]);
			}
		//first move two Squares
			x = currentSquare.getX();
			y = currentSquare.getY() + 2 * colorDirection;
			if (!squares[x][y].isOccupied() && firstMove) {
				possibleSquares.add(squares[x][y]);
			}
		//attack right
			x = currentSquare.getX() + 1;
			y = currentSquare.getY() + colorDirection;
			if (squares[x][y].isOccupied()) {
				possibleSquares.add(squares[x][y]);
			}
		//attack left
			x = currentSquare.getX() - 1;
			y = currentSquare.getY() + colorDirection;
			if (squares[x][y].isOccupied()) {
				possibleSquares.add(squares[x][y]);
			}
		return possibleSquares;
	}

	public Piece promote(String pieceName) throws ParameterException {
		switch (pieceName){
			case "Queen": return new Queen(this.color);
			case "Knight": return new Knight(this.color);
			case "Rook": return new Rook(this.color);
			case "Bishop": return new Bishop(this.color);
			default: throw new ParameterException("Invalid Name of new Piece");
		}
	}

	//---getter/setter---

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove() {
		if (this.firstMove){
			this.firstMove = firstMove;
		}
	}
}
