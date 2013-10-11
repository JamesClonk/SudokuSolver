package ch.jamesclonk.sudoku

trait GameDef {

  type Data = Vector[Int]
  val data: Data
  
    /*
------------------------------------------------
		 0  1  2    3  4  5    6  7  8
		 9 10 11   12 13 14   15 16 17
		18 19 20   21 22 23   24 25 26
		
		27 28 29   30 31 32   33 34 35
		36 37 38   39 40 41   42 43 44
		45 46 47   48 49 50   51 52 53
		
		54 55 56   57 58 59   60 61 62
		63 64 65   66 67 68   69 70 71
		72 73 74   75 76 77   78 79 80
------------------------------------------------
   */

  def coordinatesOfPos(pos: Int): (Int, Int) = {
    val row = pos / 9
    val col = pos - (row * 9)
    (row, col)
  }

  def cellCoordinatesOfPos(pos: Int): (Int, Int) = {
    val cellRow = pos / 27
    val cellCol = (pos % 9) / 3
    (cellRow, cellCol)
  }

  def posOfCoordinates(coord: (Int, Int)): Int = {
    val (row, col) = coord
    row * 9 + col
  }

  def cellPositions(coord: (Int, Int)): IndexedSeq[Int] = {
    val (cellRow, cellCol) = coord

    val rowPos = cellRow * 27
    val colPos = cellCol * 3
    val topLeftPos = rowPos + colPos

    for {
      col <- 0 to 2
      row <- 0 to 2
    } yield (topLeftPos + col + (row * 9))
  }
  
  def positions = (0 to 80) zip data
  def emptyPositions = positions filter (_._2 == 0) map (_._1)
  
  def allVerticalPositionsOfPos(pos: Int) = allVerticalPositionsOfCoordinates(coordinatesOfPos(pos))
  def allVerticalPositionsOfCoordinates(coord: (Int, Int)) =
    (0 to 8) map (r => posOfCoordinates((r, coord._2)))
    
  def allHorizontalPositionsOfPos(pos: Int) = allHorizontalPositionsOfCoordinates(coordinatesOfPos(pos))
  def allHorizontalPositionsOfCoordinates(coord: (Int, Int)) =
    (0 to 8) map (c => posOfCoordinates((coord._1, c)))

  def valueOfPos(pos: Int): Int = data(pos)
  def valueOfCoordinates(coord: (Int, Int)): Int = valueOfPos(posOfCoordinates(coord))
  
  def valuesOfPositions(positions: List[Int]) =
    positions map (p => (p, valueOfPos(p)))
  def valuesOfCoordinates(coordinates: List[(Int, Int)]) =
    coordinates map (c => (c, valueOfCoordinates(c)))
  
  def allPossibleValuesOfPos(pos: Int) = allPossibleValuesOfPosFromRange(pos, 1 to 9)
  def allPossibleValuesOfPosFromRange(pos: Int, range: IndexedSeq[Int]) = {
    val vertical = allVerticalPositionsOfPos(pos) map valueOfPos
    val horizontal = allHorizontalPositionsOfPos(pos) map valueOfPos
    val cell = cellPositions(cellCoordinatesOfPos(pos)) map valueOfPos
    
    range filter { value => 
    	!vertical.contains(value) &&
    	!horizontal.contains(value) &&
    	!cell.contains(value)
    }
  }

  def isValidCell(coord: (Int, Int)): Boolean = {
    val values = cellPositions(coord) map valueOfPos
    values.distinct.sum == values.sum
  }

  def isValidPos(pos: Int): Boolean = {
    val value = data(pos)
    val (row, col) = coordinatesOfPos(pos)

    // vertical - rows
    val vertical = {
      if (value == 0) true
      else (0 to 8) forall (r => if (r != row) valueOfCoordinates((r, col)) != value else true)
    }

    // horizontal - columns
    val horizontal = {
      if (value == 0) true
      else (0 to 8) forall (c => if (c != col) valueOfCoordinates((row, c)) != value else true)
    }

    val cell = isValidCell(cellCoordinatesOfPos(pos))

    vertical && horizontal && cell
  }

  sealed case class Board(val data: Data) extends GameDef

}