package snake.basic

import snake.{ReverseGame, SnakeTestSuiteBase}

class NoReverseTest extends SnakeTestSuiteBase {

    test("TestNoReverseMode") {
        checkGame(
            List(
                TestFrame(2,
                    """OO>.
                      |.A.."""),
                TestFrame(2,
                    """.OO>
                      |.A.."""),
                TestFrame(2,
                    """>.OO
                      |.A.."""),
                TestFrame(2,
                    """O>.O
                      |.A.."""),
                TestFrame(2, List(ReverseGame(true)),
                    """OO>.
                      |.A.."""),
                TestFrame(2,
                    """.OO>
                      |.A.."""),
                TestFrame(2,
                    """>.OO
                      |.A.."""),
                TestFrame(2, List(ReverseGame(false)),
                    """O>.O
                      |.A.."""),
                TestFrame(2,
                    """OO>.
                      |.A.."""),
                TestFrame(2,
                    """.OO>
                      |.A.."""),
                TestFrame(2, List(ReverseGame(true)),
                    """>.OO
                      |.A.."""),
                TestFrame(2,
                    """O>.O
                      |.A.."""),
                TestFrame(2,
                    """OO>.
                      |.A.."""),
                TestFrame(2, List(ReverseGame(false)),
                    """.OO>
                      |.A.."""),
            )
        )
    }
}
