fun main() {

    val day = "Day07"

    fun buildFSTree(input: List<String>): FSTree {
        val fsTree = FSTree("/")
        var node = fsTree

        var changeDir = """\$ cd ([a-z]+)""".toRegex()

        input.drop(1)
            .forEach { line ->
                when {
                    line.equals("\$ cd ..") -> node = node.parent!!
                    changeDir.matches(line) -> {
                        val (newDir) = changeDir.find(line)!!.destructured
                        val newNode = FSTree(newDir)
                        node.addChild(newNode)
                        node = newNode
                    }
                    line[0].isDigit() -> node.size += line.substringBefore(" ").toInt()
                }
            }

        return fsTree
    }

    fun part1(fsTree: FSTree): Int {
        val results = mutableListOf<Int>()
        getSizes(fsTree, results)
        return results.filter { it < 100000 }.sumOf { it }
    }

    fun part2(fsTree: FSTree): Int {

        val results = mutableListOf<Int>()
        getSizes(fsTree, results)
        return results.filter { it > 30000000 - (70000000 - results.max()) }.min()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(buildFSTree(testInput)) == 95437)
    check(part2(buildFSTree(testInput)) == 24933642)

    val input = readInput("$day")
    println(part1(buildFSTree(input)))
    println(part2(buildFSTree(input)))
}

fun getSizes(node: FSTree, results: MutableList<Int>): Int {
    if (!node.children.isEmpty()) {
        val sumOfChildren = node.children.sumOf { getSizes(it, results) }
        val totalSize = node.size + sumOfChildren
        results.add(totalSize)
        return totalSize
    } else {
        // End condition (leaf)
        results.add(node.size)
        return node.size
    }
}

data class FSTree(val directoryName: String) {
    var size = 0
    var parent: FSTree? = null
    var children: MutableList<FSTree> = mutableListOf()

    fun addChild(node: FSTree) {
        children.add(node)
        node.parent = this
    }

    override fun toString(): String {
        var s = "$directoryName $size \n"
        if (!children.isEmpty()) {
            s += "\t" + children.map { it.toString() } + "\n"
        }
        return s
    }
}