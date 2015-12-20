package chess.move.rules;

import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.move.HitMoveFinder;
import chess.pieces.Piece;
import chess.state.EditableGameState;
import chess.state.GameState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class KingMovementRule extends GenericMovementRule {

    private int[][] offsets = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {-1, 1},
            {-1, -1},
            {1, -1}
    };

    public KingMovementRule(GameState gameState) {
        super(gameState);
    }

    public List<PositionPair> generateMoves(Position currentPosition) {
        Player owner = gameState.getPieceAt(currentPosition).getOwner();

        List<PositionPair> moves = new ArrayList<>();
        generateMovesForOffset(currentPosition, moves, offsets);

        if (owner == gameState.getCurrentPlayer()) {
            EditableGameState editableGameState = gameState.getEditableGameState();
            editableGameState.removePiece(currentPosition);
            //need to delete those moves that lead under hit of enemy
            cleanUpMoves(owner, moves, editableGameState);

            //Ok, let's imagine that targets that can hit King will change to our allies, so enemy will be able to hit them
            for (PositionPair move : moves) {
                Piece pieceAt = gameState.getPieceAt(move.getEnd());
                if (pieceAt != null) {
                    editableGameState.changeOwnership(move.getEnd());
                }
            }

            //clean up those hits that are protected
            cleanUpMoves(owner, moves, editableGameState);
        }
        return moves;
    }

    private void cleanUpMoves(Player owner, List<PositionPair> moves, EditableGameState editableGameState) {
        final List<PositionPair> enemyMoves = new HitMoveFinder(editableGameState).getEnemyHitMoves(owner);
        for (PositionPair enemyMove : enemyMoves) {
            for (Iterator<PositionPair> iterator = moves.iterator(); iterator.hasNext(); ) {
                PositionPair move = iterator.next();
                if (enemyMove.getEnd().equals(move.getEnd())) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Will check if board to rook is empty, rook is on its place and no enemy hits on our road
     */
    private void addCastlingMoves(){
       throw new NotImplementedException();
    }
}
