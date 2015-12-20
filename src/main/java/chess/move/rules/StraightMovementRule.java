package chess.move.rules;

import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;

import java.util.List;


public abstract class StraightMovementRule extends GenericMovementRule{

    public StraightMovementRule(GameState gameState) {
        super(gameState);
    }

    public void addStraightMovement(Position currentPosition, Player owner, List<PositionPair> moves, int dx, int dy) {
        Position position;
        int i=dx;
        int j=dy;
        while ((position = currentPosition.move(i, j)) != null) {
            if(addIfEmptyOrEnemy(currentPosition,moves, owner, position)!=null){
                break;
            }
            i+=dx;
            j+=dy;
        }
    }
}
