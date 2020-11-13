package cards

object PokerProbability {

  /**
   * For a given number of iterations, shuffles a deck, draws a hand and
   * returns a vector with the frequency of each hand category.
   */
  def register(n: Int, deck: Deck): Vector[Int] = {
    var frequencies = Array.fill(10)(0)
    for(i <- 1 to n){
      deck.shuffle()
      frequencies(Hand.from(deck).category) += 1
    }
    frequencies.toVector
  }

  def main(args: Array[String]): Unit = {
    val n = scala.io.StdIn.readLine("number of iterations: ").toInt
    val deck = Deck.full()
    val frequencies = register(n, deck)
    for (i <- Hand.Category.values) {
        val name = Hand.Category.Name.english(i).capitalize
        val percentages = frequencies(i).toDouble / n * 100
        println(f"$name%16s $percentages%10.6f%%")
    }
  }
}
