package game

import scala.collection.mutable.ListBuffer

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
object Board {
  def apply(size: Int, plays:List[(Int,Int)]):Board = {
    new Board(size, plays)
  }

  def apply(view:String):Board = {
    new Board(determineSizeFromView(view), determinePlaysFromView(view))
  }

  def determineSizeFromView(view:String): Int = {
    view.split("\n").head.count(_ == '+')
  }


  def determinePlaysFromView(view:String) = {
    val size = determineSizeFromView(view)
    val lines = view.split("\n")

    def horizontal(stringRow:Int, boardRow:Int) = {
      val gaps = lines(stringRow).split("\\+")
      for {
        (gap, i) <- gaps.zipWithIndex
        if "---".equals(gap)
      } yield (i - 1 + (size * boardRow), i + (size * boardRow))
    }


    def vertical(stringRow:Int, boardRow:Int) = {
      for {
        (char, i) <- lines(stringRow).toCharArray.zipWithIndex
        if char == '|'
      } yield ((size * boardRow) + (i/4), (size * boardRow) + (i/4) + size)
    }

    val plays = new ListBuffer[(Int, Int)]()

    for (iter <- 0 until size * 2 - 1) {
      if(iter % 2 == 0) {
        plays ++= horizontal(iter, iter / 2)
      } else {
        plays ++= vertical(iter, (iter - 1) / 2)
      }
    }

    plays.result()
  }
}

class Board(val size: Int, val plays:List[(Int,Int)]) {
  require(size > 1, "Size of board must be greater than 1.")

  val area = size * size

  val possibleMoves = List.concat(
    for {
      i <- (0 to area).toList
      if i + 1 < area && i % size != size - 1
    } yield (i, i + 1)
    ,
    for {
      i <- (0 to area).toList
      if i + size < area
    } yield (i, i + size)
  )

  def availableMoves = {
    possibleMoves filterNot(plays contains)
  }

  val possibleSquares = {
    for {
      i <- (0 to area).toList
      if i + 1 < area && i % size != size - 1 && i + size < area
    } yield ((i, i + 1), (i + size, i + size + 1), (i, i + size), (i + 1, i + 1 + size))
  }

  def completedSquares:Int = plays match {
    case Nil => 0
    case x::xs => {
      possibleSquares.count(square =>
        List(square._1, square._2, square._3, square._4).forall(plays contains))
    }
  }

  def play(line:(Int, Int)):Board = {
    require(!plays.contains(line), "Illegal play: " + line + " already on board")
    new Board(size, plays.::(line))
  }

  override def toString = {
    var out = ""

    def horizontal(start:Int, end:Int):String = {
      if (plays.contains((start, end))) "+---"
      else if(end % size == 0) "+"
      else "+   "
    }

    def vertical(start:Int, end: Int):String = {
      val rowEnd = end % size == size - 1
      val exists = plays.contains((start, end))
      if(rowEnd) {
        if(exists) "|" else " "
      } else {
        if(exists) "|   " else "    "
      }
    }

    var row = 0
    for (iter <- 0 until size * 2 - 1) {
      if(iter % 2 == 0) {
        for (start <- row * size until (row + 1) * size) out += horizontal(start, start + 1)
      } else {
        for (start <- row * size until (row + 1) * size) out += vertical(start, start + size)
        row += 1
      }
      out += "\n"
    }
    out
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Board]

  override def equals(other: Any): Boolean = other match {
    case that: Board =>
      (that canEqual this) &&
        size == that.size &&
        plays == that.plays
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(size, plays)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
