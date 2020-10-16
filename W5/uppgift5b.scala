def deepCopy(xs: Array[Mutant]): Array[Mutant] = {
    val result = Array.ofDim[Mutant](xs.length)
    var i = 0
    while (i < xs.length) {
        result(i) = new Mutant(xs(i).int)
        i += 1
    }
    result
}