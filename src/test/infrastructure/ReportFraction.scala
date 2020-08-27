// DO NOT MODIFY THIS FILE
package infrastructure

import java.io.{OutputStream, PrintStream}

import org.scalatest.{Args, ConfigMap, Reporter}
import org.scalatest.events.Event
import snake.{SnakeTestSuite, SnakeTestsAssignment1_1, SnakeTestsAssignment1_3}

// reports your score as a fraction between 0 and 1 for codegrade
abstract class ReportFraction {
    def Tests(): SnakeTestSuite

    def Run(): Unit  = {
        val err = System.err
        // prevent inventive students from printing 1.0 to
        // stderr and then getting full points
        System.setErr(new PrintStream(new OutputStream {
            override def write(i: Int): Unit = ()
        }))
        val scoreCounter = new ScoreCounter()
        Tests().runDirect(None, Args(reporter = new Reporter {
            override def apply(event: Event): Unit = () } ,
            configMap = ConfigMap("scoreCounter"->  scoreCounter))
        )
        err.printf("%.2f",scoreCounter.fraction())
    }
}

object ReportFraction1_1 extends ReportFraction {
    override def Tests(): SnakeTestSuite = new SnakeTestsAssignment1_1()

    def main(args: Array[String]) : Unit = {
        Run()
    }
}

object ReportFraction1_3 extends ReportFraction {
    override def Tests(): SnakeTestSuite = new SnakeTestsAssignment1_3()

    def main(args : Array[String]) : Unit  = {
        Run()
    }
}
