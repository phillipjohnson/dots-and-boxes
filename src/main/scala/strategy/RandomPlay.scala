package strategy

import game.{GameState, Board}
import scala.util.Random

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class RandomPlay extends Strategy {

  def play(state:GameState):(Int,Int) = {
    Random.shuffle(state.availableMoves).head
  }

  override def toString = "RANDO"
}
