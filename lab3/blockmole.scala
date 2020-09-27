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
    import introprog.PixelWindow

    val windowSize = (30, 50)
    val blockSize = 10

    val window = new PixelWindow(windowSize._1 * blockSize, windowSize._2 * blockSize, "Spela Blockmole")

    type Pos = (Int, Int)

    def block(pos: Pos)(color: JColor = JColor.gray): Unit = {
        val x = pos._1 * blockSize
        val y = pos._2 * blockSize
        window.fill(x, y, blockSize, blockSize, color) 
    }

    def rektangel(leftTop: Pos)(size: (Int, Int))(color: JColor = JColor.gray): Unit = {
        for (y <- leftTop._2 until size._2) {
            for (x <- leftTop._1 until size._1){
                block(x, y)(color)

            }
        }
    }

    val maxWaitMillis = 10

    def waitForKey(): String = {
        window.awaitEvent(maxWaitMillis)
        while (window.lastEventType != PixelWindow.Event.KeyPressed) {
            window.awaitEvent(maxWaitMillis)
        }
        println(s"KeyPressed: " + window.lastKey)
        window.lastKey
    }

}

object mole {
    def dig(): Unit = {
        println("Här ska det grävas!")
        var x = Blockwindow.windowSize._1 / 2
        var y = Blockwindow.windowSize._2 / 2
        var quit = false
        while (!quit) {
            Blockwindow.block(x, y)(Color.mole)
            val key = Blockwindow.waitForKey()
            Blockwindow.block(x, y)(Color.tunnel)
            if (key == "w") y -= 1
            else if (key == "a") x -= 1
            else if (key == "s") y += 1
            else if (key == "d") x += 1
            else if (key == "q") quit = true

        }
    }
}

object Main{
    def drawWorld(): Unit = {
        println("Ska rita ut underjorden!")
        Blockwindow.rektangel(0, 0)(Blockwindow.windowSize._1, 10)(Color.sky)
        Blockwindow.rektangel(0, 10)(Blockwindow.windowSize._1, 20)(Color.grass)
        Blockwindow.rektangel(0, 20)(Blockwindow.windowSize._1, Blockwindow.windowSize._2)(Color.soil)

    }

    def main(args: Array[String]): Unit = {
        drawWorld()
        mole.dig()
    }

}

