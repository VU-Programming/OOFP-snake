package snake.basic

import snake.SnakeTestSuiteBase

@org.scalatest.DoNotDiscover
class Test1_Placement extends SnakeTestSuiteBase {
    override def suiteName = "1 Placement"

    test("testStartCorrectly") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......""")
            ),
            hint = "The snake should begin in the top left, heading east, and be three\n" +
              "cells long."
        )
    }

    // See assignment description on where to place the
    // apple!
    test("testApplePos3") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......""")
            )
        , hint = "The apple should be placed correctly. See README.md"
        )
    }

    test("testApplePos8") {
        checkGame(
            List(
                TestFrame(8,
                    """OO>...
                      |.....A""")
            ),
             hint =  "The apple should be placed correctly. See README.md"
        )
    }

    test("testNoRoomForApple") {
        checkGame(
            List(
                TestFrame(2,
                    """OO>..A"""),
                TestFrame(2,
                    """.OO>.A"""),
                TestFrame(2,
                    """..OO>A"""),
                TestFrame(2,
                    """..AOO>"""),
                TestFrame(2,
                    """>.AOOO"""),
                TestFrame(2,
                    """O>AOOO"""),
                TestFrame(2,
                    """OO>OOO"""),
                TestFrame(2,
                    GameOverDisplay()
                )
            )

        , hint = "No apple should be placed if the entire playing field is filled with\n" +
          "the snake body."
        )
    }
}
