package minesweeper

import kotlin.random.Random

class Minefield(val rows: Int, var mines: Int) {
    val columns: Int = rows
    val playground =  MutableList(rows) { MutableList(columns) { '.' } }
    init {
        var a = 0
       while (a < mines) {
            val randomRowInd = Random.nextInt(rows)
            val randomColumnInd = Random.nextInt(columns)
            if (playground[randomRowInd] [randomColumnInd] != 'X') {
                playground[randomRowInd] [randomColumnInd] = 'X'
                val fromRowInd =  if (randomRowInd - 1 > 0) {randomRowInd - 1}
                else { randomRowInd}
                val toRowInd =  if (randomRowInd  + 1 < rows) {randomRowInd + 1}
                else {randomRowInd}
                val fromColumnInd =  if (randomColumnInd - 1 > 0) {randomColumnInd - 1}
                else { randomColumnInd}
                val toColumnInd =  if (randomColumnInd  + 1 < columns) {randomColumnInd + 1}
                else {randomColumnInd}
                for (b in fromRowInd .. toRowInd)
                    for (c in fromColumnInd .. toColumnInd){
                        if (playground[b][c] == '.') {
                            playground[b][c] = '1'
                        } else if (playground[b][c].isDigit()) {
                            playground[b][c] = (playground[b][c].code + 1).toChar()
                        }
                    }
                a++;
                }
        }
    }
    fun printMinefield() {
        for (a in 0 until rows) {
            for (b in 0 until columns) {
                print(playground[a][b] + " ")
            }
            println()
        }
    }
}

