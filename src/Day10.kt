fun main() {

    
    fun part1(input: List<String>): Int {
        var x = 1
        var cycles = 0
        val points = setOf(20, 60, 100, 140, 180, 220)
        val ranges = mutableListOf<Int>()
        input.forEach {
            cycles++
            if (cycles in points) {
                ranges.add(cycles * x)
            }
            if (it.startsWith("addx")) {
                cycles++
                if (cycles in points) {
                    ranges.add(cycles * x)
                }
                x += it.split(" ")[1].toInt()
            }
        }
        return ranges.sum()
    }

    fun part2(input: List<String>) {
        var x = 1
        var cycles = 0
        val crt = mutableListOf<Int>()
        input.forEach {
            cycles++
            val rowPos = (cycles % 40) - 1
            if (rowPos in setOf(x -1, x, x+1)) {
                crt.add(1)
            } else {
                crt.add(0)
            }
            if (it.startsWith("addx")) {
                cycles++
                val rowPos = (cycles % 40) - 1
                if (rowPos in setOf(x -1, x, x+1)) {
                    crt.add(1)
                } else {
                    crt.add(0)
                }
                x += it.split(" ")[1].toInt()
            }
        }
        crt.chunked(40).forEach {
            it.forEach {
                if (it == 0) {
                    print(" . ")
                } else {
                    print(" # ")
                }
            }
            println()
        }
    }

    //val input = readInput("Day10")
    //part2(input)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
