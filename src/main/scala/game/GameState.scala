package game

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class GameState(val board:Board, val player1:Player, val player2:Player) {

  val completedSquares = board.completedSquares

  def availableMoves = board.availableMoves

  def isTerminal = board.availableMoves.isEmpty

  def hVal = {
    if(player1.isCurrentPlayer) player1.score - player2.score
    else player2.score - player1.score
  }

  def makeMove(move:(Int, Int)):GameState = {
    val newBoard = board.play(move)
    val score = newBoard.completedSquares - completedSquares
    if(score == 0) {
      new GameState(board.play(move), player1.drawLine, player2.drawLine)
    } else {
      if(player1.isCurrentPlayer) {
        new GameState(board.play(move), player1.completeBox(score), player2.completeBox(0))
      } else {
        new GameState(board.play(move), player1.completeBox(0), player2.completeBox(score))
      }
    }
  }

  def currentPlayer = {
    if(player1.isCurrentPlayer) player1 else player2
  }
}
