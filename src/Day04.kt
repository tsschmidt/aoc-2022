fun main() {

    fun parse(input: List<String>): List<Pair<IntRange, IntRange>> {
       return  input.map {
            val (a, b) = it.split(",")
            val rangeA = IntRange(a.split("-")[0].toInt(), a.split("-")[1].toInt())
            val rangeB = IntRange(b.split("-")[0].toInt(), b.split("-")[1].toInt())
            rangeA to rangeB
        }
    }

    fun part1(input: List<String>): Int {
        return parse(input).count { it.first.toList().containsAll(it.second.toList()) || it.second.toList().containsAll(it.first.toList()) }
    }

    fun part2(input: List<String>): Int {
        return parse(input).count { it.first.intersect(it.second).size > 0 }
    }

    //val input = readInput("Day04_test")
    //check(part1(input) == 2)

    val input = readInput("Day04")
   //  println(part1(input))
    println(part2(input))
}
