package snake.basic

import snake.SnakeTestSuiteBase

class GrowthTests extends SnakeTestSuiteBase {

    test("testGrow") {
        checkGame(
            List(
                TestFrame(1,
                    """OO>.A.....
                      |.........."""),
                TestFrame(1,
                    """.OO>A.....
                      |.........."""),
                TestFrame(10,
                    """..OO>.....
                      |...A......"""),
                TestFrame(10,
                    """..OOO>....
                      |...A......"""),
                TestFrame(10,
                    """..OOOO>...
                      |...A......"""),
                TestFrame(10,
                    """..OOOOO>..
                      |...A......"""),
                TestFrame(10,
                    """...OOOOO>.
                      |...A......"""),
                TestFrame(10,
                    """....OOOOO>
                      |...A......"""),
            )
        )
    }

    test("testGrowTwice") {
        checkGame(
            List(
                TestFrame(0,
                    """OO>A..........
                      |.............."""),
                TestFrame(2,
                    """.OO>.A........
                      |.............."""),
                TestFrame(2,
                    """.OOO>A........
                      |.............."""),
                TestFrame(10,
                    """.OOOO>........
                      |.A............"""),
                TestFrame(10,
                    """.OOOOO>.......
                      |.A............"""),
                TestFrame(10,
                    """.OOOOOO>......
                      |.A............"""),
                TestFrame(10,
                    """.OOOOOOO>.....
                      |.A............"""),
                TestFrame(10,
                    """.OOOOOOOO>....
                      |.A............"""),
                TestFrame(10,
                    """..OOOOOOOO>...
                      |.A............"""),
                TestFrame(10,
                    """...OOOOOOOO>..
                      |.A............"""),
                TestFrame(10,
                    """....OOOOOOOO>.
                      |.A............"""),
            )
        )
    }
}
