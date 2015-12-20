package chess;

import chess.bean.Position;
import chess.pieces.Piece;
import chess.state.GameState;

import java.util.HashMap;


public class MockGameState extends GameState {

    public void clearBoard(){
        positionToPieceMap = new HashMap<>();
    }

    public void put(Piece piece, Position position){
        placePiece(piece, position);
    }


}
