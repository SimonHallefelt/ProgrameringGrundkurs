package blockbattle

import scala.annotation.varargs

case class Mole(
    val name: String,
    var pos: Pos,
    var dir: (Int, Int),
    val color: java.awt.Color,
    val keyControl: KeyControl
){
    var points = 0

    override def toString = s"Mole[name=$name, pos=$pos, dir=$dir, points=$points]"

    def setDir(key: String): Unit = {

        if (keyControl.has(key) == true){dir = keyControl.direction(key)}
    }

    def reverseDir(): Unit = {
        var dirx = dir._1 * -1
        var diry = dir._2 * -1
        dir = (dirx, diry)
    }
    def move(): Unit = {
        pos = pos.moved(dir)

    }

    def nextPos: Pos = {
        var posx = pos.x + dir._1
        var posy = pos.y + dir._2
        Pos(posx, posy)
    }
}