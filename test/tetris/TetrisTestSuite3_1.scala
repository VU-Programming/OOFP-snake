package tetris

import infrastructure.ScoreCounter
import org.scalatest.{Args, Status, Suites}

class TetrisTestSuite3_1 extends TetrisTestSuitesBase(new PlacementTests,
  new RotationTests,
  new RotateBackToStartTests,
  new MovementTests)
{
  val MinPointsToPass = 17

  override def run(testName: Option[String], args: Args): Status = {
    val (scoreCounter,res) = runWithScoreCounter(testName, args)
    printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
    if(scoreCounter.points >= MinPointsToPass) printf("You pass assignment 3.1!")
    else printf("You do not pass assignment 3.1 yet")
    res
  }
}