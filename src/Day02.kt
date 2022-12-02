fun main() {

    val ROCK = "A"
    val PAPER = "B"
    val SCISSORS = "C"


    fun outcome(a: String, b: String): Int {
        return when (a+b) {
            "AA" -> 3
            "AB" -> 6
            "AC" -> 0
            "BA" -> 0
            "BB" -> 3
            "BC" -> 6
            "CA" -> 6
            "CB" -> 0
            else -> 3
        }
    }

    fun points(a: String): Int {
        return when(a) {
            ROCK -> 1
            PAPER -> 2
            else -> 3
        }
    }

    fun round(a: String, b: String): Int {
        val b1 = when (b) {
            "X" -> ROCK
            "Y" -> PAPER
            else -> SCISSORS
        }
        return outcome(a, b1) + points(b1)
    }

    fun round2(a: String, b: String): Int {
        val b1 = when(b) {
            "X" -> when (a) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                else -> PAPER
            }
            "Z" -> when(a) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                else -> ROCK
            }
            else -> a
        }
        return outcome(a, b1) + points(b1)
    }

    fun part1(input: List<String>): Int {
        println(input)
        val sum = input.sumOf { round(it.split(" ")[0], it.split(" ")[1]) }
        println(sum)
        return sum
    }

    fun part2(input: List<String>): Int {
        val sum = input.sumOf {  round2(it.split(" ")[0], it.split(" ")[1]) }
        println(sum)
        return sum
    }

    //val input = readInput("Day02_test")
    //check(part2(input) == 12)

    val input = readInput("Day02")
    //println(part1(input))
    println(part2(input))
}
