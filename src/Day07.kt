import java.security.InvalidParameterException

fun main() {
    
    data class File(val name: String, val size: Long) 
    
    class Directory(val name: String, val parent: Directory?) {
        val files = mutableListOf<File>()
        val dirs = mutableListOf<Directory>()
        var size = 0L
    }
    
    fun addSize(node: Directory, size: Long) {
        node.size += size
        if (node.parent !== null) {
            addSize(node.parent, size)
        }
    }
    
    fun createTree(input: List<String>): Directory {
        val root = Directory("/", null)
        var node = root
        input.drop(1).forEach { 
            if (it.startsWith("$ cd")) {
                val d = it.split(" ").last()
                if (d == "..") {
                    node = node.parent ?: throw IndexOutOfBoundsException("")
                } else {
                    node = node.dirs.find { it.name == d } ?: throw InvalidParameterException("")
                }
            } else if (it.contains("dir")) {
                val d = it.split(" ").last()
                node.dirs.add(Directory(d, node))
            } else if (!it.startsWith("$")){
                val (size, name) = it.split(" ")
                node.files.add(File(name, size.toLong()))
                addSize(node, size.toLong())
            }
        }
        return root
    }
    
    fun flattenDirs(node: Directory, dirs: MutableList<Directory>): List<Directory> {
        dirs.addAll(node.dirs)
        node.dirs.forEach { 
            flattenDirs(it, dirs)
        }
        return dirs
    }

    fun part1(input: List<String>): Long {
        val tree = createTree(input)
        val dirs = flattenDirs(tree, mutableListOf(tree))
        return dirs.filter { it.size < 100000 }.sumOf { it.size }
    }

    fun part2(input: List<String>): Long {
        val tree = createTree(input)
        val dirs = flattenDirs(tree, mutableListOf(tree))
        val used = tree.size
        val free = 70000000 - used
        val needed = 30000000 - free
        return dirs.filter { it.size > needed }.minOf { it.size }
    }

    //val input = readInput("Day07_test")
    //check(part1(input) == 95437L)

    val input = readInput("Day07")
    //println(part1(input))
    println(part2(input))
}
