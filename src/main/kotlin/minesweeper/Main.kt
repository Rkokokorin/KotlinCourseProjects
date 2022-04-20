package minesweeper

fun main() {
    println("How many mines do you want on the field?")
    val n = readln().toInt()
    val rows = 9
    val minefield = Minefield(rows, n)
    minefield.printMinefield()
}
