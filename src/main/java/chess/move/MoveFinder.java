package chess.move;

import chess.state.EditableGameState;
import chess.state.GameState;
import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.pieces.*;
import chess.move.rules.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to find all possible moves for pieces
 */
public class MoveFinder {

    private GameState gameState;
    protected final Map<Class, MovementRule> movementRuleMap;

    public MoveFinder(GameState gameState) {
        this.gameState = gameState;
        movementRuleMap = new HashMap<>();
        BishopMovementRule bishopMovementRule = new BishopMovementRule(gameState);
        RookMovementRule rookMovementRule = new RookMovementRule(gameState);
        movementRuleMap.put(Bishop.class, bishopMovementRule);
        movementRuleMap.put(King.class, new KingMovementRule(gameState));
        movementRuleMap.put(Knight.class, new KnightMovementRule(gameState));
        movementRuleMap.put(Pawn.class, new PawnMovementRule(gameState, new PawnHitMovementRule(gameState)));
        movementRuleMap.put(Queen.class, new QueenMovementRule(gameState, rookMovementRule, bishopMovementRule));
        movementRuleMap.put(Rook.class, rookMovementRule);

    }

    private List<PositionPair> getPossibleMoves(Class<? extends Piece> piece, Position position) {
        return movementRuleMap.get(piece).generateMoves(position);
    }

    /**
     * returns all allowed moves for a specified player. Takes into account if king under check, so the only possible way to move a king, that;s simplification :)
     *
     * @param player player to check moves for
     * @return all allowed moves for a specified player
     */
    public List<PositionPair> getPossibleMovesFor(Player player) {
        return getMovesFor(player, true);
    }

    /**
     * return all allowed moves for a specified player
     *
     * @param player            player to check moves for
     * @param checkForKingCheck flag should we limit moves to avoid check
     * @return all allowed moves
     */
    public List<PositionPair> getMovesFor(Player player, boolean checkForKingCheck) {
        List<PositionPair> moves = new ArrayList<>();
        Map<Position, Piece> boardMapFor = gameState.getBoardMapFor(player);
        for (Map.Entry<Position, Piece> positionPieceEntry : boardMapFor.entrySet()) {
            List<PositionPair> possibleMoves = getPossibleMoves(positionPieceEntry.getValue().getClass(), positionPieceEntry.getKey());
            moves.addAll(possibleMoves);
        }

        //Let's check if we should limit our moves to moves that avoid check
        if (checkForKingCheck) {
            if (isKingCheck(boardMapFor, gameState, player)) {
                List<PositionPair> movesToAvoidCheck = new ArrayList<>();
                for (PositionPair move : moves) {
                    EditableGameState editableGameState = gameState.getEditableGameState();
                    editableGameState.makeMove(move);
                    if (!isKingCheck(editableGameState.getBoardMapFor(player), editableGameState, player)) {
                        movesToAvoidCheck.add(move);
                    }
                }
                return movesToAvoidCheck;
            }
        }

        return moves;
    }

    private boolean isKingCheck(Map<Position, Piece> boardMapFor, GameState currentGameState, Player player) {
        for (Map.Entry<Position, Piece> positionPieceEntry : boardMapFor.entrySet()) {
            if (positionPieceEntry.getValue().getClass().equals(King.class)) {
                final List<PositionPair> enemyMoves = new HitMoveFinder(currentGameState).getEnemyHitMoves(player);
                for (PositionPair enemyMove : enemyMoves) {
                    if (enemyMove.getEnd().equals(positionPieceEntry.getKey())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}


