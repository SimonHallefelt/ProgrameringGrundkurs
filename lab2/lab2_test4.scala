import scala.io.StdIn.readLine


object lab2{
    def main(args: Array[String]): Unit= {
        println("välkommen till mattespelet")
    }

var hälsa = 3

    def game{
        var a = (math.random()*100).toInt
        var b = (math.random()*100+a).toInt
        var d  = a + b
        def gissa{
            println("Du har " + hälsa + " liv kvar!")
            println(a + " + " + b)
            if(d == readLine.toInt){println("you win")}
            else{println("you fail"); hälsa = hälsa - 1; gissa;}

        }
        gissa
    }
game

}
