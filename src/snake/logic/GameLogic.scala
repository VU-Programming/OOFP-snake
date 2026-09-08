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
  var applePos = Point(0,5)

  var snake : List[Point] = List(Point(0,0), Point(1,0), Point(2,0))
  var currentDir : Direction = East()
  
  
  def getCellType(p: Point): CellType = {
    if(applePos == p) Apple()
    else if(snake.contains(p)) SnakeBody(0)
    else Empty()
  }
  // TODO implement me
  def step(): Unit = {
    if(currentDir == East()) applePos = Point(applePos.x + 1, applePos.y )
    else applePos = Point(applePos.x - 1, applePos.y )
  }
  // TODO implement me
  def changeDir(d: Direction): Unit = {
    currentDir = d 
  }

  def gameOver: Boolean = false
  
  // TODO implement me
  def setReverse(r: Boolean): Unit = ()

}

/** GameLogic companion object */
object GameLogic {

  val FramesPerSecond: Int = 5 // change this to increase/decrease speed of game

  val DrawSizeFactor = 1.0 // increase this to make the game bigger (for high-res screens)
  // or decrease to make game smaller

  // These are the dimensions used when playing the game.
  // When testing the game, other dimensions are passed to
  // the constructor of GameLogic.
  //
  // DO NOT USE the variable DefaultGridDims in your code!
  //
  // Doing so will cause tests which have different dimensions to FAIL!
  //
  // In your code only use gridDims.width and gridDims.height
  // do NOT use DefaultGridDims.width and DefaultGridDims.height
  val DefaultGridDims
    : Dimensions =
    Dimensions(width = 25, height = 25)  // you can adjust these values to play on a different sized board



}


