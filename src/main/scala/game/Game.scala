package game

/**
 * Author: Phillip Johnson
 * Date: 1/20/15
 */
class Game(val player1:Player, val player2:Player) {
  val board = new Board(4, List.empty)

  val gameState = new GameState(board, player1, player2)

  def play:GameState = gameLoop(gameState)

  private def gameLoop(state:GameState):GameState = {
    if(state.isTerminal) return state
    val move = state.currentPlayer.pickPlay(state)
    val newState = state.makeMove(move)

    gameLoop(newState)
  }
}
