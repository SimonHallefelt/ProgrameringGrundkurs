package blockbattle

object Main{
    def drawWorld(): Unit = {
        println("Ska rita ut underjorden!")

    }

    def main(args: Array[String]): Unit = {
        drawWorld()

        import blockbattle._

        val kplayer1 = KeyControl(left="a",down="s",right="d",up="w")
        val pplayer1 = Pos(1, 1)
        val player1 = {
            Mole(name="jag", pos=pplayer1, dir=(0,0), color=java.awt.Color.BLUE, keyControl=kplayer1)
        }

        
    }

}
