package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.ArrayList;
import java.util.List;


public class PawnMovementRule extends GenericMovementRule{

    private static final int WHITE_START_ROW = 2;
    private static final int BLACK_START_ROW = 7;
    private PawnHitMovementRule pawnHitMovementRule;

    public PawnMovementRule(GameState gameState, PawnHitMovementRule pawnMovementRule) {
        super(gameState);
        this.pawnHitMovementRule = pawnMovementRule;
    }

    public List<PositionPair> generateMoves(Position currentPosition) {
        List<PositionPair> moves = new ArrayList<>();

        int direction = gameState.getCurrentPlayer() == Player.White ? 1 : -1;

        Position position = currentPosition.move(0, direction);
        if (position != null && gameState.isEmpty(position)) {
            moves.add(new PositionPair(currentPosition, position));
        }
        //maybe we can even do single step?
        if (moves.size()>0 && canDoGiantStep(currentPosition)) {
            Position giantStep = currentPosition.move(0, direction * 2);
            if (position != null && gameState.isEmpty(giantStep)) {
                moves.add(new PositionPair(currentPosition, giantStep));
            }
        }
        List<PositionPair> positionPairs = pawnHitMovementRule.generateMoves(currentPosition);
        moves.addAll(positionPairs);

        return moves;
    }


    private boolean canDoGiantStep(Position currentPosition) {
        return (gameState.getCurrentPlayer() == Player.White && currentPosition.getRow() == WHITE_START_ROW) ||
                (gameState.getCurrentPlayer() == Player.Black && currentPosition.getRow() == BLACK_START_ROW);
    }
}
