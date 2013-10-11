package ch.jamesclonk.sudoku

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SudokuSuite
  extends FunSuite {

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

  trait Test1 extends Solver {
    val data = Vector(
      0, 6, 0, 1, 0, 4, 0, 5, 0,
      0, 0, 8, 3, 0, 5, 6, 0, 0,
      2, 0, 0, 0, 0, 0, 0, 0, 1,
      8, 0, 0, 4, 0, 7, 0, 0, 6,
      0, 0, 6, 0, 0, 0, 3, 0, 0,
      7, 0, 0, 9, 0, 1, 0, 0, 4,
      5, 0, 0, 0, 0, 0, 0, 0, 2,
      0, 0, 7, 2, 0, 6, 9, 0, 0,
      0, 4, 0, 5, 0, 8, 0, 7, 0)
    val solution = Vector(
      9, 6, 3, 1, 7, 4, 2, 5, 8,
      1, 7, 8, 3, 2, 5, 6, 4, 9,
      2, 5, 4, 6, 8, 9, 7, 3, 1,
      8, 2, 1, 4, 3, 7, 5, 9, 6,
      4, 9, 6, 8, 5, 2, 3, 1, 7,
      7, 3, 5, 9, 6, 1, 8, 2, 4,
      5, 8, 9, 7, 1, 3, 4, 6, 2,
      3, 1, 7, 2, 4, 6, 9, 8, 5,
      6, 4, 2, 5, 9, 8, 1, 7, 3)
  }

  trait Test2 extends Solver {
    val input =
      """
    		001005300
			050490000
			000102064
			000000750
			600000001
			035000000
			460903000
			000024090
			003600100"""
        .trim.replaceAll("[^0-9]", "")
        .trim.toList
        .map(_.toString.trim.toInt)
    val data = Vector(input: _*)
    val solution = Vector(
      2, 4, 1, 8, 6, 5, 3, 7, 9,
      3, 5, 6, 4, 9, 7, 2, 1, 8,
      8, 7, 9, 1, 3, 2, 5, 6, 4,
      1, 9, 4, 3, 8, 6, 7, 5, 2,
      6, 8, 2, 5, 7, 9, 4, 3, 1,
      7, 3, 5, 2, 4, 1, 9, 8, 6,
      4, 6, 7, 9, 1, 3, 8, 2, 5,
      5, 1, 8, 7, 2, 4, 6, 9, 3,
      9, 2, 3, 6, 5, 8, 1, 4, 7)
  }

  trait HardTest1 extends Solver {
    val input =
      """
        000 000 000
        000 003 085
        001 020 000
        
        000 507 000
        004 000 100
        090 000 000
        
        500 000 073
        002 010 000
        000 040 009
        """
        .trim.replaceAll("[^0-9]", "")
        .trim.toList
        .map(_.toString.trim.toInt)
    val data = Vector(input: _*)
    val solution = Vector(
      9, 8, 7, 6, 5, 4, 3, 2, 1,
      2, 4, 6, 1, 7, 3, 9, 8, 5,
      3, 5, 1, 9, 2, 8, 7, 4, 6,
      1, 2, 8, 5, 3, 7, 6, 9, 4,
      6, 3, 4, 8, 9, 2, 1, 5, 7,
      7, 9, 5, 4, 6, 1, 8, 3, 2,
      5, 1, 9, 2, 8, 6, 4, 7, 3,
      4, 7, 2, 3, 1, 9, 5, 6, 8,
      8, 6, 3, 7, 4, 5, 2, 1, 9)
  }

  trait HardTest2 extends Solver {
    val data = Vector(
      1, 2, 0, 4, 0, 0, 3, 0, 0,
      3, 0, 0, 0, 1, 0, 0, 5, 0,
      0, 0, 6, 0, 0, 0, 1, 0, 0,
      7, 0, 0, 0, 9, 0, 0, 0, 0,
      0, 4, 0, 6, 0, 3, 0, 0, 0,
      0, 0, 3, 0, 0, 2, 0, 0, 0,
      5, 0, 0, 0, 8, 0, 7, 0, 0,
      0, 0, 7, 0, 0, 0, 0, 0, 5,
      0, 0, 0, 0, 0, 0, 0, 9, 8)
    val solution = Vector(1, 2, 8, 4, 6, 5, 3, 7, 9, 3, 7, 4, 2, 1, 9, 8, 5, 6, 9, 5, 6, 8, 3, 7, 1, 4, 2, 7, 6, 5, 1, 9, 8, 4, 2, 3, 2, 4, 9, 6, 7, 3, 5, 8, 1, 8, 1, 3, 5, 4, 2, 9, 6, 7, 5, 9, 2, 3, 8, 6, 7, 1, 4, 4, 8, 7, 9, 2, 1, 6, 3, 5, 6, 3, 1, 7, 5, 4, 2, 9, 8)
  }

  trait InvalidBoardTest1 extends Solver {
    val data = Vector(
      0, 6, 0, 1, 0, 4, 0, 5, 0,
      0, 0, 8, 3, 0, 5, 6, 7, 0,
      2, 0, 0, 0, 0, 0, 0, 1, 1,
      8, 0, 0, 4, 0, 7, 0, 0, 6,
      0, 0, 6, 0, 6, 0, 3, 0, 0,
      7, 6, 0, 9, 0, 1, 0, 0, 4,
      5, 0, 0, 0, 0, 0, 0, 0, 2,
      0, 0, 7, 2, 0, 6, 9, 0, 0,
      0, 4, 0, 6, 7, 8, 0, 7, 0)
  }

  test("coordinatesOfPos") {
    new Test1 {
      assert(coordinatesOfPos(1) === (0, 1))
      assert(coordinatesOfPos(11) === (1, 2))
      assert(coordinatesOfPos(39) === (4, 3))
      assert(coordinatesOfPos(59) === (6, 5))
      assert(coordinatesOfPos(78) === (8, 6))
    }
  }

  test("cellCoordinatesOfPos") {
    new Test1 {
      assert(cellCoordinatesOfPos(1) === (0, 0))
      assert(cellCoordinatesOfPos(14) === (0, 1))
      assert(cellCoordinatesOfPos(25) === (0, 2))
      assert(cellCoordinatesOfPos(37) === (1, 0))
      assert(cellCoordinatesOfPos(50) === (1, 1))
      assert(cellCoordinatesOfPos(33) === (1, 2))
      assert(cellCoordinatesOfPos(63) === (2, 0))
      assert(cellCoordinatesOfPos(58) === (2, 1))
      assert(cellCoordinatesOfPos(78) === (2, 2))
    }
  }

  test("posOfCoordinates") {
    new Test1 {
      assert(posOfCoordinates((0, 1)) === 1)
      assert(posOfCoordinates((1, 2)) === 11)
      assert(posOfCoordinates((4, 3)) === 39)
      assert(posOfCoordinates((6, 5)) === 59)
      assert(posOfCoordinates((8, 6)) === 78)
    }
  }

  test("cellPositions") {
    new Test1 {
      assert(cellPositions((0, 0)).sorted === List(0, 1, 2, 9, 10, 11, 18, 19, 20))
      assert(cellPositions((1, 1)).sorted === List(30, 31, 32, 39, 40, 41, 48, 49, 50))
      assert(cellPositions((2, 1)).sorted === List(57, 58, 59, 66, 67, 68, 75, 76, 77))
    }
  }

  test("positions & emptyPositions") {
    new Test1 {
      assert(positions.take(5) === List((0, 0), (1, 6), (2, 0), (3, 1), (4, 0)))
      assert(emptyPositions.take(5) === List(0, 2, 4, 6, 8))
    }
  }

  test("allVerticalPositionsOfPos") {
    new Test1 {
      assert(allVerticalPositionsOfPos(0).sorted === List((0 to 8) map (_ * 9): _*))
      assert(allVerticalPositionsOfPos(51).sorted === List(6, 15, 24, 33, 42, 51, 60, 69, 78))
    }
  }

  test("allHorizontalPositionsOfPos") {
    new Test1 {
      assert(allHorizontalPositionsOfPos(0).sorted === List((0 to 8): _*))
      assert(allHorizontalPositionsOfPos(51).sorted === List((45 to 53): _*))
    }
  }

  test("valueOfPos") {
    new Test1 {
      assert(valueOfPos(13) === 0)
      assert(valueOfPos(38) === 6)
      assert(valueOfPos(69) === 9)
      assert(valueOfPos(79) === 7)
    }
  }

  test("valueOfCoordinates") {
    new Test1 {
      assert(valueOfCoordinates((0, 1)) === 6)
      assert(valueOfCoordinates((5, 3)) === 9)
      assert(valueOfCoordinates((8, 5)) === 8)
      assert(valueOfCoordinates((6, 2)) === 0)
    }
  }

  test("valuesOfPositions") {
    new Test1 {
      assert(valuesOfPositions(List(0, 1, 2)) === List((0, 0), (1, 6), (2, 0)))
      assert(valuesOfPositions(List(79, 78, 77)) === List((79, 7), (78, 0), (77, 8)))
    }
  }

  test("valuesOfCoordinates") {
    new Test1 {
      assert(valuesOfCoordinates(List((0, 0), (0, 1), (0, 2))) === List(((0, 0), 0), ((0, 1), 6), ((0, 2), 0)))
      assert(valuesOfCoordinates(List((8, 7), (8, 6), (8, 5))) === List(((8, 7), 7), ((8, 6), 0), ((8, 5), 8)))
    }
  }

  test("allPossibleValuesOfPos") {
    new Test1 {
      assert(allPossibleValuesOfPos(0) === List(3, 9))
      assert(allPossibleValuesOfPos(60) === List(1, 4, 8))
      assert(allPossibleValuesOfPos(23) === List(9))
    }
  }

  test("isValidCell") {
    new Test1 {
      assert(isValidCell((0, 0)) === true)
      assert(isValidCell((0, 1)) === true)
      assert(isValidCell((0, 2)) === true)
      assert(isValidCell((1, 0)) === true)
      assert(isValidCell((1, 1)) === true)
      assert(isValidCell((1, 2)) === true)
      assert(isValidCell((2, 0)) === true)
      assert(isValidCell((2, 1)) === true)
      assert(isValidCell((2, 2)) === true)
    }
    new InvalidBoardTest1 {
      assert(isValidCell((0, 0)) === true, "0,0")
      assert(isValidCell((0, 1)) === true, "0,1")
      assert(isValidCell((0, 2)) === false, "0,2")
      assert(isValidCell((1, 0)) === false, "1,0")
      assert(isValidCell((1, 1)) === true, "1,1")
      assert(isValidCell((1, 2)) === true, "1,2")
      assert(isValidCell((2, 0)) === true, "2,0")
      assert(isValidCell((2, 1)) === false, "2,1")
      assert(isValidCell((2, 2)) === true, "2,2")
    }
  }

  test("isValidPos") {
    new Test1 {
      assert(isValidPos(2) === true)
      assert(isValidPos(13) === true)
      assert(isValidPos(38) === true)
      assert(isValidPos(69) === true)
      assert(isValidPos(79) === true)
      assert(isValidPos(50) === true)
      assert(isValidPos(60) === true)
    }
    new InvalidBoardTest1 {
      assert(isValidPos(1) === false, "1")
      assert(isValidPos(2) === true, "2")
      assert(isValidPos(15) === false, "15")
      assert(isValidPos(16) === false, "16")
      assert(isValidPos(37) === false, "37")
      assert(isValidPos(38) === false, "38")
      assert(isValidPos(76) === false, "76")
      assert(isValidPos(79) === false, "79")
      assert(isValidPos(80) === true, "80")
    }
  }

  test("Solver - Test1") {
    new Test1 {
      val sit = Board(data)
      assert(solve1(sit) === Some(Board(solution)))
      assert(solve2(sit) === Some(Board(solution)))
    }
  }

  test("Solver - Test2") {
    new Test2 {
      val sit = Board(data)
      assert(solve1(sit) === Some(Board(solution)))
      assert(solve2(sit) === Some(Board(solution)))
    }
  }

  test("Solver - with invalid Board") {
    new InvalidBoardTest1 {
      val sit = Board(data)
      assert(solve1(sit) === None)
      assert(solve2(sit) === None)
    }
  }

  test("Solver - HardTest1") {
    new HardTest1 {
      val sit = Board(data)
      //assert(solve1(sit) === Some(Board(solution)))  // to bad! takes too long..
      assert(solve2(sit) === Some(Board(solution)))
    }
  }

  test("Solver - HardTest2") {
    new HardTest2 {
      val sit = Board(data)
      //assert(solve1(sit) === Some(Board(solution)))  // to bad! takes waaaaay too long..
      assert(solve2(sit) === Some(Board(solution)))  // takes long here too.. really hard board!
    }
  }

}

