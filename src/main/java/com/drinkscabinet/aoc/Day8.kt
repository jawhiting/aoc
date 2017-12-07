package com.drinkscabinet.aoc

import java.io.BufferedReader
import java.io.StringReader
import java.util.stream.Collector
import java.util.stream.Collectors

class Day8 {

    private val grid = Array(6) { BooleanArray(50) }

    fun execute(instructions: String) {
        val reader = BufferedReader(StringReader(instructions))

        println(printGrid(grid))

        for (line in reader.lines()) {
            if (line.startsWith("rect")) {
                val tokens = line.substring(5).split("x".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val x = Integer.valueOf(tokens[0])!!
                val y = Integer.valueOf(tokens[1])!!
                for (r in 0..y - 1) {
                    for (c in 0..x - 1) {
                        grid[r][c] = true
                    }
                }
            } else if (line.startsWith("rotate column")) {
                val tokens = line.substring(16).split(" by ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val column = Integer.valueOf(tokens[0])!!
                val count = Integer.valueOf(tokens[1])!!
                for (i in 0..count - 1) {
                    rotateColumn(column!!)
                }

            } else if (line.startsWith("rotate row")) {
                val tokens = line.substring(13).split(" by ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val row = Integer.valueOf(tokens[0])!!
                val count = Integer.valueOf(tokens[1])!!
                for (i in 0..count - 1) {
                    rotateRow(row!!)
                }
            }
            println(printGrid(grid))
        }
    }

    private fun rotateColumn(column: Int) {
        val last = grid[grid.size - 1][column]
        for (r in grid.size - 1 downTo 1) {
            grid[r][column] = grid[r - 1][column]
        }
        grid[0][column] = last
    }

    private fun rotateRow(row: Int) {
        val last = grid[row][grid[row].size - 1]
        for (c in grid[row].size - 1 downTo 1) {
            grid[row][c] = grid[row][c - 1]
        }
        grid[row][0] = last
    }

    private fun printGrid(grid: Array<BooleanArray>): String {
        val buffer = StringBuffer()
        for (r in grid.indices) {
            for (c in 0..grid[r].size - 1) {
                buffer.append(if (grid[r][c]) '@' else '.')
            }
            buffer.append(System.lineSeparator())
        }
        return buffer.toString()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Day8().execute(inst)
        }

        private val inst = "rect 1x1\n" +
                "rotate row y=0 by 4\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 7\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 10\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 5\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 4\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 2\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 4\n" +
                "rect 1x1\n" +
                "rotate row y=0 by 4\n" +
                "rect 1x2\n" +
                "rotate row y=0 by 29\n" +
                "rotate column x=17 by 2\n" +
                "rotate column x=10 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 20x1\n" +
                "rotate row y=2 by 4\n" +
                "rotate row y=1 by 5\n" +
                "rotate row y=0 by 3\n" +
                "rect 1x4\n" +
                "rotate column x=32 by 1\n" +
                "rotate row y=3 by 30\n" +
                "rotate row y=1 by 18\n" +
                "rotate row y=0 by 14\n" +
                "rotate column x=10 by 3\n" +
                "rotate column x=6 by 3\n" +
                "rotate column x=4 by 1\n" +
                "rotate column x=0 by 1\n" +
                "rect 12x1\n" +
                "rotate row y=2 by 29\n" +
                "rotate column x=5 by 2\n" +
                "rotate row y=2 by 21\n" +
                "rotate column x=15 by 3\n" +
                "rotate column x=2 by 2\n" +
                "rotate row y=3 by 20\n" +
                "rotate row y=2 by 29\n" +
                "rotate row y=0 by 47\n" +
                "rotate column x=11 by 3\n" +
                "rotate column x=8 by 3\n" +
                "rotate column x=7 by 1\n" +
                "rotate column x=6 by 2\n" +
                "rotate column x=5 by 1\n" +
                "rotate column x=4 by 2\n" +
                "rotate column x=3 by 1\n" +
                "rotate column x=1 by 3\n" +
                "rotate column x=0 by 1\n" +
                "rect 13x1\n" +
                "rotate column x=10 by 2\n" +
                "rotate column x=9 by 2\n" +
                "rotate row y=0 by 3\n" +
                "rotate column x=27 by 1\n" +
                "rotate row y=4 by 9\n" +
                "rotate row y=3 by 1\n" +
                "rotate row y=2 by 1\n" +
                "rotate row y=1 by 2\n" +
                "rotate row y=0 by 1\n" +
                "rect 1x5\n" +
                "rotate column x=33 by 1\n" +
                "rotate column x=30 by 4\n" +
                "rotate column x=24 by 1\n" +
                "rotate column x=23 by 1\n" +
                "rotate column x=18 by 1\n" +
                "rotate column x=8 by 1\n" +
                "rotate row y=4 by 23\n" +
                "rotate row y=3 by 1\n" +
                "rotate row y=2 by 1\n" +
                "rotate row y=1 by 3\n" +
                "rotate row y=0 by 1\n" +
                "rect 1x6\n" +
                "rotate column x=40 by 2\n" +
                "rotate column x=30 by 2\n" +
                "rotate column x=17 by 2\n" +
                "rotate column x=16 by 2\n" +
                "rotate row y=5 by 37\n" +
                "rotate row y=4 by 5\n" +
                "rotate row y=3 by 5\n" +
                "rotate row y=2 by 25\n" +
                "rotate row y=1 by 5\n" +
                "rotate row y=0 by 5\n" +
                "rotate column x=41 by 4\n" +
                "rotate column x=38 by 5\n" +
                "rotate column x=36 by 5\n" +
                "rotate column x=33 by 1\n" +
                "rotate column x=32 by 5\n" +
                "rotate column x=31 by 1\n" +
                "rotate column x=28 by 1\n" +
                "rotate column x=27 by 3\n" +
                "rotate column x=26 by 4\n" +
                "rotate column x=25 by 5\n" +
                "rotate column x=18 by 1\n" +
                "rotate column x=17 by 5\n" +
                "rotate column x=16 by 5\n" +
                "rotate column x=15 by 1\n" +
                "rotate column x=13 by 3\n" +
                "rotate column x=12 by 2\n" +
                "rotate column x=11 by 2\n" +
                "rotate column x=10 by 3\n" +
                "rotate column x=8 by 1\n" +
                "rotate column x=5 by 1"
    }
}
