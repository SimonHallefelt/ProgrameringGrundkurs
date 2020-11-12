//a
def lookupIndex(xs: Vector[(String, String)])(key: String): Int = {
    xs.indexWhere(key == _._1)
}
//b
lookupIndex(huvudstad)("Skåne")