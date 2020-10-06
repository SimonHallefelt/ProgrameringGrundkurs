package blockbattle

object Game{
    val windowSize = (30,50)
    val windowTitle = "EPIC BLOCK BATTLE"
    val blockSize = 14
    val skyRange = 0 to 7
    val grassRange = 8 to 8
    object Color {???}

    def backgroundCollorAtDepth(y: Int): java.awt.Color = {
        ???
    }
}

class Game(
    val leftPlayerName: String = "LEFT",
    val rightPlayerName: String = "RIGHT"
){
    import Game._

    val window = new BlockWindow(windowSize, windowTitle, blockSize)
    val leftMole: Mole = ???
    val rightMole: Mole = ???

    def drawWorld(): Unit = ???

    def eraseBlocks(x1: Int, y1: Int, x2: Int, y2: Int): Unit = ???

    def update(mole: Mole): Unit = ???

    def gameLoop(): Unit = ???

    def start(): Unit = {
        println("Start digging!")
        println(s"$leftPlayerName ${leftMole.keyControl}")
        println(s"$rightPlayerName ${rightMole.keyControl}")
        drawWorld()
        gameLoop()
    }
}