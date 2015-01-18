import scala.util.Random

/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val board = new Board(4)

  def gameOver(state:Board):Boolean = {
    state.availableMoves.isEmpty
  }

  val player1 = new Player()
  val player2 = new Player()

  var currentPlayer = player1

  while(!gameOver(board)){
    val initialSquares = board.completedSquares
    board.play(currentPlayer.pickPlay(board))
    val newSquares = board.completedSquares
    if(newSquares == initialSquares) {
      if(currentPlayer == player1) player2 else player1
    } else {
      currentPlayer.completeBox()
    }
    board.print
  }

  println(player1.score)
  println(player2.score)
}
