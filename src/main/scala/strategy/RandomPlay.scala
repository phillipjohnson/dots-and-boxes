package strategy

import game.Board
import scala.util.Random

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class RandomPlay extends Strategy {

  def play(state:Board):(Int,Int) = {
    Random.shuffle(state.availableMoves).head
  }
}
