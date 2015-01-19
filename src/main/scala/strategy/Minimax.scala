package strategy

import game.{GameState, DotsAndBoxesApp, Board}
import scala.util.Random

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class Minimax extends Strategy {
  def play(state:GameState):(Int, Int) = {
    val moveScores = {
      for {
        move <- state.availableMoves
      } yield move -> minimax(state.makeMove(move, 1)) //TODO: works only for player 1
    }
    moveScores.maxBy(_._2)._1
    //println(minimax(state))
    //Random.shuffle(state.availableMoves).head
  }

  private def minimax(state:GameState):Int = {
    maximize(state, Integer.MIN_VALUE, Integer.MAX_VALUE)
  }

  private def minimize(state:GameState, alpha:Int, beta:Int):Int = {
    if(state.isTerminal) {//println("min TERM");
     return state.hVal}
    var newBeta = beta
    for(move <- state.availableMoves) {
      val newState = state.makeMove(move, 2)
      newBeta = math.min(beta, maximize(newState, alpha, newBeta))
      if(alpha >= newBeta) return alpha
    }
    //println("MIN A: " + alpha + " B: " + newBeta)
    newBeta
  }

  private def maximize(state:GameState, alpha:Int, beta:Int):Int = {
    if(state.isTerminal) {//println("max TERM");
     return state.hVal}
    var newAlpha = alpha
    for(move <- state.availableMoves) {
      val newState = state.makeMove(move, 1)
      newAlpha = math.max(newAlpha, minimize(newState, newAlpha, beta))
      if(newAlpha >= beta) return beta
    }
    //println("MAX A: " + newAlpha + " B: " + beta)
    newAlpha
  }

  override def toString = "MINIMAX"
}
