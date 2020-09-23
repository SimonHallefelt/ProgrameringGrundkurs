import scala.io.StdIn.readLine


object lab2{
    def main(args: Array[String]): Unit= {
        println("välkommen till mattespelet")
        var liv = 3
        var poäng = 0

        game
    }

    def game{
        var a = (math.random()*100).toInt
        var b = (math.random()*100).toInt
        var c = math.random()
        var svar = if(c < 0.5){b + a + 1 + poäng}; else if(0.5 <= c){b - a - 1 - poäng}

        gissa

        def gissa() {
            if(liv < 1){
                println("Du förlorade, korekt svar va: " + svar); System.exit(1)
            }
            println("poäng " + poäng + "/5  liv " + liv)
            if(c < 0.5){
                println(b + " + " + a)
            }
            else if(0.5 <= c){
                println(b + " - " + a)
            }
            if(svar == readLine.toInt){
                println("corekt"); poäng = poäng + 1
                if(poäng == 5){
                    println("du van!")
                }
                else{
                    game
                }
            }
            else{
                println("you fail"); liv = liv - 1; gissa;
            }

        }
        
    }

    var x = readLine()

    try {
        x.toInt
    } catch NumberFormatException {

    }

}
