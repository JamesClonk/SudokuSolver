package ch.jamesclonk.sudoku

import scala.util.Random
import scala.annotation.tailrec

trait Solver extends GameDef {

  def solve1(board: Board): Option[Board] = {

    def loop(board: Board): List[Board] = {
      // no more empty positions, board is full!
      if (board.data.indexOf(0) < 0) List(board)
      else {
        val emptyPos = board.data.indexOf(0)
        val newBoards = board.allPossibleValuesOfPos(emptyPos) map (value => Board(board.data.updated(emptyPos, value)))

        val result = for {
          board <- newBoards
        } yield loop(board)

        if (result.isEmpty) Nil
        else result.flatten.toList
      }
    }

    loop(board).headOption
  }

  def solve2(board: Board): Option[Board] = {

    def loop(board: Board, listOfEmptyPositions: IndexedSeq[(Int,IndexedSeq[Int])]): List[Board] = {
      // no more empty positions, board is full!
      if (listOfEmptyPositions.isEmpty) List(board)
      else {
        val emptyPos = listOfEmptyPositions.head
        val newBoards = board.allPossibleValuesOfPosFromRange(emptyPos._1, emptyPos._2) map (value => Board(board.data.updated(emptyPos._1, value)))

        val result = for {
          board <- newBoards
        } yield loop(board, listOfEmptyPositions.tail)

        if (result.isEmpty) Nil
        else result.flatten.toList
      }
    }

    @tailrec
    def fillEmptyPosWhereOnlyOnePossibleValueExists(board: Board, listOfPositions: List[(Int,Int)]): Board = listOfPositions match {
      case Nil => board
      case t :: ts => fillEmptyPosWhereOnlyOnePossibleValueExists(Board(board.data.updated(t._1, t._2)), ts)
    }

    val emptyPositionsWithPossibleValues = for {
      pos <- emptyPositions
    } yield (pos, board.allPossibleValuesOfPos(pos))

    val sortedEmptyPositionsWithPossibleValues =
      emptyPositionsWithPossibleValues sortWith (_._2.length < _._2.length)

    if (sortedEmptyPositionsWithPossibleValues.isEmpty || sortedEmptyPositionsWithPossibleValues.head._2.isEmpty) None
    else {
      val (emptyWithOnePossibleValue, emptyWithMultiplePossibleValues) =
        sortedEmptyPositionsWithPossibleValues span (_._2.length <= 1)

      val startBoard = fillEmptyPosWhereOnlyOnePossibleValueExists(
          board, 
          emptyWithOnePossibleValue map (t => (t._1, t._2.head)) toList
          )

      loop(startBoard, emptyWithMultiplePossibleValues).headOption
    }
  }

}