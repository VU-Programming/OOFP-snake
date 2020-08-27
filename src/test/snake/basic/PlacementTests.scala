package snake.basic

import snake.SnakeTestSuiteBase

class PlacementTests extends SnakeTestSuiteBase {
    test("testStartCorrectly") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......""")
            )
        )
    }

    test("testApplePos3") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......""")
            )
        )
    }

    test("testApplePos8") {
        checkGame(
            List(
                TestFrame(8,
                    """OO>...
                      |.....A""")
            )
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
        )
    }
}
