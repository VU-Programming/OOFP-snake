package infrastructure

import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.exceptions.TestFailedException
import org.scalatest.time.{Days, Seconds, Span}
import org.scalatest.{Args, Status, Tag}
import org.scalatest.funsuite.AnyFunSuite

abstract class TestBase extends AnyFunSuite with TimeLimitedTests {

  // check if the program was launced from the debugger, so that we can disable the timeout in that case
  val isDebug : Boolean = java.lang.management.ManagementFactory.getRuntimeMXBean.getInputArguments.toString.indexOf("jdwp") >= 0
  override def timeLimit: Span = if (isDebug) Span(365,Days) else Span(1,Seconds)

  // this is need to actually stop when the buggy code contains an infinite loop...
  override val defaultTestSignaler: Signaler = ReallyStopSignaler

  var scoreCounter : Option[ScoreCounter] = None

  override def run(testName: Option[String], args: Args): Status = {
    if(args.configMap.contains("scoreCounter")) {
      args.configMap("scoreCounter") match {
        case sc: ScoreCounter => this.scoreCounter = Some(sc)
      }
    }
    super.run(testName, args)
  }

  def weightedTest(testName : String, weight : Int = 1, testTags : Tag*)(testFun : => Any): Unit = {
    super.test(testName,testTags*){
      try {
        testFun
        scoreCounter.foreach(_.addScore(weight,weight))
      } catch {
        case e : TestFailedException => {
          scoreCounter.foreach(_.addScore(weight,0))
          throw e
        }
      }
    }
  }
}

object ReallyStopSignaler extends Signaler {
  override def apply(testThread: Thread): Unit = {
    StopRunningNow.stopRunningNowUnsafe(testThread)
  }
}

