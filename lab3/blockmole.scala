package blockmole

import java.awt.{Color => JColor}

object Color {
    val black = new JColor(0, 0, 0)
    val mole = new JColor(51, 51, 0)
    val soil = new JColor(153, 102, 51)
    val tunnel = new JColor(204, 153, 102)
    val grass = new JColor(25, 130, 35)
    val sky = new JColor(0, 0, 100)
}

object Blockwindow {

}

object mole {
    def dig(): Unit = println("Här ska det grävas!")
}



object Main{
    def drawWorld(): Unit = println("Ska rita ut underjorden!")

    def main(args: Array[String]): Unit = {
        drawWorld()
        mole.dig()
    }

}
