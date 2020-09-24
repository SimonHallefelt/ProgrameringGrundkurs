object Underjorden{
    var x = 0
    var y = 1

    object Mullvaden{
        var x = Underjorden.x + 10
        var y = Underjorden.y + 9
    }

    object Masken{
        private var x = Mullvaden.x
        var y = Mullvaden.y + 190
        def ärMullvadsmat: Boolean = x == Mullvaden.x && y == Mullvaden.y
    }
}