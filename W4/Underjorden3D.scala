object Underjorden3D {
    private  val hemlis = ("uppgången till överjorden", (0, 0, 0.0))

    object Mullvaden {
        var pos = (5,3,math.random() * 10 + 1)
        def djup: Double = pos._3
    }

    object Masken {
        private var pos = (0,0, 10.0)
        def ärMullvadsmat: Boolean = pos == Mullvaden.pos
        def ärRaktUnderUppgången: Boolean = hemlis._2._1 == pos._1 && hemlis._2._2 == pos._2
    }
}