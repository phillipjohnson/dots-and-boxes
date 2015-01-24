package strategy

import game.GameState

/**
 * Author: Phillip Johnson
 * Date: 1/23/15
 */
class MinimaxNaive extends Strategy {

  val maxDepth = 3

  def play(state:GameState):(Int, Int) = {
    val moveScores = {
      for {
        move <- state.availableMoves
      } yield move -> minimax(state.makeMove(move))
    }
    val bestMove = moveScores.minBy(_._2)._1
    bestMove
  }

  private def minimax(state:GameState):Int = {
    minimize(state, maxDepth)
  }

  private def minimize(state:GameState, depth:Int):Int = {
    if(state.isTerminal || depth == 0) return state.hVal
    var bestResult = Integer.MAX_VALUE
    state.availableMoves.foreach(move => {
      val newState = state.makeMove(move)
      val bestChildResult = maximize(newState, depth - 1)
      bestResult = math.min(bestResult, bestChildResult)
    })
    bestResult
  }

  private def maximize(state:GameState, depth:Int):Int = {
    if(state.isTerminal || depth == 0) return state.hVal
    var bestResult = Integer.MIN_VALUE
    state.availableMoves.foreach(move => {
      val newState = state.makeMove(move)
      val bestChildResult = minimize(newState, depth - 1)
      bestResult = math.max(bestResult, bestChildResult)
    })
    bestResult
  }

  override def toString = "MINIMAX NAIVE"
}
