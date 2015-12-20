package chess.state;


import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.pieces.*;

import java.util.*;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    protected Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    public boolean isEmpty(Position position) {
        return getPieceAt(position) == null;
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    protected void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    /**
     * Move!, and don't look back :)
     * @param move position where to move the figure
     */
    public void makeMove(PositionPair move) {
        Piece moved = positionToPieceMap.remove(move.getStart());
        positionToPieceMap.put(move.getEnd(), moved);
        //maybe changes current player in different place ?
        currentPlayer = currentPlayer == Player.Black ? Player.White : Player.Black;
    }

    /**
     * Creates editable game state tat is copy of current, changes to should not reflect to original game state
     * @return returns editable state with initial state of current state
     */
    public EditableGameState getEditableGameState() {
        return new EditableGameState(new HashMap<>(positionToPieceMap));
    }

    /**
     * Get pieces with positions for player
     * @param player player to get info for
     * @return pieces for specified player with their positions. changes to this map will not reflect to origin
     */
    public Map<Position, Piece> getBoardMapFor(Player player) {
        Map<Position, Piece> result = new HashMap<>();
        positionToPieceMap.entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getValue().getOwner() == player).forEach(positionPieceEntry -> {
            result.put(positionPieceEntry.getKey(), positionPieceEntry.getValue());
        });
        return result;
    }
}
