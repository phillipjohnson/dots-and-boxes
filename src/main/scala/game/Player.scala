package game

import strategy.Strategy

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class Player(val strategy:Strategy, val score:Int, val isCurrentPlayer:Boolean) {

  def pickPlay(state:GameState):(Int,Int) = {
    strategy.play(state)
  }

  def completeBox(score:Int):Player = new Player(strategy, this.score + score, isCurrentPlayer)

  def drawLine:Player = new Player(strategy, score, !isCurrentPlayer)

  override def toString = strategy.toString
}
