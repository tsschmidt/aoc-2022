import kotlin.math.floor

data class Item(var worry: Long)

class Monkey {
    var id: Int = 0
    var items = mutableListOf<Item>()
    lateinit var opValue: (Long) -> Long
    var divisibleBy: Long = 0
    var divTrue: Int = 0
    var divFalse: Int = 0
    var inspected = 0L
    
    companion object {
        fun parseMonkey(input: List<String>): Monkey {
            val m = Monkey()
            m.id = input[0][input[0].length - 2].digitToInt()
            m.items = input[1].substringAfter(": ").split(",").map { Item(it.trim().toLong()) }.toMutableList()
            val op = input[2].substringAfter(" old ").split(" ")
            if (op[0] == "*") {
                if (op[1] == "old")
                    m.opValue = { a -> a * a }
                else {
                    m.opValue = { a -> a * op[1].toLong() }
                }
            } else {
                m.opValue = { a -> a + op[1].toLong() }
            }
            m.divisibleBy = input[3].substringAfter(" by ").toLong()
            m.divTrue = input[4].substringAfter("monkey ").toInt()
            m.divFalse = input[5].substringAfter(" monkey ").toInt()
            return m
        }
    }
    
    fun inspects(item: Item) {
        item.worry = opValue(item.worry)
        inspected++
    }
    
    fun tests(item: Item): Int {
        if(item.worry % divisibleBy == 0L) {
            return divTrue
        } else {
            return divFalse
        }
    }
}

fun main() {

    fun part1(input: List<String>): Long {
        val m = input.chunkBy { it.isBlank() }
        val monkeys = m.map { Monkey.parseMonkey(it) }
        repeat(20) {
            monkeys.forEach {
                while(it.items.isNotEmpty()) {
                    val cur = it.items.removeAt(0)
                    it.inspects(cur)
                    cur.worry = floor(cur.worry.toDouble() / 3.0).toLong()
                    monkeys[it.tests(cur)].items.add(cur)
                }
            }
        }
        val insps = monkeys.map { it.inspected }.sortedDescending().take(2)
        return insps[0] * insps[1]
    }

    fun part2(input: List<String>): Long {
        val m = input.chunkBy { it.isBlank() }
        val monkeys = m.map { Monkey.parseMonkey(it) }
        val modBy = monkeys.map { it.divisibleBy }.reduce {p, e -> p * e}
        println(modBy)
        repeat(10000) {
            monkeys.forEach {
                while(it.items.isNotEmpty()) {
                    val cur = it.items.removeAt(0)
                    it.inspects(cur)
                    cur.worry = cur.worry % modBy
                    monkeys[it.tests(cur)].items.add(cur)
                }
            }
        }
        val insps = monkeys.map { it.inspected }.sortedDescending().take(2)
        return insps[0] * insps[1]
    }

    //val input = readInput("Day11_test")
    //check(part1(input) == 8)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
