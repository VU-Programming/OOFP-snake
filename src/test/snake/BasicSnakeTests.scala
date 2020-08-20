package snake

import snake.logic.{North, West}

class BasicSnakeTests extends SnakeTestSuiteBase {

  test("testStartCorrectly",
    List(
      TestFrame(0,
        """OO>A..
          |......""")))

  test("testApplePos3",
    List(
      TestFrame(3,
        """OO>...
          |A.....""")))

  test("testApplePos8",
    List(
      TestFrame(8,
        """OO>...
          |.....A""")))

  test("testNoRoomForApple",
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
      )))


  test("testMove",
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
    ))

  test("testIgnoreEatHead",
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
    ))

  test("testQuickSwitch",
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
    ))

}
