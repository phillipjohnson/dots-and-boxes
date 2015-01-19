package game

import strategy.Strategy

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class Player(val strategy:Strategy) {
  var score = 0

  def completeBox(boxes:Int) = score += boxes

  def pickPlay(state:GameState):(Int,Int) = {
    strategy.play(state)
  }
}
