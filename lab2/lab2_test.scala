import scala.io.StdIn.readLine


object lab2{
    def main(args: Array[String]): Unit= {
        println("välkommen till mattespelet")
    }

    def game{
        println("1 + 2")
        if("3" == readLine){println("you win")}
        else{println("you fail")}

    }
game

}
