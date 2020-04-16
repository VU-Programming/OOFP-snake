package snake.logic

import engine.random.{RandomGenerator, ScalaRandomGen}
import snake.logic.GameLogic._

/** To implement Snake, complete the ``TODOs`` below.
 *
 * If you need additional files,
 * please also put them in the ``snake`` package.
 */
class GameLogic(val random: RandomGenerator,
                val gridDims : Dimensions) {
  def gameOver: Boolean = false

  // TODO implement me
  def step(): Unit = ()

  // TODO implement me
  def setReverse(r: Boolean): Unit = ()

  // TODO implement me
  def changeDir(d: Direction): Unit = ()

  // TODO implement me
  def getCellType(p : Point): CellType = Empty()

}

/** GameLogic companion object */
object GameLogic {

  // These are the dimensions used when playing the game
  // when testing the game, other dimensions are passed to
  // the constructor of GameLogic when testing.
  // Note that therefore it is important that you do not use
  // the variable DefaultGridDims in your code!
  // Doing so will cause tests which have different dimensions to FAIL!
  //
  // In your code only use gridDims.width and gridDims.height
  // do NOT use DefaultGridDims.width and DefaultGridDims.height
  val DefaultGridDims
    : Dimensions =
    Dimensions(width = 25, height = 25)  // you can adjust these values to play on a different sized board



}


