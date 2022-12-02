const val ROCK = "A"
const val PAPER = "B"
const val SCISSORS = "C"

const val LOSE = "X"
const val DRAW = "Y"
const val WIN = "Z"

fun main() {

    fun outcome(a: String, b: String): Int {
        return when (a+b) {
            "AA", "BB", "CC" -> 3
            "AB", "BC", "CA" -> 6
            "AC", "BA", "CB" -> 0
            else -> error("Check Inputs")
        }
    }

    fun points(a: String): Int {
        return when(a) {
            ROCK -> 1
            PAPER -> 2
            SCISSORS -> 3
            else -> error("Check Inputs")
        }
    }

    fun round(a: String, b: String): Int {
        val b1 = when (b) {
            "X" -> ROCK
            "Y" -> PAPER
            "Z" -> SCISSORS
            else -> error("Check Inputs")
        }
        return outcome(a, b1) + points(b1)
    }

    fun round2(a: String, b: String): Int {
        val b1 = when(b) {
            LOSE -> when (a) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                else -> PAPER
            }
            WIN -> when(a) {
                ROCK -> PAPER
                PAPER -> SCISSORS
                else -> ROCK
            }
            DRAW -> a
            else -> error("Check Inputs")
        }
        return outcome(a, b1) + points(b1)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val (a, b) = it.split(" ")
            round(a, b)
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val (a, b) = it.split(" ")
            round2(a, b)
        }
    }

    //val input = readInput("Day02_test")
    //check(part2(input) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
