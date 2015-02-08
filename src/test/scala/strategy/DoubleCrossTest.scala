package strategy

import game.Board
import org.scalatest.FunSuite

/**
 * Author: Phillip Johnson
 * Date: 2/6/15
 */
class DoubleCrossTest extends FunSuite {
  test("Empty board has zero double crosses.") {
    val view = "" +
    "+   +   +   +\n" +
    "             \n" +
    "+   +   +   +\n" +
    "             \n" +
    "+   +   +   +\n" +
    "             \n" +
    "+   +   +   +\n"

    val b = Board(view)

    assert(new DoubleCross().countDoubleCrosses(b) == 0)
  }

  test("Horizontal crosses.") {
    val view = "" +
      "+---+---+   +\n" +
      "|       |     \n" +
      "+---+---+---+\n" +
      "    |       |\n" +
      "+   +---+---+\n" +
      "             \n" +
      "+   +   +   +\n"

    val b = Board(view)

    assert(new DoubleCross().countDoubleCrosses(b) == 2)
  }

  test("Vertical double crosses.") {
    val view = "" +
      "+---+   +   +\n" +
      "|   |         \n" +
      "+   +---+   +\n" +
      "|   |   |     \n" +
      "+---+   +   +\n" +
      "    |   |    \n" +
      "+   +---+   +\n"

    val b = Board(view)

    assert(new DoubleCross().countDoubleCrosses(b) == 2)
  }

  test("Mixed double crosses.") {
    val view = "" +
      "+   +---+---+\n" +
      "    |       |\n" +
      "+---+---+---+\n" +
      "|   |       |\n" +
      "+   +---+---+\n" +
      "|   |        \n" +
      "+---+   +   +\n"

    val b = Board(view)

    assert(new DoubleCross().countDoubleCrosses(b) == 3)
  }

  test("Extra room is not a double cross.") {
    val view = "" +
      "+   +   +   +\n" +
      "             \n" +
      "+---+   +   +\n" +
      "|   |        \n" +
      "+   +---+   +\n" +
      "|       |    \n" +
      "+---+---+   +\n"

    val b = Board(view)

    assert(new DoubleCross().countDoubleCrosses(b) == 0)
  }

}
