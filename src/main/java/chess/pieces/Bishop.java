package chess.pieces;


import chess.bean.Player;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
}
