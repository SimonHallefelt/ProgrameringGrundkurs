package life

case class Life(cells: Matrix[Boolean]) {

    def apply(row: Int, col: Int): Boolean = {
        if (row >= 0 && row < cells.dim._1 && col >= 0 && col < cells.dim._2) cells(row, col)
        else false
    }

    def updated(row: Int, col: Int, value: Boolean): Life = {
        Life(Matrix(cells.data.updated(row, cells.data(row).updated(col, value))))
    }

    def toggled(row: Int, col: Int): Life = {
        if(cells(row, col) == true) updated(row, col, false)
        else updated(row, col, true)
    }

    def nbrOfNeighbours(row: Int, col: Int): Int = {
        var nbr = 0
        for (i <- row - 1 to row + 1; j <- col - 1 to col + 1) {
            if (!(i == row && j == col) && apply(i, j)) nbr += 1
        }
        nbr
    }

    def evolved(rule: (Int, Int, Life) => Boolean = Life.defaultRule): Life = {
        var nextGeneration = Life.empty(cells.dim)
        cells.foreachIndex { 
            (r,c) => ???//Life.defaultRule(r, c, Life)
        }
        nextGeneration
    }
    
    override def toString = {
        cells.data.map(xs => xs.map(cell => if (cell) "0" else "-").mkString("")).mkString("\n")
    }
}


object Life {

    def empty(dim: (Int, Int)): Life = {
        new Life(new Matrix[Boolean](Vector.fill(dim._1, dim._2)(false)))
    }

    def random(dim: (Int, Int)): Life = {
        new Life(new Matrix[Boolean](Vector.fill(dim._1, dim._2)(if(math.random() >= 0.5){true}; else{false})))
    }

    def defaultRule(row: Int, col: Int, current: Life): Boolean = {
        var lifestatus = current(row, col)
        val neighbours = current.nbrOfNeighbours(row, col)

        if(current(row, col) == true){
            if(neighbours != 2 && neighbours != 3){lifestatus = false}
        }
        else{
            if(neighbours == 3){lifestatus = true}
        }
        lifestatus
    }
}