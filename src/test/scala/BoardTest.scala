import org.scalatest.FunSuite
/**
 * Author: Phillip Johnson
 * Date: 1/18/15
 */
class BoardTest extends FunSuite {
  test("2x2 board has one possible square") {
    val board = new Board(2)
    assert(board.completedSquares == 0)
    board.play((0,1))
    board.play((2,3))
    board.play((0,2))
    board.play((1,3))
    assert(board.completedSquares == 1)
  }

  test("3x3 board has 4 possible squares") {
    val board = new Board(3)
    assert(board.completedSquares == 0)
    board.play((0,1))
    board.play((3,4))
    board.play((0,3))
    board.play((1,4))
    assert(board.completedSquares == 1)
    board.play((4,5))
    board.play((7,8))
    board.play((4,7))
    board.play((5,8))
    assert(board.completedSquares == 2)
    board.play((1,2))
    board.play((2,5))
    assert(board.completedSquares == 3)
    board.play((3,6))
    board.play((6,7))
    assert(board.completedSquares == 4)
  }
}
