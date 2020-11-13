package life

case class Life(cells: Matrix[Boolean]) {

    def apply(row: Int, col: Int): Boolean = {
        if (row >= 0 && row < cells.dim._1 && col >= 0 && col < cells.dim._2) cells(row, col)
        else false
    }

    def updated(row: Int, col: Int, value: Boolean): Life = {
        //Life(Matrix(cells.data.updated(row, cells.data(row).updated(col, value))))
        Life(cells.updated(row, col)(value))
    }

    def toggled(row: Int, col: Int): Life = {
        //if(cells(row, col) == true) updated(row, col, false)
        //else updated(row, col, true)
        updated(row, col, !apply(row, col))
    }

    def nbrOfNeighbours(row: Int, col: Int): Int = {
        var nbr = 0
        for (i <- row - 1 to row + 1; j <- col - 1 to col + 1) {
            if (!(i == row && j == col) && apply(i, j)) nbr += 1
        }
        nbr
    }

    /** Skapar en ny Life-instans med nästa generation av universum.
     * Detta sker genom att applicera funktionen \code(rule) på cellerna
     */
    def evolved(rule: (Int, Int, Life) => Boolean = Life.defaultRule): Life = {
        var nextGeneration = Life.empty(cells.dim)
        cells.foreachIndex { 
            (r,c) => nextGeneration = nextGeneration.updated(r, c, rule(r, c, this))
        }
        nextGeneration
    }

//  def foreachIndex(f: (Int, Int) => Unit): Unit = {                 ska raderas
//      for(i <- data.indices; c <- data(i).indices)f(i,c)            ska raderas
//  }                                                                 ska raderas
    
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