package minesweeper

class Cell(var contain: Char) {
    var isMarked = false
    var isOpened = false
    fun invertMark() {
        isMarked = !isMarked
    }
    fun open() {
        isOpened = true
        Minefield.openedCells++
    }
    fun  isBomb(): Boolean {
        return this.contain == 'X'
    }
}