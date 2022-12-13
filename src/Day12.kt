
typealias Coord = Pair<Int,Int>

fun main() {

    fun findSList(map: List<List<Char>>): List<Pair<Int, Int>> {
        val start = mutableListOf<Pair<Int,Int>>()
        for(i in map.indices) {
            for (j in 0 until map[i].size) {
                if (map[i][j] == 'S' || map[i][j] == 'a') {
                    start.add(i to j)
                }
            }
        }
        return start
    }

    fun findS(map: List<List<Char>>): Coord {
        val start = mutableListOf<Pair<Int,Int>>()
        for(i in map.indices) {
            for (j in 0 until map[i].size) {
                if (map[i][j] == 'S') {
                    return i to j
                }
            }
        }
        error("e not found")
    }
    
    fun letter(ch: Char): Char {
        return when (ch) {
            'S' -> 'a'
            'E' -> 'z'
            else -> ch
        }
    }
    
    data class Step(val c: Coord, val distance: Int, val parent: Step?)
    
    
    fun findShortest(S: Coord, map: List<List<Char>>): Int {
        val queue = ArrayDeque<Step>()
        val visited = mutableSetOf(S)
        queue.addFirst(Step(S, 0, null))
        while (queue.isNotEmpty()) {
            val v = queue.removeFirst()
            if (map[v.c.first][v.c.second] == 'E') {
                return v.distance
            }
            setOf(
                if (v.c.second < map[v.c.first].size - 1) v.c.first to v.c.second + 1 else null,
                if (v.c.second > 0) v.c.first to v.c.second - 1 else null,
                if (v.c.first > 0) v.c.first - 1 to v.c.second else null,
                if (v.c.first < map.size - 1) v.c.first + 1 to v.c.second else null)
                .filterNotNull()
                .filter {letter(map[it.first][it.second]) - letter(map[v.c.first][v.c.second]) <= 1 }
                .filter { !visited.contains(it) }
                .forEach {
                    visited.add(it)
                    queue.add(Step(it, v.distance + 1, v))
                }
        }
        return Int.MAX_VALUE
    }

    fun part1(input: List<String>): Int {
        val map = input.map{ it.toList() }
        val starts = findS(map)
        return findShortest(starts, map)
    }

    fun part2(input: List<String>): Int {
        val map = input.map{ it.toList() }
        val starts = findSList(map)
        return starts.minOf { findShortest(it, map) }
    }

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
    
}
