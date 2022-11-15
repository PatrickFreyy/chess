package de.dhbw.ase.pieces;

import de.dhbw.ase.field.Square;

import java.util.Set;
import java.util.HashSet;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, 9);
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
                Square square = squares[currentSquare.getX() + i][currentSquare.getY() - i];
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
}
