package game
import strategy.{Minimax, RandomPlay}
/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val strategy1 = new Minimax()
  val strategy2 = new RandomPlay()
  new Tournament(100, strategy1, strategy2).play()
}
