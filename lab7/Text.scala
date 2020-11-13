package nlp

case class Text(source: String){
  lazy val words: Vector[String] = source.toLowerCase.map(c => if (c.isLetter) c else ' ').split("\\s+").toVector  // dela upp source i ord

  lazy val distinct: Vector[String] = words.distinct

  lazy val wordSet: Set[String] = words.toSet

  lazy val wordsOfLength: Map[Int, Set[String]] = wordSet.groupBy(_.length)

  lazy val wordFreq: Map[String, Int] = FreqMapBuilder(words:_*).toMap  // använd FreqMapBuilder

  def ngrams(n: Int): Vector[Vector[String]] = words.sliding(n).toVector  // använd sliding
 
  lazy val bigrams: Vector[(String, String)] = ngrams(2).map(xs => (xs(0), xs(1)))

  lazy val followFreq: Map[String, Map[String, Int]] = {
    val resultat = scala.collection.mutable.Map.empty[String, FreqMapBuilder]
    for((key, next) <- bigrams){
      if(resultat.isDefinedAt(key)) {resultat(key).add(next)}

      else {resultat += ((key, FreqMapBuilder(next)))}

    }
    resultat.map(p => p._1 -> p._2.toMap).toMap
  } //nästlad tabell

  lazy val follows: Map[String, String] = {
    followFreq.map { case (key, followMap) => 
      val maxByFreq: (String, Int) = followMap.maxBy(_._2)
      val mostCommonFollower: String = maxByFreq._1
      (key, mostCommonFollower)
    }
  }
}

object Text {
  def fromFile(fileName: String, encoding: String = "UTF-8"): Text = {
    val source = scala.io.Source.fromFile(fileName, encoding).mkString
    Text(source)
  }
  def fromURL(url: String, encoding: String = "UTF-8"): Text = {
    val source = scala.io.Source.fromURL(url, encoding).mkString
    Text(source)
  }
}
