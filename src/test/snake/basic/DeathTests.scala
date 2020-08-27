package snake.basic

import snake.logic.{North, South, West}
import snake.{ChangeDir, SnakeTestSuiteBase}

class DeathTests extends SnakeTestSuiteBase {

    test("testPreciselyDoesNotDie") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......"""),
                TestFrame(3,
                    """.OO>..
                      |A....."""),
                TestFrame(3,
                    """.OOO>.
                      |A....."""),
                TestFrame(3,
                    """.OOOO>
                      |A....."""),
                TestFrame(3,
                    """>OOOOO
                      |A....."""),
                TestFrame(3,
                    """O>OOOO
                      |A....."""),
                TestFrame(3,
                    """OO>OOO
                      |A....."""),
                TestFrame(3,
                    """OOO>OO
                      |A....."""),
                TestFrame(3,
                    """OOOO>O
                      |A....."""),
                TestFrame(3,
                    """OOOOO>
                      |A....."""),
            )
        )
    }

    test("testGameOver") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..
                      |......
                      |......"""),
                TestFrame(6,
                    """.OO>..
                      |...A..
                      |......"""),
                TestFrame(6,
                    """.OOO>.
                      |...A..
                      |......"""),
                TestFrame(6,
                    """.OOOO>
                      |...A..
                      |......"""),
                TestFrame(6, List(ChangeDir(South())),
                    """.OOOOO
                      |...A.v
                      |......"""),
                TestFrame(6, List(ChangeDir(West())),
                    """..OOOO
                      |...A<O
                      |......"""),
                TestFrame(6, List(ChangeDir(North())),
                    GameOverDisplay())
            )
        )
    }

    test("testNoEscapeGameOver") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A.
                      |.....
                      |.....
                      |....."""),
                TestFrame(0,
                    """AOO>.
                      |.....
                      |.....
                      |....."""),
                TestFrame(0, List(ChangeDir(South())),
                    """AOOO.
                      |...v.
                      |.....
                      |....."""),
                TestFrame(0, List(ChangeDir(West())),
                    """AOOO.
                      |..<O.
                      |.....
                      |....."""),
                TestFrame(0, List(ChangeDir(North())),
                    GameOverDisplay()),
                TestFrame(0, GameOverDisplay())
            )
        )
    }
}
