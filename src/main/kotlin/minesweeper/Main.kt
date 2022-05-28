package minesweeper

import kotlin.math.pow
import kotlin.system.exitProcess


fun main() {
    println("How many mines do you want on the field?")
    val n = readln().toInt()
    Minefield.createMinefield(n)
    if (n > Minefield.cells)
        exitProcess(1)
    Minefield.printMinefield()
    gameProcess()
}
//Сделать регенирацию поля при первом попадании

fun gameProcess() {
    val freeCom = "free"
    val mineCom = "mine"
    var x: Int
    var y: Int
//        if (Minefield.playground[y][x].contain.isDigit()) {
//            println("There is a number here!")
//            continue;
//            }
//        else
    while (true) {
        try {
            print("Set/delete mines marks (x and y coordinates):")
            val (xS, yS, command) = readln().trim().split(' ')
            x = xS.toInt() - 1
            y = yS.toInt() - 1
            when (command) {
                freeCom -> proceedFreeCommand(x,y)
                mineCom -> proceedMarkCommand(x,y)
                else -> wrongInput()
            }
        }
        catch (e: java.lang.Exception){
            wrongInput()
            e.printStackTrace()
            continue
        }
        Minefield.printMinefield()
        //println(Minefield.openedCells)
        if (Minefield.correctlyMarked == Minefield.mines) {
            println("Congratulations! You found all the mines!")
            return
        }
        if (Minefield.cells - Minefield.mines == Minefield.openedCells) {
            println("Congratulations! You found all the mines!")
            return
        }
    }
}
//Закончил на том что добавил счетчик открытых мин
fun proceedFreeCommand(x: Int, y: Int) {
    if (Minefield.firstMove) {
        val savedMarks = MutableList(0){-1}
        if (Minefield.playground[y][x].isBomb()) {
            for (y1 in 0.. 8)
                for (x1 in 0..8)
                    if (Minefield.playground[y1][x1].isMarked) {
                        savedMarks.add(savedMarks.size, y1);
                        savedMarks.add(savedMarks.size, x1)
                    }
            Minefield.createMinefield(Minefield.mines)
            for (i: Int in 0 until savedMarks.size step 2)
                Minefield.playground[savedMarks[i]][savedMarks[i + 1]].invertMark()
            proceedFreeCommand(x, y)
        } else {
            Minefield.firstMove = false
        }
    }
    if (Minefield.playground[y][x].isBomb()) {
        println("You stepped on a mine and failed!")
        exitProcess(1)
    } else {
        openCell(x,y)
    }
}
fun proceedMarkCommand(x: Int, y: Int) {
    if (Minefield.playground[y][x].isBomb())
        if (Minefield.playground[y][x].isMarked) Minefield.correctlyMarked-- else Minefield.correctlyMarked++
    Minefield.playground[y][x].invertMark()
}
fun wrongInput() {
    println("Такой команды нет")
}
fun openCell(x: Int, y: Int) {
    val openedBefore = Minefield.playground[y][x].isOpened

    if (!openedBefore) {
        Minefield.playground[y][x].open()
        if (Minefield.playground[y][x].isMarked)
            Minefield.playground[y][x].invertMark()
    }
    if (Minefield.playground[y][x].contain == '/' && !openedBefore) {
        for (i in -1..1) {
            val yN = y + i
            if (yN < 0 || yN > 8)
                continue
            for (u in -1..1) {
                if (u == 0 && i == 0) continue
                val xN = x + u
                if (xN < 0 || xN > 8)
                    continue
                else
                    openCell(xN, yN)
            }
        }
    }
}