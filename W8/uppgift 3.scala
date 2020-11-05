def throwDie: Int = scala.util.Random.nextInt(6) + 1



val ds1 = for(i <- 1 to 1000) yield for(j <- 1 to 5) yield throwDie



val ds2 = (1 to 1000).map(i => (1 to 5).map(j => throwDie))
val ds3 = (1 to 1000).map(i => Vector.fill(5)(throwDie))
val ds4 = for (i <- 1 to 1000) yield Vector.fill(5)(throwDie)
val ds5 = Vector.fill(1000)(Vector.fill(5)(throwDie))
val ds6 = Vector.fill(1000, 5)(throwDie)



def roll(n: Int): Vector[Int] = Vector.fill(n)(throwDie).sorted



def isYatzy(xs: Vector[Int]): Boolean = xs.forall(_ == xs(0))



def diceMatrix(m: Int, n: Int): Vector[Vector[Int]] = Vector.fill(m)(roll(n))



def diceMatrixToString(xss: Vector[Vector[Int]]): String = {
    xss.map(_.mkString(" ")).mkString("\n")
} 



def filterYatzy(xss: Vector[Vector[Int]]): Vector[Vector[Int]] = {
    xss.filter(isYatzy)
}



def yatzyPips(xss: Vector[Vector[Int]]): Vector[Int] = filterYatzy(xss).map(_.head)