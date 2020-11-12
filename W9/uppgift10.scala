//a
val xs = Vector("fem", "gurka", "är", "fler", "än", "fyra", "tomater")
xs.sliding(2).toVector
//scala.collection.immutable.Vector[scala.collection.immutable.Vector[String]] = Vector(Vector(fem, gurka), Vector(gurka, är), Vector(är, fler), Vector(fler, än), Vector(än, fyra), Vector(fyra, tomater))
xs.sliding(3).toVector
//scala.collection.immutable.Vector[scala.collection.immutable.Vector[String]] = Vector(Vector(fem, gurka, är), Vector(gurka, är, fler), Vector(är, fler, än), Vector(fler, än, fyra), Vector(än, fyra, tomater))
xs.sliding(10).toVector
//scala.collection.immutable.Vector[scala.collection.immutable.Vector[String]] = Vector(Vector(fem, gurka, är, fler, än, fyra, tomater))


//b
xs.sliding(2).map(p => p(0) -> p(1)).toMap