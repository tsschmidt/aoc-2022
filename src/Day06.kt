fun main() {

    fun findMarker0(input: String, size: Int): Int {
        var index = 0
        val chars = input.toList()
        while(chars.subList(index, index + size).distinct().size < size) {
            index++
        }
        return index + size
    }
    
    fun findMarker(input: String, size: Int): Int {
        var count = size - 1
        input.windowed(size).find {
            count++
            it.toList().distinct().size == size
        }
        return count    
    }
    
    fun part1(input: List<String>): Int {
        return findMarker0(input[0], 4)
    }

    fun part2(input: List<String>): Int {
        return findMarker0(input[0], 14)
    }

//    val input = readInput("Day06_test")
//    check(part2(input) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}