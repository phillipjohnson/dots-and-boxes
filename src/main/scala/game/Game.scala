package game

import strategy.{RandomPlay, Minimax}

/**
 * Author: Phillip Johnson
 * Date: 1/20/15
 */
class Game(val player1:Player, val player2:Player) {
  val board = new Board(3, List.empty)

  val gameState = new GameState(board, player1, player2)

  def play:GameState = gameLoop(gameState)

  private def gameLoop(state:GameState):GameState = {
    if(state.isTerminal) return state
    val move = state.currentPlayer.pickPlay(state)
    val newState = state.makeMove(move)

//    newState.board.print()

    gameLoop(newState)
  }

  private def gameOver(state:GameState):Boolean = {
    state.isTerminal
  }
}
