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
    val window: PixelWindow = new PixelWindow(rows * blockSize + 1, cols * blockSize + 1, "Life")
    var quit = false
    var play = false

    def drawGrid(): Unit = {
        window.fill(0, 0, rows * blockSize + 1, cols * blockSize + 1, Color.white)
        for(i <- 0 until rows){
            for(j <- 0 until cols){
                drawCell(i, j)
            }
        }
    }

    def drawCell(row: Int, col: Int): Unit = {
        var a = Color.black
        if(life(row, col) == true){a = Color.pink} 
        else{a = Color.black}
        window.fill(row * blockSize + 1, col * blockSize + 1, blockSize - 1, blockSize - 1, a)
    }

    def update(newLife: Life): Unit = {
        val oldLife = life
        life = newLife
        life.cells.foreachIndex{
            (r, c) => if(oldLife(r, c) != life(r, c)){drawCell(r, c)}
        }
    }

    def handleKey(key: String): Unit = {
        //println(key)
        if(key == "Enter") update(life.evolved())
        else if(key == " "){if(play == false){play = true} else{play = false}}
        else if(key == "r"){life = Life.random((rows, cols)); drawGrid()}
        else if(key == "Backspace"){life = Life.empty(rows, cols); drawGrid()}
        else println("Not a registerd key")
    }

    def handleClick(pos: (Int, Int)): Unit = {
        life = life.toggled(pos._1 / blockSize, pos._2 / blockSize)
        drawCell(pos._1 / blockSize, pos._2 / blockSize)
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