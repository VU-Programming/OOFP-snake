package snake.logic

import engine.random.{RandomGenerator, ScalaRandomGen}
import snake.logic.SnakeLogic._

/** To implement Snake, complete the ``TODOs`` below.
 *
 * If you need additional files,
 * please also put them in the ``snake`` package.
 */
class SnakeLogic(val randomGen: RandomGenerator,
                 val gridDimensions : Dimensions) {

  def this() = this(new ScalaRandomGen(), DefaultGridDimensions)

  def gameOver: Boolean = false

  // TODO implement me
  def step(): Unit = ()

  // TODO implement me
  def setReverse(reverse: Boolean): Unit = ()

  // TODO implement me
  def changeDir(d: Direction): Unit = ()

  // TODO implement me
  def getCellType(p : Point): CellType = Empty()

}

/** SnakeLogic companion object */
object SnakeLogic {

  val DefaultColumns = 25
  val DefaultRows = 25
  val DefaultGridDimensions = Dimensions(DefaultColumns,DefaultRows)
}


