package strategy

import org.scalatest.FunSuite
import game.{Player, GameState, Board}

/**
 * Author: Phillip Johnson
 * Date: 1/22/15
 */
class MinimaxTest extends FunSuite {
  test("Completes max boxes") {
    var board = new Board(3, List.empty)
    board = board.play((0,1))
    board = board.play((1,2))
    board = board.play((0,3))
    board = board.play((1,4))
    board = board.play((2,5))
    board = board.play((3,4))

    val player1 = new Player(new Minimax(), 0, true)
    val player2 = new Player(new Minimax(), 0, false)
    val gameState = new GameState(board, player1, player2)

    assert(new Minimax().play(gameState) == (4,5))
  }
}
