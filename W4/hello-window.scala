package hello

object Main {
    val w = new introprog.PixelWindow(400, 300, "HEJ")

    var color = java.awt.color.red

    def square(p: (Int, Int))(side: Int): Unit = if (side > 0) {
        val d = side - 1
        w.line(p._1,     p._2,     p._1 + d, p._2,     color)
        w.line(p._1 + d, p._2,     p._1 + d, p._2 + d, color)
        w.line(p._1 + d, p._2 + d, p._1,     p._2 + d, color)
        w.line(p._1,     p._2 + d, p._1,     p._2,     color)
    }

    def main(args: Array[String]): Unit = {
        println("Rita kvadrat:")
        square(300,100)(50)
    }
}