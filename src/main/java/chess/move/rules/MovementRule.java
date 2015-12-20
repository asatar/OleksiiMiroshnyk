package chess.move.rules;

import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.List;


public interface MovementRule {
    List<PositionPair> generateMoves(Position currentPosition);
}
