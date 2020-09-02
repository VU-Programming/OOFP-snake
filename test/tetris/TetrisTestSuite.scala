package tetris

import infrastructure.ScoreCounter
import org.scalatest.{Args, Status, Suites}

class TetrisTestSuite extends Suites(
  new PlacementTests,
  new RotationTests,
  new RotateBackToStartTests,
  new MovementTests,
  new DropTests,
  new BlockedTests,
  new SpawnTests,
  new ClearLinesTests,
  new GameOverTests,
  new FullGameTests )
  {

    val MaxPoints = 5.5

    override def run(testName: Option[String], args: Args): Status = {
      val scoreCounter = new ScoreCounter()
      val newArgs =
        args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
      val res = runDirect(testName,newArgs)
      printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
      printf("Your base grade for the tetris exercise will be : %.2f\n",scoreCounter.fraction() * MaxPoints)
      res
    }

    // run without making a new scorecounter
    def runDirect(testName: Option[String], args: Args): Status = {
      super.run(testName, args)
    }
  }
