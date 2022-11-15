package de.dhbw.ase.field;

import java.util.Set;
import java.util.TreeSet;

import de.dhbw.ase.exceptions.MoveException;
import de.dhbw.ase.exceptions.SquareException;
import de.dhbw.ase.pieces.*;

public class Board {

	Square[][] squares;
	Set<Piece> capturedPieces;

	public Board() {
		try {
			squares = new Square[8][8];
		} catch (SquareException e) {
			// Error can't can't occur in running Program, since all Data in the squares is
			// hard-coded
			e.printStackTrace();
		}
		
		this.capturedPieces = new TreeSet<>();

		// create White Pieces
		for (int i = 0; i < 8; i++) {
			squares[i][1].putPiece(new Pawn(Color.WHITE));
		}
		squares[0][0].putPiece(new Rook(Color.WHITE));
		squares[1][0].putPiece(new Knight(Color.WHITE));
		squares[2][0].putPiece(new Bishop(Color.WHITE));
		squares[3][0].putPiece(new Queen(Color.WHITE));
		squares[4][0].putPiece(new King(Color.WHITE));
		squares[5][0].putPiece(new Bishop(Color.WHITE));
		squares[6][0].putPiece(new Knight(Color.WHITE));
		squares[7][0].putPiece(new Rook(Color.WHITE));
		// create Black Pieces
		for (int i = 0; i < 8; i++) {
			squares[i][7].putPiece(new Pawn(Color.BLACK));
		}
		squares[0][8].putPiece(new Rook(Color.BLACK));
		squares[1][8].putPiece(new Knight(Color.BLACK));
		squares[2][8].putPiece(new Bishop(Color.BLACK));
		squares[3][8].putPiece(new Queen(Color.BLACK));
		squares[4][8].putPiece(new King(Color.BLACK));
		squares[5][8].putPiece(new Bishop(Color.BLACK));
		squares[6][8].putPiece(new Knight(Color.BLACK));
		squares[7][8].putPiece(new Rook(Color.BLACK));
	}

	//---movePiece---
	public boolean movePiece(Square currentSquare, Square targetSquare) {
		try {
			//Piece-Object handles
			this.squares = squares[currentSquare.getX()][currentSquare.getY()].getPiece().move(this.squares, currentSquare, targetSquare);
		} catch (MoveException e) {
			//TODO Parse error message to UI
			return false;
		}
		return true;
	}

	public Set<Square> possibleMoves(Square currentSquare) {
		return squares[currentSquare.getX()][currentSquare.getY()].getPiece().possibleMoves(squares, currentSquare);
	}

	// ---getter---

	public Square getSquareByIndex(int x, int y) {
		try {
			return squares[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Parse error message to UI
			return null;
		}
	}

	public Square[][] getSquares() {
		return squares;
	}
}
