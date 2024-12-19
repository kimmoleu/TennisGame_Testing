import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();
	}
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player 1 win score incorrect", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player 2 win score incorrect", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_Player1WinAfterDeuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player 1 win after deuce failed", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2WinAfterDeuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player 2 win after deuce failed", "player2 wins", score);
	}
	
	@Test 
	public void testTennisGame_Player1Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player1 advantage score incorrect", "player1 has advantage", score);
	}
	
	@Test 
	public void testTennisGame_Player2Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Player2 advantage score incorrect", "player2 has advantage", score);
	}
	
	@Test 
	public void testTennisGame_Score0To1() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("love - 15 score incorrect", "love - 15", score);
	}
	
	@Test 
	public void testTennisGame_Score2To3() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("30 - 40 score incorrect", "30 - 40", score);
	}
}
