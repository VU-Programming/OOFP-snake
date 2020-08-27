package snake.basic

import snake.logic.{East, North, South, West}
import snake.{ChangeDir, SnakeTestSuiteBase}

class MovementTests extends SnakeTestSuiteBase {

    test("testMove") {
        checkGame(
            List(
                TestFrame(3,
                    """OO>...
                      |A....."""),
                TestFrame(3,
                    """.OO>..
                      |A....."""),
                TestFrame(3,
                    """..OO>.
                      |A....."""),
            )
        )
    }

    test("testIgnoreEatHead") {
        checkGame(
            List(
                TestFrame(5,
                    """OO>...
                      |..A..."""),
                TestFrame(5, List(ChangeDir(West())),
                    """.OO>..
                      |..A..."""),
                TestFrame(5, List(ChangeDir(West())),
                    """..OO>.
                      |..A...""")
            )
        )
    }

    test("testQuickSwitch") {
        checkGame(
            List(
                TestFrame(4,
                    """OO>.
                      |...A
                      |...."""),
                TestFrame(4,
                    """.OO>
                      |...A
                      |...."""),
                TestFrame(4, List(ChangeDir(North()), ChangeDir(West())),
                    """..OO
                      |...A
                      |...^"""),
            )
        )
    }

    test("testQuickSwitch2") {
        checkGame(
            List(
                TestFrame(4,
                    """OO>.
                      |...A
                      |...."""),
                TestFrame(4,
                    """.OO>
                      |...A
                      |...."""),
                TestFrame(4, List(ChangeDir(West()), ChangeDir(North())),
                    """..OO
                      |...A
                      |...^"""),
            )
        )
    }

    test("testChangeDirs") {
        checkGame(
            List(
                TestFrame(2,
                    """OO>.
                      |.A..
                      |....
                      |...."""),
                TestFrame(2, List(ChangeDir(South())),
                    """.OO.
                      |.Av.
                      |....
                      |...."""),
                TestFrame(2,
                    """..O.
                      |.AO.
                      |..v.
                      |...."""),
                TestFrame(2, List(ChangeDir(West())),
                    """....
                      |.AO.
                      |.<O.
                      |...."""),
                TestFrame(2,
                    """....
                      |.A..
                      |<OO.
                      |...."""),
                TestFrame(2, List(ChangeDir(North())),
                    """....
                      |^A..
                      |OO..
                      |...."""),
                TestFrame(2,
                    """^...
                      |OA..
                      |O...
                      |...."""),
                TestFrame(2, List(ChangeDir(East())),
                    """O>..
                      |OA..
                      |....
                      |...."""),
                TestFrame(2,
                    """OO>.
                      |.A..
                      |....
                      |....""")
            )
        )
    }
}
