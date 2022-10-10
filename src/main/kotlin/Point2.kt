class Point2 (val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Point2) return false
        return other.x == x && other.y == y
    }
}