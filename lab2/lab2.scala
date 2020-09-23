inport scala.io.StdIn.readLine()


object lab2{
    def main(args: Array[String]): Unit= {
        println(välkommen till mattespelet)
    }

    def game{
        var skada = 0
        def liv{
            var hälsa = 3 - skada
            println("du har " + hälsa + "liv kvar!")
        }
        def matte{
            def svar{
                def a = (math.random()*100).toInt
                def b = (math.random()*100+a).int
                def c = math.random()
                def beraknarSvar{
                    if (c < 0.25){d = b + a; println(b " + " a)}
                    else if (0.25 =< c < 0.5){d = b - a; println(b " - " a}
                    else if (0.5 =< c < 0.75){d = b * a; println(b " * " a}
                    else {d = b / a; println(b " / " a}
                }

            }
            readLine()
        }

    }
}
