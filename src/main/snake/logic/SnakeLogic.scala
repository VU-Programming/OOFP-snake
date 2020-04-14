package snake.logic

import engine.random.{RandomGenerator, ScalaRandomGen}
import snake.game.{CellType, Dimensions, Direction, Empty}
import snake.logic.SnakeLogic._

/** To implement Snake, complete the ``TODOs`` below.
 *
 * If you need additional files,
 * please also put them in the ``snake`` package.
 */
class SnakeLogic(val randomGen: RandomGenerator,
                 val gridDimensions : Dimensions) {

  def this() = this(new ScalaRandomGen(), DefaultGridDimensions)

  def isGameOver: Boolean = false

  // TODO implement me
  def step(): Unit = ()

  // TODO implement me
  def setReverseTime(reverse: Boolean): Unit = ()

  // TODO implement me
  def changeDir(d: Direction): Unit = ()

  // TODO implement me
  def getGridTypeAt(x: Int, y: Int): CellType = Empty()

}

/** SnakeLogic companion object */
object SnakeLogic {

  val DefaultColumns = 25
  val DefaultRows = 25
  val DefaultGridDimensions = Dimensions(DefaultColumns,DefaultRows)
}


