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

  def this() = this(new ScalaRandomGen(), DefaultGridDims)

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

  val DefaultColumns = 25
  val DefaultRows = 25
  val DefaultGridDims : Dimensions = Dimensions(DefaultColumns,DefaultRows)
}


