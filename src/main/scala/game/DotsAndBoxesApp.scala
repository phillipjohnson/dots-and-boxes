package game
import strategy.{Minimax, RandomPlay}
/**
 * Author: Phillip Johnson
 * Date: 1/14/15
 */
object DotsAndBoxesApp extends App {

  val player1 = new Player(new Minimax(), 0, true)
  val player2 = new Player(new RandomPlay(), 0, false)
  new Tournament(10, player1, player2).play()
}
