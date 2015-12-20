package chess.move.rules;

import chess.state.GameState;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.ArrayList;
import java.util.List;


public class KnightMovementRule extends GenericMovementRule{

    private int[][] offsets = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
    };

    public KnightMovementRule(GameState gameState) {
        super(gameState);
    }

    public List<PositionPair> generateMoves(Position currentPosition) {
        List<PositionPair> moves = new ArrayList<>();
        generateMovesForOffset(currentPosition, moves,offsets);

        return moves;
    }

}
