package game
import strategy.{Minimax, RandomPlay}
/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val board = new Board(3, List.empty)

  val player1 = new Player(new Minimax())
  val player2 = new Player(new RandomPlay())

  val gameState = new GameState(board, 0, 0)

  var currentPlayer = player1

  def gameLoop(state:GameState, currentPlayer:Player):GameState = {
    if(state.isTerminal) return state
    val initialSquares = state.completedSquares
    val move = currentPlayer.pickPlay(state)
    var playerNumber = -1
    if(currentPlayer == player1) playerNumber = 1 else playerNumber = 2
    val newState = state.makeMove(move, playerNumber) //TODO: This can be better
    val newSquares = newState.completedSquares

    println("Player " + currentPlayer.strategy)
    newState.board.print()

    var nextPlayer = player1
    if(newSquares == initialSquares) {
      //We only change players if no box was completed
      if(currentPlayer == player1) nextPlayer = player2 else nextPlayer = player1
    } else {
      val score = newSquares - initialSquares
      currentPlayer.completeBox(score)
      nextPlayer = currentPlayer
    }
    gameLoop(newState, nextPlayer)
  }

  def gameOver(state:GameState):Boolean = {
    state.isTerminal
  }

  gameLoop(gameState, player1)

  println(player1.score)
  println(player2.score)
}
