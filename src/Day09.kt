import kotlin.math.abs

fun main() {

    fun moveTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int>? {
        if (head.first == tail.first) {
            if (abs((head.second) - (tail.second)) > 1) {
                return if (head.second < tail.second) {
                    tail.first to tail.second - 1
                } else {
                    tail.first to tail.second + 1
                }
            }
        }
        if (head.second == tail.second) {
            if (abs((head.first) - (tail.first)) > 1) {
                return if (head.first > tail.first) {
                    tail.first + 1 to tail.second
                } else {
                    tail.first - 1 to tail.second
                }
            }
        }
        if (abs((head.first) - (tail.first)) > 1 || abs((head.second) - (tail.second)) > 1) {
            return if (head.first > tail.first) {
                tail.first + 1 to if (head.second > tail.second) tail.second + 1 else tail.second - 1
            } else {
                tail.first - 1 to if (head.second > tail.second) tail.second  + 1 else tail.second - 1
            }
        }
        return null
    }
    
    fun part1(input: List<String>): Int {
        val tail = mutableListOf<Pair<Int, Int>>(0 to 0)         
        var head = 0 to 0
        input.forEach {
            val move = it.split(" ")
            repeat(move[1].toInt()) {
                if (move[0] == "R") {
                    head = head.first to head.second + 1
                }
                if (move[0] == "D") {
                    head = head.first + 1 to head.second
                }
                if (move[0] == "L") {
                    head = head.first to head.second - 1
                }
                if (move[0] == "U") {
                    head = head.first - 1 to head.second
                }
                moveTail(head, tail.last())?.let { t -> tail.add(t) }
            }
        }
        return tail.distinct().size
    }

    fun part2(input: List<String>): Int {
        val tail = mutableListOf(0 to 0)
        val knots = mutableListOf<Pair<Int, Int>>().also { k -> repeat(9) { k.add(0 to 0)} }
        input.forEach {
            val move = it.split(" ")
            repeat(move[1].toInt()) {
                if (move[0] == "R") {
                    knots[0] = knots[0].first to knots[0].second + 1
                }
                if (move[0] == "D") {
                    knots[0] = knots[0].first + 1 to knots[0].second
                }
                if (move[0] == "L") {
                    knots[0] = knots[0].first to knots[0].second - 1
                }
                if (move[0] == "U") {
                        knots[0] = knots[0].first - 1 to knots[0].second
                }
                for (j in 0 until 8) {
                    moveTail(knots[j], knots[j + 1])?.let { t -> knots[j + 1] = t }
                }
                moveTail(knots[8], tail.last())?.let { t -> tail.add(t) }
            }
        }
        return tail.distinct().size
    }

    //val input = readInput("Day09_part2")
    //check(part2(input) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
