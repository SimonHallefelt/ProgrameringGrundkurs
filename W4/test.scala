object test {
    object zzz {
        val a = {println("nu!"); 42}
    }
    
    object bugging {val a = b ; val b = 42}

    object funkar {lazy val a = b; val b = 42}
}