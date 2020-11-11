package life

case class Life(cells: Matrix[Boolean]) {

    def apply(row: Int, col: Int): Boolean = {
        //println(row + "  +  " + col)
        if (row >= 0 && row < 20 && col >= 0 && col < 15) cells(row, col)
        else false
    }

    def updated(row: Int, col: Int, value: Boolean): Life = {
        //println(row + "  +  " + col)
        //println(Life(Matrix(cells.data.updated(row, cells.data(row).updated(col, value)))))
        Life(Matrix(cells.data.updated(row, cells.data(row).updated(col, value))))
    }

    def toggled(row: Int, col: Int): Life = {
        if(cells(row, col) == true) updated(row, col, false)
        else updated(row, col, true)
    }

    def nmrOfNeighbours(row: Int, col: Int): Int = {
        var nbr = 0
        for (i <- row - 1 to row + 1; j <- col - 1 to col + 1) {
            if (!(i == row && j == col) && apply(i, j)) nbr += 1
        }
        nbr
    }

    def evolved(rule: (Int, Int, Life) => Boolean = Life.defaultRule): Life = {
        var nextGeneration = Life.empty(cells.dim)
        cells.foreachIndex { 
            (r,c) => ???
        }
        nextGeneration
    }
    
    override def toString = {
        cells.data.map(xs => xs.map(cell => if (cell) "0" else "-").mkString("")).mkString("\n")
    }

}

object Life {

    def empty(dim: (Int, Int)): Life = {
        var xss = new Life(new Matrix[Boolean](Vector.fill(dim._1, dim._2)(false)))
        xss
    }

    def random(dim: (Int, Int)): Life = ???

    def defaultRule(row: Int, col: Int, current: Life): Boolean = ???
}