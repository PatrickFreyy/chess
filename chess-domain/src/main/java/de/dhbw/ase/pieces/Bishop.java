package de.dhbw.ase.pieces;

import de.dhbw.ase.field.Square;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, 3);
    }

    @Override
    public Set<Square> possibleMoves(Square[][] squares, Square currentSquare) {
        Set<Square> possibleSquares = new HashSet<>();
        //one for-loop calculates all legal moves in one direction. If a square is occupied, the direction is not further persued, since the piece cant jump over other pieces
        for (int i = 1; i < 8; i++) {
            try {
                Square square = squares[currentSquare.getX() + i][currentSquare.getY() + i];
                possibleSquares.add(square);
                if (square.isOccupied()) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                Square square = squares[currentSquare.getX() - i][currentSquare.getY() + i];
                possibleSquares.add(square);
                if (square.isOccupied()) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                Square square = squares[currentSquare.getX() - i][currentSquare.getY() - i];
                possibleSquares.add(square);
                if (square.isOccupied()) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        for (int i = 1; i < 8; i++) {
            try {
                Square square = squares[currentSquare.getX() + i][currentSquare.getY() - i];
                possibleSquares.add(square);
                if (square.isOccupied()) {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        return possibleSquares;
    }

}
