
fun main() {

    fun parseDequed(input: List<String>): List<ArrayDeque<Char>> {
        val numStacks = input[0].length / 3
        val stacks = mutableListOf<ArrayDeque<Char>>()
        repeat(numStacks) { stacks.add(ArrayDeque()) }
        input.takeWhile { it.contains('[') }.forEach {
            it.chunked(4).forEachIndexed { index, s ->
                if (s.isNotBlank()) {
                    stacks[index].addFirst(s[1])
                }
            }
        }
        return stacks
    }
    
    fun part1(input: List<String>): String {
        val (s, m) = input.chunkBy { it.isEmpty() }
        val stacks = parseDequed(s)
        m.forEach {
            val moves = it.split(" ")
            val amt = moves[1].toInt()
            val from = moves[3].toInt() - 1
            val to = moves[5].toInt() - 1
            repeat(amt) { stacks[to].addLast(stacks[from].removeLast()) }
        }
        return stacks.map { if (it.isNotEmpty()) it.removeLast() else "" }.joinToString("")
        
    }

    fun part2(input: List<String>): String {
        val (s, m) = input.chunkBy { it.isEmpty() }
        val stacks = parseDequed(s)
        m.forEach {
            val moves = it.split(" ")
            val amt = moves[1].toInt()
            val from = moves[3].toInt() - 1
            val to = moves[5].toInt() - 1
            val temp = ArrayDeque<Char>()
            repeat(amt) { temp.addLast(stacks[from].removeLast())}
            repeat(amt) { stacks[to].addLast(temp.removeLast())}
        }
        return stacks.map { if (it.isNotEmpty()) it.removeLast() else "" }.joinToString("")
    }

    //val input = readInput("Day05_test")
    //check(part1(input) == "CMZ")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
