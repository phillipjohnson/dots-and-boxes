package strategy

import game.GameState

/**
 * Author: Phillip Johnson
 * Date: 2/8/15
 */
class GreedyBoxMaker extends Strategy {
  override def play(state: GameState): (Int, Int) = {
    val current = state.completedSquares
    val moves = for {
      move <- state.availableMoves
      newBoxes = state.makeMove(move).completedSquares
      if newBoxes > current
    } yield move -> newBoxes
    if(moves.nonEmpty) {
      moves.maxBy(_._2)._1
    } else {
      new RandomPlay().play(state)
    }
  }

  override def toString = "GREEDY"
}
