package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.ArrayList;
import java.util.List;


public class BishopMovementRule extends StraightMovementRule{

    public BishopMovementRule(GameState gameState) {
        super(gameState);
    }

    public List<PositionPair> generateMoves(Position currentPosition) {

        Player owner = gameState.getPieceAt(currentPosition).getOwner();

        List<PositionPair> moves = new ArrayList<>();
        int dx = 1, dy = 1;

        addStraightMovement(currentPosition, owner, moves,dx, dy);
        addStraightMovement(currentPosition, owner, moves, dx, -dy);
        addStraightMovement(currentPosition, owner, moves, -dx, dy);
        addStraightMovement(currentPosition, owner, moves, -dx, -dy);

        return moves;
    }

}
