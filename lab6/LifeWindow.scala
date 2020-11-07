package life

import introprog.PixelWindow
import introprog.PixelWindow.Event
//import org.w3c.dom.events.Event
import java.awt.{Color => JColor}

object LifeWindow {
    val EventMaxWait = 1
    var NextGenerationDelay = 200
    object Color {
        val black = new JColor(0, 0, 0)
        val pink  = new JColor(242, 128, 161)
        val white = new JColor(252,252,252)
    }

}

class LifeWindow(rows: Int, cols: Int){
    import LifeWindow._

    var life = Life.empty(rows, cols)
    val blockSize = 30
    val window: PixelWindow = new PixelWindow(rows * blockSize, cols * blockSize, "Life")
    var quit = false
    var play = false

    def drawGrid(): Unit = {
        window.fill(0, 0, rows * blockSize, cols * blockSize, Color.white)
        for(i <- 0 until rows){
            for(j <- 0 until cols){
                drawCell(i, j)
            }
        }
    }

    def drawCell(row: Int, col: Int): Unit = {
        var a = Color.black
        //println(life(row,col))
        if(life(row, col) == true){a = Color.pink} 
        else{a = Color.black}
        window.fill(row * blockSize + 1, col * blockSize + 1, blockSize - 1, blockSize - 1, a)
    }

    def update(newLife: Life): Unit = {
        val oldLife = life
        life = newLife
        life.cells.foreachIndex{
            ???
        }
    }

    def handleKey(key: String): Unit = ???

    def handleClick(pos: (Int, Int)): Unit = {
        life.toggled(pos._1 / blockSize, pos._2 / blockSize)
    }

    def loopUntilQuit(): Unit = while (!quit) {
        val t0 = System.currentTimeMillis
        if (play) update(life.evolved())
        window.awaitEvent(EventMaxWait)
        while (window.lastEventType != PixelWindow.Event.Undefined) {
            window.lastEventType match {
                case Event.KeyPressed => handleKey(window.lastKey)
                case Event.MousePressed => handleClick(window.lastMousePos)
                case Event.WindowClosed => quit = true
                case _ =>
            }
            window.awaitEvent(EventMaxWait)
        }
        val elapsed = System.currentTimeMillis - t0
        Thread.sleep((NextGenerationDelay - elapsed) max 0)
    }

    def start(): Unit = { drawGrid(); loopUntilQuit }
}