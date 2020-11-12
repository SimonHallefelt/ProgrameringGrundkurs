//a
val follow = for(i <- 2 to 16 by 2) yield (i, i+1)
val xs = follow.toMap
val ys = xs.toVector

svar: nej för Map har ingen ordning

