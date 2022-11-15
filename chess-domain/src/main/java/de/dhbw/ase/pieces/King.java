package de.dhbw.ase.pieces;

import de.dhbw.ase.exceptions.MoveException;
import de.dhbw.ase.field.Square;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    private boolean firstMove;

    public King(Color color) {
        super(color, -1);
        this.firstMove = true;
    }

    @Override
    public Square[][] move(Square[][] squares, Square currentSquare, Square targetSquare) throws MoveException{
        if (possibleMoves(squares, currentSquare).contains(targetSquare)) {
            //check if move is castling. Castling rules are checked in castling-Methods
            if (targetSquare.getX() - currentSquare.getX() == 2 && castlingKingsSide(squares, currentSquare)) {
                //move King
                squares[targetSquare.getX()][targetSquare.getY()].putPiece(this);
                squares[currentSquare.getX()][currentSquare.getY()].removePiece();
                //move Rook
                squares[targetSquare.getX() - 1][currentSquare.getY()].putPiece(squares[currentSquare.getX() + 3][currentSquare.getY()].getPiece());
                squares[currentSquare.getX() + 3][currentSquare.getY()].removePiece();
            } else if (targetSquare.getX() - currentSquare.getX() == -2 && castlingQueensSide(squares, currentSquare)) {
                //move King
                squares[targetSquare.getX()][targetSquare.getY()].putPiece(this);
                squares[currentSquare.getX()][currentSquare.getY()].removePiece();
                //move Rook
                squares[targetSquare.getX() + 1][currentSquare.getY()].putPiece(squares[currentSquare.getX() - 4][currentSquare.getY()].getPiece());
                squares[currentSquare.getX() - 4][currentSquare.getY()].removePiece();
            } else {
                squares[currentSquare.getX()][currentSquare.getY()].removePiece();
                squares[targetSquare.getX()][targetSquare.getY()].putPiece(this);
            }
        } else {
            throw new MoveException();
        }
        setFirstMove();
        return squares;
    }

    @Override
    public Set<Square> possibleMoves(Square[][] squares, Square currentSquare) {
        Set<Square> possibleSquares = new HashSet<>();
        Set<Square> attackedSquares = new HashSet<>();
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        //King cant move onto an attacked Square. Therefore, all attacked Squares are calculated.
        //TODO: Für jedes Feld ein Thread, der Figur und Figur.possileMoves abfrägt. Da nur read, kein write, vollständig parallelisierbar
        for (Square[] squares1 : squares) {
            for (Square square : squares1) {
                //leere Felder werden abgefiltert
                if (!square.isOccupied() && square.getColor() != color) {
                    attackedSquares.addAll(square.getPiece().possibleMoves(squares, square));
                }
            }
        }
        //Each Square is possible only when it is not attacked by another Piece
        if (!attackedSquares.contains(squares[x + 1][y + 1])) {
            possibleSquares.add(squares[x + 1][y + 1]);
        }
        if (!attackedSquares.contains(squares[x][y + 1])) {
            possibleSquares.add(squares[x][y + 1]);
        }
        if (!attackedSquares.contains(squares[x - 1][y])) {
            possibleSquares.add(squares[x - 1][y + 1]);
        }
        if (!attackedSquares.contains(squares[x - 1][y])) {
            possibleSquares.add(squares[x - 1][y]);
        }
        if (!attackedSquares.contains(squares[x - 1][y - 1])) {
            possibleSquares.add(squares[x - 1][y - 1]);
        }
        if (!attackedSquares.contains(squares[x][y - 1])) {
            possibleSquares.add(squares[x][y - 1]);
        }
        if (!attackedSquares.contains(squares[x + 1][y - 1])) {
            possibleSquares.add(squares[x + 1][y - 1]);
        }
        if (!attackedSquares.contains(squares[x + 1][y])) {
            possibleSquares.add(squares[x + 1][y]);
        }
        //add castling as possible Move
        if (firstMove && castlingKingsSide(squares, currentSquare)) {
            possibleSquares.add(squares[currentSquare.getX() + 2][currentSquare.getY()]);
        }
        if (firstMove && castlingQueensSide(squares, currentSquare)) {
            possibleSquares.add(squares[currentSquare.getX() - 2][currentSquare.getY()]);
        }
        return possibleSquares;
    }

    public boolean castlingQueensSide(Square[][] squares, Square currentSquare) {
        //castling only allowed, if King hasn't moved
        if (firstMove) {
            boolean castlingPossible = true;
            //can't castle over an attacked or occupied Square
            Set<Square> attackedSquares = new HashSet<>();
            for (Square[] line : squares) {
                for (Square square : line) {
                    if (square.isOccupied()) {
                        attackedSquares.addAll(square.getPiece().possibleMoves(squares, currentSquare));
                    }
                }
            }
            //Squares between King and Rook can't be attacked
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX()][currentSquare.getY()]);
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX() - 1][currentSquare.getY()]);
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX() - 2][currentSquare.getY()]);
            //Squares between King and Rook can't be occupied
            castlingPossible = !squares[currentSquare.getX() - 1][currentSquare.getY()].isOccupied();
            castlingPossible = !squares[currentSquare.getX() - 2][currentSquare.getY()].isOccupied();
            castlingPossible = !squares[currentSquare.getX() - 3][currentSquare.getY()].isOccupied();
            //Rook can't have moved yet
            try {
                Rook rook = (Rook) squares[currentSquare.getX() - 4][currentSquare.getY()].getPiece();
                castlingPossible = rook.getFirstMove();
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
            return castlingPossible;
        } else {
            return false;
        }
    }

    public boolean castlingKingsSide(Square[][] squares, Square currentSquare) {
        //castling only allowed, if King hasn't moved
        if (firstMove) {
            boolean castlingPossible = true;
            //can't castle over an attacked or occupied Square
            Set<Square> attackedSquares = new HashSet<>();
            for (Square[] line : squares) {
                for (Square square : line) {
                    if (square.isOccupied()) {
                        attackedSquares.addAll(square.getPiece().possibleMoves(squares, currentSquare));
                    }
                }
            }
            //Squares between King and Rook can't be attacked
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX()][currentSquare.getY()]);
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX() + 1][currentSquare.getY()]);
            castlingPossible = !attackedSquares.contains(squares[currentSquare.getX() + 2][currentSquare.getY()]);
            //Squares between King and Rook can't be occupied
            castlingPossible = !squares[currentSquare.getX() + 1][currentSquare.getY()].isOccupied();
            castlingPossible = !squares[currentSquare.getX() + 2][currentSquare.getY()].isOccupied();
            //Rook can't have moved yet
            try {
                Rook rook = (Rook) squares[currentSquare.getX() + 3][currentSquare.getY()].getPiece();
                castlingPossible = rook.getFirstMove();
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
            return castlingPossible;
        } else {
            return false;
        }
    }

    //---getter/setter

    public void setFirstMove() {
        this.firstMove = false;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }
}
