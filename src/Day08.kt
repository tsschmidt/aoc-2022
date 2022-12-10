fun main() {

    fun parseMap(input: List<String>): List<List<Int>> {
        val map = mutableListOf<List<Int>>()
        input.forEach {row -> 
            map.add(row.map { it.toString().toInt() })
        }
        return map
    }
    
    fun checkRight(r: Int, c: Int, cur: Int, map: List<List<Int>>): Boolean {
        if (c == map[0].size) {
            return true
        }
        if (map[r][c] < cur) {
            return checkRight(r, c+1, cur, map)
        }
        return false
    }

    fun checkLeft(r: Int, c: Int, cur: Int, map: List<List<Int>>): Boolean {
        if (c == -1) {
            return true
        }
        if (map[r][c] < cur) {
            return checkLeft(r, c-1, cur, map)
        }
        return false
    }

    fun checkDown(r: Int, c: Int, cur: Int, map: List<List<Int>>): Boolean {
        if (r == map.size) {
            return true
        }
        if (map[r][c] < cur) {
            return checkDown(r+1, c, cur, map)
        }
        return false
    }
    
    fun checkUp(r: Int, c: Int, cur: Int, map: List<List<Int>>): Boolean {
        if (r == -1) {
            return true
        }
        if (map[r][c] < cur) {
            return checkUp(r-1, c, cur, map)
        }
        return false
    }

    fun viewRight(r: Int, c: Int, cur: Int, map: List<List<Int>>, score: Int): Int {
        if (c < map[0].size - 1  && map[r][c] < cur) {
            return viewRight(r, c+1, cur, map, score + 1)
        }
        return score 
    }
    
    fun viewLeft(r: Int, c: Int, cur: Int, map: List<List<Int>>, score: Int): Int {
        if (c > 0 && map[r][c] < cur) {
            return viewLeft(r, c-1, cur, map, score + 1)
        }
        return score
    }

    fun viewDown(r: Int, c: Int, cur: Int, map: List<List<Int>>, score: Int): Int {
        if (r < map.size - 1 && map[r][c] < cur) {
            return viewDown(r+1, c, cur, map, score + 1)
        }
        return score
    }

    fun viewUp(r: Int, c: Int, cur: Int, map: List<List<Int>>, score: Int): Int {
        if (r > 0 && map[r][c] < cur) {
            return viewUp(r-1, c, cur, map, score + 1)
        }
        return score
    } 
    
    fun part1(input: List<String>): Int {
        val map = parseMap(input)
        val width = map[0].size
        val height = map.size
        var count = 0
        for (r in 1..width - 2) {
            for (c in 1..height -2) {
                if (checkRight(r, c+1, map[r][c], map) ||
                    checkLeft(r, c-1, map[r][c], map) ||
                    checkDown(r+1, c, map[r][c], map) ||
                    checkUp(r-1, c, map[r][c], map)) {
                    count++
                }
            }
        }
        count += (2 * map.size) + (2 * (map[0].size - 2))
        return count
    }

    fun part2(input: List<String>): Int {
        val map = parseMap(input)
        val width = map[0].size
        val height = map.size
        var max = 0
        for (r in 1..height - 2) {
            for (c in 1..width - 2) {
                val cur = viewDown(r + 1, c, map[r][c], map, 1) *
                    viewUp(r - 1, c, map[r][c], map, 1) *
                    viewRight(r, c + 1, map[r][c], map, 1) * 
                    viewLeft(r, c - 1, map[r][c], map, 1)
                if (cur > max) {
                    max = cur
                }
            }
        }
        return max
    }

    //val input = readInput("Day08_test")
    //check(part2(input) == 8)

    val input = readInput("Day08")
    println(part1(input)) 
    println(part2(input))
}
