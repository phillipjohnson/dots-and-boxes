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
      } yield move -> minimax(state.makeMove(move))
    }
    println(moveScores)
    //println(minimax(state))
    val bestMove = moveScores.minBy(_._2)._1
    val bestScore = moveScores.minBy(_._2)._2
   // println("BEST " + bestScore + " :: "  + bestMove)
    bestMove
    //Random.shuffle(state.availableMoves).head
  }

  private def minimax(state:GameState):Int = {
    minimize(state, Integer.MIN_VALUE, Integer.MAX_VALUE)
  }

  private def minimize(state:GameState, alpha:Int, beta:Int):Int = {
    if(state.isTerminal) {//println("min TERM " + state.hVal);
      return state.hVal}
    //println("Player " + state.currentPlayer + " is minimizing.")
    var newBeta = beta
    state.availableMoves.foreach(move => {
      val newState = state.makeMove(move)
//      if(newState.currentPlayer == state.currentPlayer) {
//        //println("hit")
//        minimize(newState, alpha, beta)
//      } else {
        newBeta = math.min(beta, maximize(newState, alpha, newBeta))
        if (alpha >= newBeta) return alpha
      //}
    })
    //println("" + state.currentPlayer + " MIN A: " + alpha + " B: " + newBeta)
    newBeta
  }

  private def maximize(state:GameState, alpha:Int, beta:Int):Int = {
    if(state.isTerminal) {//println("max TERM " + state.hVal);
      return state.hVal}
    //println("Player " + state.currentPlayer + " is maximizing.")
    var newAlpha = alpha
    for(move <- state.availableMoves) {
      val newState = state.makeMove(move)
//      if(newState.currentPlayer == state.currentPlayer) {
//        maximize(newState, alpha, beta)
//      } else {
        newAlpha = math.max(newAlpha, minimize(newState, newAlpha, beta))
        if (newAlpha >= beta) return beta
//      }
    }
    //println("" + state.currentPlayer + " MAX A: " + newAlpha + " B: " + beta)
    newAlpha
  }

  override def toString = "MINIMAX"
}
