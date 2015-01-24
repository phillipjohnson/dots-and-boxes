package game

import strategy.Strategy
import scala.annotation.tailrec

/**
 * Author: Phillip Johnson
 * Date: 1/20/15
 */
class Game(val strategy1:Strategy, val strategy2:Strategy) {
  val board = new Board(4, List.empty)

  val goesFirst = scala.util.Random.nextBoolean()
  val player1 = new Player(strategy1, 0, goesFirst)
  val player2 = new Player(strategy2, 0, !goesFirst)

  val gameState = new GameState(board, player1, player2)

  def play:GameState = gameLoop(gameState)

  @tailrec
  private def gameLoop(state:GameState):GameState = {
    if(state.isTerminal) return state
    val move = state.currentPlayer.pickPlay(state)
    val newState = state.makeMove(move)
    gameLoop(newState)
  }
}
