fun main() {

    fun findMarker(input: String, size: Int): Int {
        val wind = input.windowed(size)
        var count = size - 1
        wind.find {
            count++
            it.toList().distinct().size == size
        }
        return count    
    }
    
    fun part1(input: List<String>): Int {
        return findMarker(input[0], 4)
    }

    fun part2(input: List<String>): Int {
        return findMarker(input[0], 14)
    }

//    val input = readInput("Day06_test")
//    check(part2(input) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
