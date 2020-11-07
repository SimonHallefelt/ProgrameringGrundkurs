case class Matrix[T](data: Vector[Vector[T]]){
    require(data.forall(row => row.length == data(0).length))

    val dim: (Int, Int) = (data.length, data(0).length)

    override def toString = {
        s"""Matrix of dim $dim:\n${ data.map(_.mkString(" ").mkString("\n"))}"""
    }

    def updated(row: Int, col: Int)(value: T): Matrix[T] = {
        Matrix(data.updated(row, data(row).updated(col, value)))
    }

    def foreachIndex(f: (Int, Int) => Unit): Unit = {
        for(i <- data.indices; c <- data(i).indices)f(i,c)
    }

    def apply(row: Int, col: Int): T = data(row)(col)
}
object Matrix {
    def fill[T](dim: (Int, Int))(value: T): Matrix[T] = {
        Matrix[T](Vector.fill(dim._1, dim._2)(value))
    }
}