package strategy

import game.{Board, GameState}

import scala.annotation.tailrec

/**
 * Author: Phillip Johnson
 * Date: 2/5/15
 */
class DoubleCross extends Strategy {
  override def play(state: GameState): (Int, Int) = {
    findDoubleCross(state) getOrElse new GreedyBoxMaker().play(state)
  }

  def findDoubleCross(state: GameState):Option[(Int, Int)] = {
    val current = countDoubleCrosses(state.board)
    val moves = for {
      move <- state.availableMoves
      newCrosses = countDoubleCrosses(state.makeMove(move).board)
      if newCrosses > current
    } yield move -> newCrosses
    if(moves.nonEmpty) Some(moves.maxBy(_._2)._1)
    else None
  }

  def countDoubleCrosses(board:Board):Int = {
    val size = board.size
    def toRight(start: Int) = (start, start + 1)
    def toDown(start:Int) = (start, start + size)
    
    @tailrec
    def horizontal(start:Int, acc:Int):Int = {
      if(start + size + 2 == board.area) return acc

      val required = List(toRight(start), toRight(start + 1), toDown(start), toRight(start + size), toRight(start + size + 1), toDown(start + 2))
      val notAllowed = toDown(start + 1)
      val dc = required.forall(board.plays contains) && board.availableMoves.contains(notAllowed)

      if(dc) horizontal(start + 1, acc + 1)
      else horizontal(start + 1, acc)
    }

    @tailrec
    def vertical(start:Int, acc:Int):Int = {
      if(start + size * 2 + 1 == board.area) return acc

      val required = List(toRight(start), toDown(start), toDown(start + size), toDown(start + 1), toDown(start + 1 + size), toRight(start + size * 2))
      val notAllowed = toRight(start + size)
      val dc = required.forall(board.plays contains) && board.availableMoves.contains(notAllowed)

      if(dc) vertical(start + 1, acc + 1)
      else vertical(start + 1, acc)
    }

    horizontal(0, 0) + vertical(0, 0)
  }

  override def toString = "DBL CROSS"
}
