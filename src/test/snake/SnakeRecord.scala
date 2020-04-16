// DO NOT MODIFY FOR BASIC SUBMISSION
// scalastyle:off

package snake

import engine.random.RandomGenerator
import generic.{CellTypeInterface, GameLogicInterface, GenericRecord}
import snake.game._
import snake.logic.{Apple, CellType, Direction, East, Empty, North, SnakeBody, SnakeHead, SnakeLogic, South, West}


sealed abstract class SnakeAction
case class ChangeDir(dir: Direction) extends SnakeAction
case class ReverseGame(enable: Boolean) extends SnakeAction
case object Step extends SnakeAction


case class SnakeGridTypeWrapper(gridType: CellType)  extends CellTypeInterface[SnakeGridTypeWrapper] {
  override def conforms(rhs: SnakeGridTypeWrapper): Boolean = (gridType, rhs.gridType) match {
    case (SnakeBody(_), SnakeBody(_)) => true
      // this is such that the student cannot succeed at all test by overriding equality!
    case (Empty(), Empty()) => true
    case (Apple(), Apple()) => true
    case (SnakeHead(dl),SnakeHead(dr)) =>
      (dl, dr) match  {
        case (East(), East()) => true
        case (West(), West()) => true
        case (South(), South()) => true
        case (North(), North()) => true
        case _ => false
      }
    case _ => false
  }

  override def toChar: Char = gridType match {
    case Empty() => '.'
    case SnakeHead(West()) => '<'
    case SnakeHead(East()) => '>'
    case SnakeHead(North()) => '^'
    case SnakeHead(South()) => 'v'
    case SnakeBody(_) => 'O'
    case Apple() => 'A'
  }
}


case class SnakeLogicWrapper(logic: SnakeLogic)
  extends GameLogicInterface[SnakeAction, SnakeGridTypeWrapper] {

  override def performAction(action: SnakeAction): Unit = action match {
    case ChangeDir(d) => logic.changeDir(d)
    case Step => logic.step()
    case ReverseGame(enable) => logic.setReverseTime(enable)
  }

  def getGridTypeAt(col: Int , row: Int): SnakeGridTypeWrapper =
    SnakeGridTypeWrapper(logic.getCellTypeAt(col,row))

  override def nrRows: Int = logic.nrRows
  override def nrColumns: Int = logic.nrColumns
  override def isGameOver: Boolean = logic.isGameOver

}

object SnakeRecord extends GenericRecord
  [SnakeAction, SnakeGridTypeWrapper, SnakeLogicWrapper, (Int, Int)]() {


  override def makeGame(r: RandomGenerator, info: (Int, Int)): SnakeLogicWrapper =
    SnakeLogicWrapper(new SnakeLogic(r,info._1, info._2))

  override def gameLogicName: String = "SnakeLogic"

  def charToGridType(char: Char): SnakeGridTypeWrapper =
    SnakeGridTypeWrapper(char match {
      case '.' => Empty()
      case '<' => SnakeHead(West())
      case '>' => SnakeHead(East())
      case '^' => SnakeHead(North())
      case 'v' => SnakeHead(South())
      case 'O' => SnakeBody(0.5f)
      case 'A' => Apple()
      case _ => Empty()
    })


  object SnakeTest {
    def apply(name : String, frames : Seq[TestFrame]): SnakeRecord.Test = {
      val dimensions: (Int, Int) = frames.head.display match {
        case grid: GridDisplay => (grid.nrColumns, grid.nrRows)
        case _ => throw new Error("No grid display in test")
      }

      def addStep(input : FrameInput) : FrameInput = FrameInput(input.randomNumber, input.actions  :+ Step)
      val framesWithStep : Seq[TestFrame] =
        frames.head +: frames.tail.map(frame => SnakeRecord.TestFrame(addStep(frame.input), frame.display))
      Test(name, dimensions,framesWithStep)
    }
  }


}
