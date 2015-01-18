package game
import strategy.RandomPlay
/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val board = new Board(4)

  def gameOver(state:Board):Boolean = {
    state.availableMoves.isEmpty
  }

  val player1 = new Player(new RandomPlay())
  val player2 = new Player(new RandomPlay())

  var currentPlayer = player1

  while(!gameOver(board)){
    val initialSquares = board.completedSquares
    board.play(currentPlayer.pickPlay(board))
    val newSquares = board.completedSquares
    if(newSquares == initialSquares) {
      //We only change players if no box was completed
      if(currentPlayer == player1) currentPlayer = player2 else currentPlayer = player1
    } else {
      val score = newSquares - initialSquares
      currentPlayer.completeBox(score)
    }
    board.print
  }

  println(player1.score)
  println(player2.score)
}
