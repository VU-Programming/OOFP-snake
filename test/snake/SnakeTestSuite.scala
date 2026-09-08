package snake

import infrastructure.ScoreCounter
import org.scalatest.events.{Event, SuiteAborted, TestCanceled, TestFailed, TestIgnored, TestPending, TestStarting, TestSucceeded}
import org.scalatest.{Args, Reporter, Status, Suites}
import snake.BaseSnakeTestSuite.{BaseTests, MaxGrade}
import snake.basic.{Test1_Placement, Test2_Movement, Test3_Growth, Test4_WrapAround, Test5_Death, Test6_FullGames}

import scala.collection.immutable.ListSet

abstract class SnakeTestSuite(suites: SnakeTestSuiteBase*) extends Suites() {
    private case class FlatTest(suite: SnakeTestSuiteBase, originalName: String, reportedName: String)

    private val flatTests: Seq[FlatTest] =
        (BaseTests ++ suites).flatMap { suite =>
            suite.testNames.toSeq.zipWithIndex.map { case (testName, index) =>
                val suiteName = suite.getClass.getSimpleName
                FlatTest(suite, testName, s"$suiteName${index + 1}_$testName")
            }
        }

    private val flatTestsByName: Map[String, FlatTest] =
        flatTests.map(test => test.reportedName -> test).toMap

    override def testNames: Set[String] =
        ListSet.from(flatTests.map(_.reportedName))

    override protected def runTest(testName: String, args: Args): Status = {
        val test = flatTestsByName.getOrElse(
          testName,
          throw new IllegalArgumentException(s"Unknown test: $testName")
        )
        val reporter = new FlatTestReporter(args.reporter, test)
        test.suite.run(Some(test.originalName), args.copy(reporter = reporter))
    }

    override def run(testName: Option[String], args: Args): Status = {
        val scoreCounter = new ScoreCounter()
        val newArgs =
            args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
        val res = runDirect(testName,newArgs)
        printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        printf("Your grade for the snake exercise will be : %.2f\n",scoreCounter.fraction() * MaxGrade)
        res
    }

    // run without making a new scorecounter
    def runDirect(testName: Option[String], args: Args): Status = {
        super.run(testName, args)
    }

    private class FlatTestReporter(delegate: Reporter, test: FlatTest) extends Reporter {
        private val outerSuiteName = SnakeTestSuite.this.suiteName
        private val outerSuiteId = SnakeTestSuite.this.suiteId
        private val outerSuiteClassName = Some(SnakeTestSuite.this.getClass.getName)
        private val reportedName = test.reportedName

        private def trimFrameworkFrames(throwable: Throwable): Throwable = {
            val stackTrace = throwable.getStackTrace
            val firstTestFrame = stackTrace.indexWhere(_.getClassName == test.suite.getClass.getName)
            if (firstTestFrame >= 0) throwable.setStackTrace(stackTrace.drop(firstTestFrame))
            throwable
        }

        override def apply(event: Event): Unit = delegate(event match {
            case e: TestStarting =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName)
            case e: TestSucceeded =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName)
            case e: TestFailed =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName,
                    throwable = e.throwable.map(trimFrameworkFrames))
            case e: TestIgnored =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName)
            case e: TestCanceled =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName,
                    throwable = e.throwable.map(trimFrameworkFrames))
            case e: TestPending =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName, testName = reportedName, testText = reportedName)
            case e: SuiteAborted =>
                e.copy(suiteName = outerSuiteName, suiteId = outerSuiteId,
                    suiteClassName = outerSuiteClassName)
            case other => other
        })
    }
}

object BaseSnakeTestSuite {
    val MaxGrade = 5.5
    // Reporters alphabetize test IDs, so the numeric class prefixes mirror this sequence.
    val BaseTests: Seq[SnakeTestSuiteBase] = Seq[SnakeTestSuiteBase](
      new Test1_Placement,
      new Test2_Movement,
      new Test3_Growth,
      new Test4_WrapAround,
      new Test5_Death,
      new Test6_FullGames
    )
}
