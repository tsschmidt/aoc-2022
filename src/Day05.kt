import java.util.Stack

fun main() {

    fun parseStacks(input: List<String>): List<Stack<Char>> {
        val numStacks = input[0].length / 3
        val stacks = mutableListOf<Stack<Char>>()
        repeat(numStacks) { stacks.add(Stack<Char>()) }
        input.takeWhile { it.contains('[') }.forEach {
            it.chunked(4).forEachIndexed { index, s ->
                if (s.isNotBlank()) {
                    stacks[index].push(s[1])
                }
            }
        }
        stacks.forEach { it.reverse() }
        return stacks
    }


    fun part1(input: List<String>): String {
        val (s, m) = input.chunkBy { it.isEmpty() }
        val stacks = parseStacks(s)
        m.forEach {
            val moves = it.split(" ")
            val amt = moves[1].toInt()
            val from = moves[3].toInt() - 1
            val to = moves[5].toInt() - 1
            repeat(amt) { stacks[to].push(stacks[from].pop()) }
        }
        var ret = ""
        stacks.forEach { ret += if (it.isNotEmpty()) it.pop() else "" }
        return ret
    }

    fun part2(input: List<String>): String {
        val (s, m) = input.chunkBy { it.isEmpty() }
        val stacks = parseStacks(s)
        m.forEach {
            val moves = it.split(" ")
            val amt = moves[1].toInt()
            val from = moves[3].toInt() - 1
            val to = moves[5].toInt() - 1
            val temp = Stack<Char>()
            repeat(amt) { temp.push(stacks[from].pop())}
            repeat(amt) { stacks[to].push(temp.pop())}
        }
        var ret = ""
        stacks.forEach { ret += if (it.isNotEmpty()) it.pop() else "" }
        return ret 
    }

    //val input = readInput("Day05_test")
    //check(part1(input) == "CMZ")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
