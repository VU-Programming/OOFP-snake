## Game description

The goal of Snake is to create a snake as long as possible. This is achieved by guiding the
snake to an apple on the game board. The snake cannot stop moving, and dies whenever
it hits something (excluding apples). Because the snake is growing longer and longer as
the game progresses, it gets increasingly difficult to avoid collisions with the snake itself.
The player can change the direction of the head of the snake by using the arrow keys. At
step in the game, there is always an apple somewhere on the board. If the snake eats an
apple, the snake becomes one cell longer per step, for 3 steps. A new apple is placed on
a random location, excluding all places covered by the snake. When the snake reaches
an end of the game board, it re-emerges at the opposite end.

It is probably easiest to understand the game by playing it yourself. You can find a lot
of snake implementations on the web, for example [here](https://www.coolmathgames.
com/0-snake). There are four differences between this implementation and the one you
should make. Your implementation should:
* make the snake should grow by 3 blocks instead of 4.
* make the snake re-emerge at the other end of the screen when reaching the end of
  the screen instead of dying.
* make the snake start at a length of 3, at the top left (instead of a length of 1 at
  the center).
* show the direction of the snake head.

## Test setup

To make sure that everyone programs exactly the same thing, we have implemented
a number of tests that your Snake implementation should pass. This section describes
these tests and the specific requirement on your snake implementation.

Each test describes an expected run of a snake game, typically on a small board (for
example, 6x3). A run consists of multiple *frames* (between each frame step is called).
For each frame, the test lists:
* The next “random” number (this is important for reproducible apple placement).
* A list of actions that the player performed before this frame, namely changing directions (and later, using the reverse button).
* The placement of the snake and the food.
Each test also contains a "Hint": a brief description of what is being tested

As an example, consider the following frame as it would be displayed to you from IntelliJ:
```
step=4, rand=2, actions=<ChangeDir(West()), ChangeDir(North()), Step>
Want
....
^A..
OO..
....
```
This indicates:
* The total number steps is 4
* The next random number is 2
* The player pressed west, and then north, before the screen was rendered.
* The game field is 4x4 cells big. 
* The snake is heading north, with the head at row
 (counting from 0) and column 0, and the body at row 2, col 0, and row 2, col 1.
The apple is currently at row 1, col 1.

The following actions can be encountered:
```
+-------------------------------|--------------------------------+
| Action                        | Meaning                        |
+-------------------------------+--------------------------------|
| ChangeDir(dir : Direction)    | Change snake direction         |
| ReverseGame(enable : Boolean) | Disable or enable reverse mode |
+----------------------------------------------------------------+
```
Direction is one of North(), East(), South() or West() . Reverse mode must NOT be
implemented for assignment 2.1 (it must be implemented for assignment 2.3).

The ASCII display of the game board should read as follows:
```
+-----------+----------------------------+
| Character | Meaning                    |
+-----------+----------------------------|
| "O"       | Snake body                 |
| "^"       | Snake Head heading North   |
| "v"       | Snake Head heading South   |
| ">"       | Snake Head heading East    |
| "<"       | Snake Head heading West    |
| "."       | Empty grid cell            |
| "A"       | Food                       |
+----------------------------------------+
```

If a test fails, you will see the frames as we expected them and the ones you implemen-
tation produced. For example, if you would fail testChangeDirs , then you might see
this:
```
org.scalatest.exceptions.TestFailedException: didPass was false
Hint: You can change direction.
--------------------------------------------------------------------------------

step=0, rand=2, actions=<>
Want   | Got ✓ 
---------------
OO>.   | OO>.
.A..   | .A..
....   | ....
....   | ....

step=4, rand=2, actions=<ChangeDir(South(), Step)>
Want   | Got ✗ 
---------------------------
.OO.   | .OO>
.Av.   | .A..
....   | ....
....   | ....
```
On each frame the expected output (Want) and the output produced by your implementation 
are displayed side-by-side. A checkmark (✓) indicates that on this frame the expected ouput and the
output produced by your implementation are the same, a cross (✗) indicates that they differ.
 Here, the implementation seems to ignore a change in direction.
 
 ## Apple placement
 
 To ensure reproducible behavior, we specify exactly how a random number generator must be used to place the food. The random number generator itself is passed
 to the SnakeLogic class as an argument called `randomGen: RandomGenerator` . This
 `RandomGenerator` class has the following method:
 ```scala 
 def randomInt(upTo: Int): Int
```
This gives a random integer in the range [0 − upTo ) (exclusive). During testing, a dummy
integer generator will be passed to SnakeLogic which generates the numbers specified
in the test frames.

The apple must be placed randomly at a free spot on the board. You might be tempted
to randomly place the apple using the following method, which we DO NOT USE:
1. Pick a random horizontal coordinate.
2. Pick a random vertical coordinate.
3. If the resulting coordinate is not free, try again.

This method has the downside that it might take a lot of tries before you find a free spot,
especially if the board is almost full. Moreover, it requires arbitrary amount of numbers
for placing a single apple, which complicates testing.

Instead of the above method, we opt for a method which always succeeds at placing an
apple, using only a single number. To see how this works, suppose we have a 5x3 board
and the current position of the snake is as follows:
```
.....
.OO>.
.....
```
To determine the position of the apple, we want to pick a random free spot on the board
(the apple should not be placed on the snake). We number the free spots on the board
from left to right, top to bottom, skipping any spot occupied to by snake. In our example,
this means the following numbering:
```
 0 | 1 | 2 | 3 | 4 
---+---+---+---+---
 5 | X | X | X | 6 
---+---+---+---+---
 7 | 8 | 9 | 10| 11   
```
To pick a random spot, first compute the number of free spots and then call `randomGen.randomInt(nrFreeSpots)`
(in our case nrFreeSpots=12 ). Then  place the apple at the position corresponding to the
number you got.

