package hello2
//import introprog_2.13-1.1.4.jar

object Color{
    import java.awt.{Color => JColor}
    val mole = new JColor(51, 51, 0)
    val soil = new JColor(153, 102, 51)
    val tunnel = new JColor(204, 153, 102)
}

object Main {
    val w = new introprog.PixelWindow(400, 300, "HEJ")

    type Pt = (Int, Int)

    var color = java.awt.Color.red
    
    def rak(p:Pt)(d: Int) = w.line(p._1, p._2, p._1 + d - 1, p._2, color)

    def fill(p:Pt)(s: Int) = for (i <- 0 until s){rak((p._1, p._2 + i))(s)}

    def main(args: Array[String]): Unit = {
        import Color._
        color = soil
        fill(100, 100)(75)
        color = tunnel
        fill(100, 100)(50)
        color = mole
        fill(150, 150)(25)
    }
}