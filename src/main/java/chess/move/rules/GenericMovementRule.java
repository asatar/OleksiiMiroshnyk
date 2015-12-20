package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.pieces.Piece;

import java.util.List;


public abstract class GenericMovementRule implements MovementRule{

    protected GameState gameState;

    public GenericMovementRule(GameState gameState) {
        this.gameState = gameState;
    }

    public Piece addIfEmptyOrEnemy(Position currentPosition, List<PositionPair> moves, Player owner, Position position) {
        Piece pieceAt = gameState.getPieceAt(position);
        if (pieceAt == null || pieceAt.getOwner() != owner) {
            moves.add(new PositionPair(currentPosition, position));
        }
        return pieceAt;
    }

    public void generateMovesForOffset(Position currentPosition, List<PositionPair> moves, int[][] offsets) {
        Player owner = gameState.getPieceAt(currentPosition).getOwner();
        for (int[] o : offsets) {
            Position position = currentPosition.move(o[0], o[1]);
            if (position != null) {
                addIfEmptyOrEnemy(currentPosition, moves, owner, position);
            }
        }
    }

}
