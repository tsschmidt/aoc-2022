
fun main() {

    fun priority(item: Char): Int = if (item.isUpperCase()) item.code - 38 else item.code - 96

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val compartments = it.chunked(it.length / 2)
            val common = compartments[0].toSet().intersect(compartments[1].toSet())
            priority(common.single())
        }
    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        return groups.sumOf {
            val badge = it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet())
            priority(badge.single())
        }
    }

    //val input = readInput("Day03_test")
    //check(part2(input) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
