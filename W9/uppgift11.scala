val url = "https://fileadmin.cs.lth.se/pgk/europa.txt"
val xs = io.Source.fromURL(url, "UTF-8").getLines.toVector
val data = xs.map(_.split(";").toVector)
data.head
data.foreach(println)

//a
val populationOf: Map[String, Int]    = ???
val sizeOf:       Map[String, Int]    = ???
val capitalOf:    Map[String, String] = ???