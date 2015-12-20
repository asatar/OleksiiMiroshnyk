package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.ArrayList;
import java.util.List;


public class RookMovementRule extends StraightMovementRule {

    public RookMovementRule(GameState gameState) {
        super(gameState);
    }

    public List<PositionPair> generateMoves(Position currentPosition) {

        Player owner = gameState.getPieceAt(currentPosition).getOwner();

        List<PositionPair> moves = new ArrayList<>();
        int i = 1, j = 1;
        int dx = 1, dy = 1;

        addStraightMovement(currentPosition, owner, moves, 0, dy);
        addStraightMovement(currentPosition, owner, moves, 0, -dy);
        addStraightMovement(currentPosition, owner, moves, -dx, 0);
        addStraightMovement(currentPosition, owner, moves,  dx, 0);

        return moves;
    }
}
