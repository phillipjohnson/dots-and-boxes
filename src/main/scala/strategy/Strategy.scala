package strategy
import game.GameState

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
trait Strategy {
  def play(state:GameState):(Int, Int)
}
