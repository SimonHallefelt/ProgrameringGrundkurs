package blockbattle

case class KeyControl(left: String, right: String, up: String, down: String){

    def direction(key: String): (Int, Int) = {
        if (key == left){(-1, 0);}
        else if (key == right){(1, 0);}
        else if (key == up){(0, -1);}
        else if (key == down){(0, 1);}
        else (0, 0)

    }

    def has (key: String): Boolean = {
        key == left || key == right || key == up || key == down
    }
}
