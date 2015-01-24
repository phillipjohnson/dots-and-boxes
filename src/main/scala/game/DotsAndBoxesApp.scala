package game
import strategy.{MinimaxNaive, Minimax, RandomPlay}
/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val strategy1 = new Minimax()
  val strategy2 = new RandomPlay()
  val start = System.nanoTime()
  new Tournament(10, strategy1, strategy2).play()
  val end = System.nanoTime()
  println("Total time: " + (end - start) / 1000000000.0f)
}
