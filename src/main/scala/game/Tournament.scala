package game

import strategy.{Strategy}

/**
 * Author: Phillip Johnson
 * Date: 1/20/15
 */
class Tournament(val rounds:Int, val strategy1:Strategy, val strategy2:Strategy) {

  var player1Win = 0
  var player1Loss = 0
  var player1Tie = 0

  var player2Win = 0
  var player2Loss = 0
  var player2Tie = 0

  def play() = {
    for(_ <- 1 to rounds) {
      val finalState = new Game(strategy1,strategy2).play
      if(finalState.player1.score == finalState.player2.score) {
        player1Tie += 1
        player2Tie += 1
      } else if (finalState.player1.score > finalState.player2.score) {
        player1Win += 1
        player2Loss += 1
      } else {
        player1Loss += 1
        player2Win += 1
      }
    }

    println(formatScores)
  }

  private def formatScores = {
    val out = "  " + strategy1 + "  |  " + strategy2 + "  \n" +
              "==========|===========\n" +
              "W:  " + player1Win + "     |  " + player2Win + "  \n" +
              "L:  " + player1Loss + "     |  " + player2Loss + "  \n" +
              "D:  " + player1Tie + "     |  " + player2Tie + "  \n"
    out
  }
}
