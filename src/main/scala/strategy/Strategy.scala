package strategy
import game.{GameState, Board}

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
trait Strategy {
  def play(state:GameState):(Int, Int)
}
