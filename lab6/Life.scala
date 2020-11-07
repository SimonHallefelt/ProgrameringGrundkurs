package life

case class Life(cells: Matrix[Boolean]) {

    def apply(row: Int, col: Int): Boolean = {
        if (row >= 0 && row < 20 && col >= 0 && col < 15) cells(row, col)
        else false
    }

    def updated(row: Int, col: Int, value: Boolean): Life = {
        println(row + "  +  " + col)
        Life(cells(row, col) = value)
        //println(Life(cells.updated(row, cells(row))))
        Life(cells(row, col).updated(col, value))
        Life(cells.updated(row, cells(row).updated(col, value)))
        //Matrix(data.updated(row, data(row).updated(col, value)))
        //???
    }

    def toggled(row: Int, col: Int): Life = {
        if(cells(row, col) == true) updated(row, col, false)
        else updated(row, col, true)
    }

    def nmrOfNeighbours(row: Int, col: Int): Int = ???

    def evolved(rule: (Int, Int, Life) => Boolean = Life.defaultRule): Life = {
        var nextGeneration = Life.empty(cells.dim)
        cells.foreachIndex { 
            (r,c) => ???
        }
        nextGeneration
    }
    
    override def toString = {
        //var a = cells.update(true, "0")
        //a = cells.update(false, "-")
        //for(i <- cells.indices){}
        //s"""${ cells.map(_.mkString(" ").mkString("\n"))}"""
        println(cells)
        ???
    }

}

object Life {

    def empty(dim: (Int, Int)): Life = {
        var xss = new Life(new Matrix[Boolean](Vector.fill(dim._1, dim._2)(false)))
        //println(xss)
        xss
    }

    def random(dim: (Int, Int)): Life = ???

    def defaultRule(row: Int, col: Int, current: Life): Boolean = ???
}