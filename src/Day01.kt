fun main() {

    fun part1(input: List<List<String>>): Int {
        return input.map { e -> e.sumOf { it.toInt() } }.max()
    }

    fun part2(input: List<List<String>>): Int {
        return input.map { e -> e.sumOf { it.toInt() } }.sortedDescending().take(3).sum()
    }

    val input = readInput("Day01").chunkBy { it.isEmpty() }
    println(part1(input))
    println(part2(input))
}
