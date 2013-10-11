package ch.jamesclonk.sudoku

object Sudoku {

  def main(args: Array[String]): Unit = {

    new Solver {
      val input =
        """001005300
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

      println(solve2(Board(data)).getOrElse("No solution!"))
    }
    
    new Solver {
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

      println(solve2(Board(data)).getOrElse("No solution!"))
    }
  }

}

