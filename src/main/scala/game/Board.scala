package game

/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
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

  def print() = {
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
        if(exists) "|" else ""
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
    println(out)
  }
}
