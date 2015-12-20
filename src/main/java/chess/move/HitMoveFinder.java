package chess.move;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.PositionPair;
import chess.pieces.*;
import chess.move.rules.*;

import java.util.List;

/**
 * Class to find all possible "hit" moves
 */
public class HitMoveFinder extends MoveFinder{

    public HitMoveFinder(GameState editableGameState) {
        super(editableGameState);
        movementRuleMap.put(Pawn.class,new PawnHitMovementRule(editableGameState));
    }

    /**
     * returns all possible hit moves of opposite player
     * @param player current player
     * @return all possible hit moves
     */
    public List<PositionPair> getEnemyHitMoves(Player player) {
        return getMovesFor(player==Player.Black?Player.White:Player.Black, false);
    }

}
