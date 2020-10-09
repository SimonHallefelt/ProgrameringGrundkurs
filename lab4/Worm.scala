package blockbattle

case class Worm(
    var pos: Pos = Pos(0, 0),
    var gammalPos: Pos = Pos(0, 0)
){

    def randomPos: Pos = {
        val posx = (math.random() * 30).toInt
        val posy = ((math.random() * (50 - 8)).toInt + 8)
        pos = Pos(posx, posy)
        Pos(posx, posy)

    }
    def randomMove: Unit = {
        gammalPos = Pos(pos.x, pos.y)
        if (math.random() < 0.02) {
            randomPos
        }
    }
}