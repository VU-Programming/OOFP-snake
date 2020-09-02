package tetris

import infrastructure.ScoreCounter
import org.scalatest.{Args, Status, Suites}

class TetrisTestSuitesBase(suites : TetrisTestSuiteBase*) extends Suites(suites){



  override def runWithScoreCounter(testName: Option[String], args: Args): (ScoreCounter,Status) = {
    val scoreCounter = new ScoreCounter()
    val newArgs =
      args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
    val res = runDirect(testName,newArgs)
    printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
    printf("Your base grade for the tetris exercise will be : %.2f\n",scoreCounter.fraction() * MaxPoints)
    (scoreCounter,res)
  }

  // run without making a new scorecounter
  def runDirect(testName: Option[String], args: Args): Status = {
    super.run(testName, args)
  }

}
