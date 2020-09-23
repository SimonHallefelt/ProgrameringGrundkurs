import scala.io.StdIn.readLine


object lab2{
    def main(args: Array[String]): Unit= {
    }

    println("välkommen till mattespelet")
    val fusk = "Vem bryr sig!"
    var liv = 3
    var poäng = 0

    game

    def game{
        var a = (math.random()*100).toInt
        var b = (math.random()*100).toInt
        var c = math.random()
        var svar = if(c < 0.5){b + a + 1 + poäng}; else if(0.5 <= c){b - a - 1 - poäng}

        gissa

        def gissa{
            if(liv < 1){println("Du förlorade, korrekt svar va: " + svar); System.exit(1)}
            println("poäng " + poäng + "/5  liv " + liv)
            if(c < 0.5){println(b + " + " + a)}; else if(0.5 <= c){println(b + " - " + a)}
            var x = readLine
            if(fusk == x){println("du vann!"); System.exit(1)}
            else if(svar == x.toInt){println("korrekt"); poäng += 1; if(poäng == 5){println("du vann!")}; else{game}}
            else{println("fel"); liv -= 1; gissa;}

        }
        
    }

}
