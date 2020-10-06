package blockbattle

import java.awt.{Color => JColor}


object Game{
    val windowSize = (30,50)
    val windowTitle = "EPIC BLOCK BATTLE"
    val blockSize = 14
    val skyRange = 0 to 7
    val grassRange = 8 to 8
    object Color {
        val black = new JColor(0, 0, 0)
        val mole1 = new JColor(51, 51, 0)
        val mole2 = new JColor(70, 51, 0)
        val soil = new JColor(153, 102, 51)
        val tunnel = new JColor(204, 153, 102)
        val grass = new JColor(25, 130, 35)
        val sky = new JColor(0, 0, 100)
        val worm = new JColor(225, 100, 235) 
    }

    def backgroundCollorAtDepth(y: Int): java.awt.Color = {
//        eraseBlocks()
        
        if (skyRange contains(y)) Color.sky
        else if (grassRange contains(y)) Color.grass
        else Color.soil

    }
}

class Game(
    val leftPlayerName: String = "LEFT",
    val rightPlayerName: String = "RIGHT"
){
    import Game._

    val window = new BlockWindow(windowSize, windowTitle, blockSize)
    val leftMole: Mole = {
        val keyControl = KeyControl(left="a",down="s",right="d",up="w")
        val pos = Pos(14, 25)
        Mole(name=leftPlayerName, pos, dir=(-1,0), Color.mole1, keyControl)
    }
    val rightMole: Mole = {
        val keyControl = KeyControl(left="Left",down="Down",right="Right",up="Up")
        val pos = Pos(16, 26)
        Mole(name=rightPlayerName, pos, dir=(1,0), Color.mole2, keyControl)
    }

    def drawWorld(): Unit = {
        for(y <- 0 to windowSize._2){
            for(x <- 0 to windowSize._1){
                window.setBlock(Pos(x, y), backgroundCollorAtDepth(y))
            }
        }
        window.setBlock(Pos(15, 15), java.awt.Color.red)
    }

    def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
        ???
    }

    def handleEvents(): Unit = {
        var e = window.nextEvent()
        while(e != BlockWindow.Event.Undefined) {
            e match {
                case BlockWindow.Event.KeyPressed(key) =>
                    leftMole.setDir(key)
                    rightMole.setDir(key)

                case BlockWindow.Event.WindowClosed =>
                    ???
            }
            e = window.nextEvent()
        }
    }

    def update(mole: Mole): Unit = {
        window.setBlock(mole.pos, Color.tunnel)
        mole.move()
        window.setBlock(mole.pos, mole.color)
    }

    var quit = false
    val delayMillis = 200

    def gameLoop(): Unit = {
        while(!quit) {
            val t0 = System.currentTimeMillis
            handleEvents()
            update(leftMole)
            update(rightMole)

            val elapsedMillis = (System.currentTimeMillis - t0).toInt
            Thread.sleep((delayMillis - elapsedMillis) max 0)
        }
    }

    def start(): Unit = {
        println("Start digging!")
        println(s"$leftPlayerName ${leftMole.keyControl}")
        println(s"$rightPlayerName ${rightMole.keyControl}")
        drawWorld()
        gameLoop()
    }
}