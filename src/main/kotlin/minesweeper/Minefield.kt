package minesweeper

import kotlin.math.pow
import kotlin.random.Random

object  Minefield {
    const val SIZE =  9
    val cells = SIZE.toDouble().pow(2).toInt()
    var mines = 0
    var openedCells = 0
    var firstMove = true
    var correctlyMarked = 0
    var playground = MutableList(SIZE) { MutableList(SIZE) { Cell('/') }}

    fun createMinefield(mines: Int) {
        var a = 0
        playground = MutableList(SIZE) { MutableList(SIZE) { Cell('/') }}
        this.mines = mines
        while (a < mines) {
            val randomRowInd = Random.nextInt(SIZE)
            val randomColumnInd = Random.nextInt(SIZE)
            if (playground[randomRowInd][randomColumnInd].contain != 'X') {
                //println("$randomRowInd $randomColumnInd")
                playground[randomRowInd][randomColumnInd].contain = 'X'
                val fromRowInd = if (randomRowInd - 1 >= 0) randomRowInd - 1
                else randomRowInd
                val toRowInd = if (randomRowInd  + 1 < SIZE) randomRowInd + 1
                else randomRowInd
                val fromColumnInd = if (randomColumnInd - 1 >= 0) randomColumnInd - 1
                else randomColumnInd
                val toColumnInd =  if (randomColumnInd  + 1 < SIZE) randomColumnInd + 1
                else randomColumnInd
                for (b in fromRowInd .. toRowInd){
                    for (c in fromColumnInd .. toColumnInd) {
                        if (playground[b][c].contain == '/') {
                            playground[b][c].contain = '1'
                        } else if (playground[b][c].contain.isDigit()) {
                            playground[b][c].contain = (playground[b][c].contain.code + 1).toChar()
                        }
                    }
                }
                a++
            }
        }
    }
    fun printMinefield(playMode: Boolean = true, showMines: Boolean = false) {
        println(" │123456789│\n" +
                "—│—————————│")
        for (a in 0 until SIZE) {
            print("${a + 1}|")
            for (b in 0 until SIZE) {
                if (playMode) {
                    when {
                        playground[a][b].isMarked -> print('*')
                        playground[a][b].isOpened && !playground[a][b].isBomb() -> print(playground[a][b].contain)
                        else -> print(".")
                    }
                } else {
                    when {
                        playground[a][b].isMarked -> print('*')
                        playground[a][b].isBomb() -> print(if (showMines) "X" else "/")
                        else -> print(playground[a][b].contain)
                    }
                }
            }
            println('|')
        }
        println("—│—————————│")
    }
}


