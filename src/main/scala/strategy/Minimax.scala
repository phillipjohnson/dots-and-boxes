package strategy

import game.GameState

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class Minimax extends Strategy {

  private val maxDepth = 5

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
    minimize(state, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE)
  }

  private def minimize(state:GameState, depth:Int, alpha:Int, beta:Int):Int = {
    if(state.isTerminal || depth == 0) return state.hVal
    var newBeta = beta
    state.availableMoves.foreach(move => {
      val newState = state.makeMove(move)
        newBeta = math.min(beta, maximize(newState, depth - 1, alpha, newBeta))
        if (alpha >= newBeta) return alpha
    })
    newBeta
  }

  private def maximize(state:GameState, depth:Int, alpha:Int, beta:Int):Int = {
    if(state.isTerminal || depth == 0) return state.hVal
    var newAlpha = alpha
    for(move <- state.availableMoves) {
      val newState = state.makeMove(move)
        newAlpha = math.max(newAlpha, minimize(newState, depth - 1, newAlpha, beta))
        if (newAlpha >= beta) return beta
    }
    newAlpha
  }

  override def toString = "MINIMAX"
}
