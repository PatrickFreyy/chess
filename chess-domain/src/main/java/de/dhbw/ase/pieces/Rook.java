package de.dhbw.ase.pieces;

import java.util.HashSet;
import java.util.Set;

import de.dhbw.ase.exceptions.MoveException;
import de.dhbw.ase.field.Square;

public class Rook extends Piece {

	private boolean firstMove;

	public Rook(Color color) {
		super(color, 5);
		this.firstMove = true;
	}

	@Override
	public Set<Square> possibleMoves(Square[][] squares, Square currentSquare) {
		Set<Square> possibleSquares = new HashSet<>();
		//one for-loop calculates all legal moves in one direction. If a square is occupied, the direction is not further persued, since the piece cant jump over other pieces
		for (int i = 1; i < 8; i++) {
			try {
				Square square = squares[currentSquare.getX()][currentSquare.getY() + i];
				possibleSquares.add(square);
				if (square.isOccupied()) {
					break;
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		for (int i = 1; i < 8; i++) {
			try {
				Square square = squares[currentSquare.getX() - i][currentSquare.getY()];
				possibleSquares.add(square);
				if (square.isOccupied()) {
					break;
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		for (int i = 1; i < 8; i++) {
			try {
				Square square = squares[currentSquare.getX()][currentSquare.getY() - i];
				possibleSquares.add(square);
				if (square.isOccupied()) {
					break;
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		for (int i = 1; i < 8; i++) {
			try {
				Square square = squares[currentSquare.getX()][currentSquare.getY() + i];
				possibleSquares.add(square);
				if (square.isOccupied()) {
					break;
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		return possibleSquares;
	}

	public Square castlingKingsSide(Square[][] squares, Square currentSquare) throws MoveException {
		if (firstMove) {
		//not necessary to check for arrayIndexOutOfBounds, if firstMove, targetSquare is definitely in squares. Otherwise, the board was initialized incorrectly
			return squares[currentSquare.getX() - 2][currentSquare.getY()];
		} else {
			throw new MoveException("Rook has moved already");
		}
	}
	
	public Square castlingQueensSide(Square[][] squares, Square currentSquare) throws MoveException {
		if (firstMove) {
			//not neccessary to check for arrayindex, if firstMove, new Square is defintily in squares
			return squares[currentSquare.getX() + 3][currentSquare.getY()];
		} else {
			throw new MoveException("Rook has moved already");
		}
	}

	public boolean getFirstMove(){
		return firstMove;
	}

	public void setFirstMove(boolean firstMove){
		if (!firstMove) {
			this.firstMove = firstMove;
		}
	}
}
