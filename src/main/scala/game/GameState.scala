package game

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class GameState(val board:Board, val player1Score:Int, val player2Score:Int) {

  val completedSquares = board.completedSquares

  def availableMoves = board.availableMoves

  def isTerminal = board.availableMoves.isEmpty

  def hVal = player1Score - player2Score

  def makeMove(move:(Int, Int), player:Int):GameState = {
    val newBoard = board.play(move)
    val score = newBoard.completedSquares - completedSquares
    if(player == 1) new GameState(board.play(move), player1Score + score, player2Score)
    else new GameState(board.play(move), player1Score, player2Score + score)
  }
}
