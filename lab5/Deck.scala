package cards

class Deck private (val initCards: Vector[Card]){
  private var cards: Array[Card] = initCards.toArray

  def reset(): Unit = cards = initCards.toArray
  def apply(i: Int): Card = cards(i)
  def toVector: Vector[Card] = cards.toVector
  override def toString: String = cards.mkString(" ")

  def peek(n: Int): Vector[Card] = cards.take(n).toVector

  def remove(n: Int): Vector[Card] = {
    val init = peek(n)
    cards = cards.drop(n)
    init
  }

  def prepend(moreCards: Card*): Unit = cards = moreCards.toArray ++ cards

  /** Swaps cards at position a and b. */
  def swap(a: Int, b: Int): Unit = {
    val c = cards(a)
    cards(a) = cards(b)
    cards(b) = c
  }

  /** Randomly reorders the cards in this deck. */
  def shuffle(): Unit = {
    var n = cards.length
    val range = 0 to n-1
    for(i <- range.reverse){
      val r = scala.util.Random.nextInt(i+1)
      swap(i, r)
    }

  }
}
object Deck {
  def empty: Deck = new Deck(Vector())
  def apply(cards: Seq[Card]): Deck = new Deck(cards.toVector)

  /** Creates a new full Deck with 52 cards in rank and suit order. */
  def full(): Deck = {
    var cards = Vector[Card]()
    for(s <- Card.suitRange){
      for(r <- Card.rankRange){
        cards = cards :+ Card(r, s)
      }
    }
    val nyDeck = new Deck(cards)
    nyDeck
  }
}