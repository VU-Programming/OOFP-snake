package snake

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import snake.reverse.Test7_Reverse

@RunWith(classOf[JUnitRunner])
class SnakeTestsAssignment2_3 extends SnakeTestSuite(
  new Test7_Reverse
)
