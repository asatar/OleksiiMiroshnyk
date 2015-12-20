package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;


public class PawnHitMovementRule extends GenericMovementRule {

    public PawnHitMovementRule(GameState gameState) {
        super(gameState);
    }

    @Override
    public List<PositionPair> generateMoves(Position currentPosition) {
        int direction = gameState.getCurrentPlayer() == Player.White ? 1 : -1;
        List<PositionPair> moves = new ArrayList<>();
        Player owner = gameState.getPieceAt(currentPosition).getOwner();
        Position hitLeft = currentPosition.move(-1, direction);
        addHitMove(currentPosition, moves, hitLeft, owner);
        Position hitRight = currentPosition.move(1, direction);
        addHitMove(currentPosition, moves, hitRight, owner);
        return moves;
    }

    private void addHitMove(Position currentPosition, List<PositionPair> moves, Position hitLeft, Player owner) {
        Piece underAttack = gameState.getPieceAt(hitLeft);
        if (underAttack != null && underAttack.getOwner() != owner) {
            moves.add(new PositionPair(currentPosition, hitLeft));
        }
    }

}
