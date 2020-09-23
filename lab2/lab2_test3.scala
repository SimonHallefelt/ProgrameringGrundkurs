import scala.io.StdIn.readLine


object lab2{
    def main(args: Array[String]): Unit= {
        println("välkommen till mattespelet")
    }

var b = 3
var a = b

    def game{
        println("1 + 2")
        if(a == readLine.toInt){println("you win")}
        else{println("you fail"); game}

    }
game

}
