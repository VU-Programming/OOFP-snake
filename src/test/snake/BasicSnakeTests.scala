package snake

import snake.logic.{East, North, South, West}

class BasicSnakeTests extends SnakeTestSuiteBase {

  test("testStartCorrectly") {
    checkGame(
      List(
        TestFrame(0,
          """OO>A..
            |......""")))
  }

  test("testApplePos3") {
    checkGame(
      List(
        TestFrame(0,
          """OO>A..
            |......""")))
  }

  test("testApplePos8") {
    checkGame(
      List(
        TestFrame(8,
          """OO>...
            |.....A""")))
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
        )))
  }


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
    ))
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
      ))
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
      ))
  }

  test("testQuickSwitch2") {
    checkGame(List(
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
    ))
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
            |....""")))
  }

  test("testWrapAroundEast") {
    checkGame(
      List(
        TestFrame(6,
          """OO>...
            |...A.."""),
        TestFrame(6,
          """.OO>..
            |...A.."""),
        TestFrame(6,
          """..OO>.
            |...A.."""),
        TestFrame(6,
          """...OO>
            |...A.."""),
        TestFrame(6,
          """>...OO
            |...A.."""),
        TestFrame(6,
          """O>...O
            |...A.."""),
        TestFrame(6,
          """OO>...
            |...A.."""),
        TestFrame(6,
          """.OO>..
            |...A.."""),
      ))
  }

}
