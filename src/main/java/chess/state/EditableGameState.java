package chess.state;

import chess.bean.Player;
import chess.bean.Position;
import chess.pieces.Pawn;
import chess.pieces.Piece;

import java.util.HashMap;


public class EditableGameState extends GameState {

    public EditableGameState(HashMap<Position, Piece> positionPieceHashMap) {
        this.positionToPieceMap = positionPieceHashMap;
    }

    public void changeOwnership(Position pos) {
        Piece piece = positionToPieceMap.get(pos);
        positionToPieceMap.put(pos, new Pawn(piece.getOwner() == Player.Black ? Player.White : Player.Black));
    }

    public void removePiece(Position pos){
        positionToPieceMap.remove(pos);
    }


}
