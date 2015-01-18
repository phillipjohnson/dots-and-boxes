import scala.util.Random

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class Player() {
  var score = 0

  def completeBox() = score += 1

  def pickPlay(state:Board):(Int,Int) = {
    Random.shuffle(state.availableMoves).head
  }
}
