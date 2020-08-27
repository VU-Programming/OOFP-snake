package snake

import infrastructure.ScoreCounter
import org.junit.runner.RunWith
import org.scalatest.{Args, Status, Suites}
import org.scalatestplus.junit.JUnitRunner
import snake.SnakeTestsAssignment1_1.MaxGrade
import snake.basic.{DeathTests, GrowthTests, MovementTests, NoReverseGameTests, NoReverseTest, PlacementTests, WrapAroundTests}
import snake.reverse.ReverseTests

@RunWith(classOf[JUnitRunner])
class SnakeTestsAssignment1_1 extends Suites(
    new PlacementTests,
    new MovementTests,
    new GrowthTests,
    new WrapAroundTests,
    new DeathTests,
    new NoReverseTest,
    new NoReverseGameTests
) {

    override def run(testName: Option[String], args: Args): Status = {
        val scoreCounter = new ScoreCounter()
        val newArgs =
            args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
        val res = runDirect(testName,newArgs)
        printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        printf("Your grade for the repls exercise will be : %.2f\n",scoreCounter.fraction() * MaxGrade)
        res
    }

    // run without making a new scorecounter
    def runDirect(testName: Option[String], args: Args): Status = {
        super.run(testName, args)
    }
}

object SnakeTestsAssignment1_1 {
    val MaxGrade = 10
}


@RunWith(classOf[JUnitRunner])
class SnakeTestsAssignment1_3 extends Suites(
    new PlacementTests,
    new MovementTests,
    new GrowthTests,
    new WrapAroundTests,
    new DeathTests,
    new NoReverseGameTests,
    new ReverseTests
) {

    override def run(testName: Option[String], args: Args): Status = {
        val scoreCounter = new ScoreCounter()
        val newArgs =
            args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
        val res = runDirect(testName,newArgs)
        printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        printf("Your grade for the repls exercise will be : %.2f\n",scoreCounter.fraction() * MaxGrade)
        res
    }

    // run without making a new scorecounter
    def runDirect(testName: Option[String], args: Args): Status = {
        super.run(testName, args)
    }
}

object SnakeTestsAssignment1_3 {
    val MaxGrade = 10
}
