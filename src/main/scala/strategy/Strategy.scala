package strategy
import game.Board

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
trait Strategy {
  def play(b:Board):(Int, Int)
}
