package chess;

import chess.bean.Player;
import chess.bean.Position;
import chess.bean.PositionPair;
import chess.move.MoveFinder;
import chess.pieces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MoveFinderTest {

    @Test
    public void checkKingMoves() {
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new King(Player.White), new Position("b2"));
        gameState.put(new Rook(Player.Black), new Position("a1"));
        gameState.put(new Rook(Player.Black), new Position("h1"));

        List<PositionPair> movesFor = new MoveFinder(gameState).getMovesFor(Player.White, false);
        movesFor.sort((p1, p2) -> p1.getStart().toString().compareTo(p2.getStart().toString()));
        Assert.assertEquals(Arrays.asList(new PositionPair("b2", "c2"), new PositionPair("b2", "b3"), new PositionPair("b2", "c3")), movesFor);
    }

    @Test
    public void checkKingUnderCheck() {
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new King(Player.White), new Position("b2"));
        gameState.put(new Queen(Player.White), new Position("g8"));
        gameState.put(new Rook(Player.Black), new Position("a1"));
        gameState.put(new Rook(Player.Black), new Position("h2"));
        gameState.put(new Rook(Player.Black), new Position("h1"));

        List<PositionPair> movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        movesFor.sort((p1, p2) -> p1.getStart().toString().compareTo(p2.getStart().toString()));
        Assert.assertEquals(Arrays.asList(new PositionPair("b2", "b3"), new PositionPair("b2", "c3"), new PositionPair("g8","g2")), movesFor);
    }

    @Test
    public void checkCheckmate() {
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new King(Player.White), new Position("b1"));
        gameState.put(new Bishop(Player.White), new Position("g8"));
        gameState.put(new Rook(Player.Black), new Position("h2"));
        gameState.put(new Rook(Player.Black), new Position("h1"));

        List<PositionPair> movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        movesFor.sort((p1, p2) -> p1.getStart().toString().compareTo(p2.getStart().toString()));
        Assert.assertEquals(0,movesFor.size());
    }

    @Test
    public void checkPawnMoves() {
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new Pawn(Player.White), new Position("b2"));
        gameState.put(new Rook(Player.Black), new Position("a3"));
        gameState.put(new Rook(Player.Black), new Position("c3"));
        List<PositionPair> movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        movesFor.sort((p1, p2) -> p1.getStart().toString().compareTo(p2.getStart().toString()));
        Assert.assertEquals(Arrays.asList(new PositionPair("b2", "b3"), new PositionPair("b2", "b4"),new PositionPair("b2", "a3"), new PositionPair("b2", "c3")), movesFor);

        gameState.clearBoard();
        gameState.put(new Pawn(Player.White), new Position("b2"));
        gameState.put(new Rook(Player.Black), new Position("b3"));
        movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        Assert.assertEquals(0,movesFor.size());

        gameState.clearBoard();
        gameState.put(new Pawn(Player.White), new Position("b3"));
        movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        Assert.assertEquals(Arrays.asList(new PositionPair("b3", "b4")), movesFor);
    }


    @Test
    public void checkRookBishopQueenMoves(){
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new Bishop(Player.White), new Position("a1"));
        List<PositionPair> movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        List<PositionPair> bishopPositionPairs = Arrays.asList(new PositionPair("a1", "b2"),
                new PositionPair("a1", "c3"),
                new PositionPair("a1", "d4"),
                new PositionPair("a1", "e5"),
                new PositionPair("a1", "f6"),
                new PositionPair("a1", "g7"),
                new PositionPair("a1", "h8")
        );
        Assert.assertEquals(bishopPositionPairs,movesFor);
        gameState.clearBoard();
        gameState.put(new Rook(Player.White), new Position("a1"));
        movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        List<PositionPair> rookPositionPairs = Arrays.asList(new PositionPair("a1", "a2"),
                new PositionPair("a1", "a3"),
                new PositionPair("a1", "a4"),
                new PositionPair("a1", "a5"),
                new PositionPair("a1", "a6"),
                new PositionPair("a1", "a7"),
                new PositionPair("a1", "a8"),
                new PositionPair("a1", "b1"),
                new PositionPair("a1", "c1"),
                new PositionPair("a1", "d1"),
                new PositionPair("a1", "e1"),
                new PositionPair("a1", "f1"),
                new PositionPair("a1", "g1"),
                new PositionPair("a1", "h1")
        );
        Assert.assertEquals(rookPositionPairs,movesFor);
        gameState.clearBoard();
        gameState.put(new Queen(Player.White), new Position("a1"));
        movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        List<PositionPair> queenMoves = new ArrayList<>();
        queenMoves.addAll(bishopPositionPairs);
        queenMoves.addAll(rookPositionPairs);
        Assert.assertEquals(queenMoves,movesFor);

    }

    @Test
    public void checkKnightMoves(){
        MockGameState gameState = new MockGameState();
        gameState.clearBoard();
        gameState.put(new Knight(Player.White), new Position("a1"));
        List<PositionPair> movesFor = new MoveFinder(gameState).getPossibleMovesFor(Player.White);
        Assert.assertEquals(Arrays.asList(new PositionPair("a1","b3"),new PositionPair("a1","c2")),movesFor);
    }

}