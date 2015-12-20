package chess.move.rules;

import chess.state.GameState;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.ArrayList;
import java.util.List;


public class QueenMovementRule extends GenericMovementRule{

    private BishopMovementRule bishopMovementRule;
    private RookMovementRule rookMovementRule;

    public QueenMovementRule(GameState gameState,RookMovementRule rookMovementRule,BishopMovementRule bishopMovementRule) {
        super(gameState);
        this.rookMovementRule = rookMovementRule;
        this.bishopMovementRule = bishopMovementRule;
    }

    public List<PositionPair> generateMoves(Position currentPosition) {
        List<PositionPair> moves = new ArrayList<>();
        List<PositionPair> bishopMoves = bishopMovementRule.generateMoves(currentPosition);
        List<PositionPair> rookMoves = rookMovementRule.generateMoves(currentPosition);

        moves.addAll(bishopMoves);
        moves.addAll(rookMoves);

        return moves;
    }
}
