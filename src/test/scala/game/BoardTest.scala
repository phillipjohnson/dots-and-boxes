package game

import org.scalatest.FunSuite
/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class BoardTest extends FunSuite {
  test("2x2 board has one possible square") {
    var board = new Board(2, List.empty)
    assert(board.completedSquares == 0)
    board = board.play((0,1))
    board = board.play((2,3))
    board = board.play((0,2))
    board = board.play((1,3))
    assert(board.completedSquares == 1)
  }

  test("3x3 board has 4 possible squares") {
    var board = new Board(3, List.empty)
    assert(board.completedSquares == 0)
    board = board.play((0,1))
    board = board.play((3,4))
    board = board.play((0,3))
    board = board.play((1,4))
    assert(board.completedSquares == 1)
    board = board.play((7,8))
    board = board.play((4,7))
    board = board.play((5,8))
    board = board.play((4,5))
    assert(board.completedSquares == 2)
    board = board.play((1,2))
    board = board.play((2,5))
    assert(board.completedSquares == 3)
    board = board.play((3,6))
    board = board.play((6,7))
    assert(board.completedSquares == 4)
  }

  test("Board from string calculates 2x2") {
    val view = "+---+\n" +
               "|   |\n" +
               "+---+\n"
    val b = Board(view)
    assert(b.size == 2)
    assert(view == b.toString)
  }

  test("Board from string calculates empty 2x2") {
    val view = "+   +\n" +
               "     \n" +
               "+   +\n"
    val b = Board(view)
    assert(b.size == 2)
    assert(view == b.toString)
  }

  test("3x3 complete board") {
    val view = "+---+---+---+\n" +
               "|   |   |   |\n" +
               "+---+---+---+\n" +
               "|   |   |   |\n" +
               "+---+---+---+\n" +
               "|   |   |   |\n" +
               "+---+---+---+\n"

    val b = Board(view)
    assert(view == b.toString)
  }

  test("3x3 board with random plays") {
    val view =
      "+   +---+---+\n" +
      "|       |   |\n" +
      "+---+---+---+\n" +
      "|   |   |   |\n" +
      "+---+   +---+\n" +
      "    |        \n" +
      "+---+   +   +\n"

    val b = Board(view)
    assert(view == b.toString)
  }
}
