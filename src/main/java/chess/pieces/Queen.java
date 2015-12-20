package chess.pieces;


import chess.bean.Player;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
}
