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
        val mole2 = new JColor(80, 60, 0)
        val soil = new JColor(153, 102, 51)
        val tunnel = new JColor(204, 153, 102)
        val grass = new JColor(25, 130, 35)
        val sky = new JColor(0, 50, 200)
        val worm = new JColor(225, 100, 235) 
    }

    def backgroundCollorAtDepth(y: Int): java.awt.Color = {        
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
    }

    val worm1 = new Worm
    val worm2 = new Worm
    val worm3 = new Worm
    val worm4 = new Worm

    def drawWorm(): Unit = {
        if (worm1.pos == worm2.pos || worm1.pos == worm3.pos || worm1.pos == worm4.pos) {worm1.randomPos; drawWorm()}
        else if(window.getBlock(worm1.pos) != Color.worm) {
            if (window.getBlock(worm1.randomPos) == Color.worm) drawWorm()
            else window.setBlock(worm1.pos, Color.worm)
        }
        if (worm2.pos == worm1.pos || worm2.pos == worm3.pos || worm2.pos == worm4.pos) {worm2.randomPos; drawWorm()}
        else if(window.getBlock(worm2.pos) != Color.worm) {
            if (window.getBlock(worm2.randomPos) == Color.worm) drawWorm()
            else window.setBlock(worm2.pos, Color.worm)
        }
        if (worm3.pos == worm2.pos || worm3.pos == worm1.pos || worm3.pos == worm4.pos) {worm3.randomPos; drawWorm()}
        else if(window.getBlock(worm3.pos) != Color.worm) {
            if (window.getBlock(worm3.randomPos) == Color.worm) drawWorm()
            else window.setBlock(worm3.pos, Color.worm)
        }
        if (worm4.pos == worm2.pos || worm4.pos == worm3.pos || worm4.pos == worm1.pos) {worm4.randomPos; drawWorm()}
        else if(window.getBlock(worm4.pos) != Color.worm) {
            if (window.getBlock(worm4.randomPos) == Color.worm) drawWorm()
            else window.setBlock(worm4.pos, Color.worm)
        }
    }

    def wormRandomMoved: Unit = {
        worm1.randomMove
        worm2.randomMove
        worm3.randomMove
        worm4.randomMove
        if (worm1.pos != worm1.gammalPos) {window.setBlock(worm1.gammalPos, Color.soil); drawWorm()}
        if (worm2.pos != worm2.gammalPos) {window.setBlock(worm2.gammalPos, Color.soil); drawWorm()}
        if (worm3.pos != worm3.gammalPos) {window.setBlock(worm3.gammalPos, Color.soil); drawWorm()}
        if (worm4.pos != worm4.gammalPos) {window.setBlock(worm4.gammalPos, Color.soil); drawWorm()}
    }

    def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = {
        for (x <- x1 to x2){
            for (y <- y1 to y2){
                window.setBlock(Pos(x, y), Color.sky)
            }
        }
    }

    def handleEvents(): Unit = {
        var e = window.nextEvent()
        while(e != BlockWindow.Event.Undefined) {
            e match {
                case BlockWindow.Event.KeyPressed(key) => {
                    leftMole.setDir(key)
                    rightMole.setDir(key)
                }

                case BlockWindow.Event.WindowClosed => {
                    quit = true
                }
            }
            e = window.nextEvent()
        }
    }

    val moveInXRange = 0 to (windowSize._1 - 1)
    val moveInYRange = 8 to (windowSize._2 - 1)
    var poängpos = Pos(0, 0)
    val winCondition = 300

    def update(mole: Mole): Unit = {
        val pointsAtStart = mole.points
        if (moveInXRange contains(mole.nextPos.x)) {
            if (moveInYRange contains(mole.nextPos.y)){
                if (window.getBlock(mole.nextPos) == Color.soil) {mole.points += 1}
                else if (window.getBlock(mole.nextPos) == Color.grass) {mole.points += 2}
                else if (window.getBlock(mole.nextPos) == Color.worm) {mole.points += 20}
                else {mole.points = mole.points}
                window.setBlock(mole.nextPos, mole.color)
                window.setBlock(mole.pos, Color.tunnel)
                mole.move()
                drawWorm()
                
            }
            else mole.reverseDir()
        }
        else mole.reverseDir()
        
        if (pointsAtStart < mole.points){
            if (mole.name == "LEFT") {poängpos = Pos(5, 5)}
            else {poängpos = Pos(20, 5)}
            eraseBlocks(poängpos.x + 3, poängpos.y - 1, poängpos.x + 6, poängpos.y + 1)
            window.write(text = (mole.name + ": " + mole.points), pos = poängpos, color = Color.black)    
        }

        if (mole.points >= winCondition) {
            quit = true
            window.write(text = ("GAME OVER"), pos = Pos(1, windowSize._2 / 2 - 4), color = Color.black, textSize = 5 * blockSize)
            window.write(text = (mole.name + " WIN"), pos = Pos(7, windowSize._2 / 2 + 1), color = Color.black, textSize = 3 * blockSize)
        }
    }

    var quit = false
    val delayMillis = 80

    def gameLoop(): Unit = {
        while(!quit) {
            val t0 = System.currentTimeMillis
            handleEvents()
            wormRandomMoved
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
        drawWorm()
        gameLoop()
    }
}