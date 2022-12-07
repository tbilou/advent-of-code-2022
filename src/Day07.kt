fun main() {

    val day = "Day07"

    fun buildFSTree(input: List<String>): FSTree {
        val fsTree = FSTree("/")
        var currentDirectory = fsTree

        input.drop(1) // skip the first one. It's manually created above
            .forEach { line ->
                when {
                    line.isMoveOutOfCurrentDirectory() -> currentDirectory = currentDirectory.parent!!
                    line.isFile() -> currentDirectory.size += line.substringBefore(" ").toInt()
                    line.isChangeDirectory() -> {
                        val dirName = line.substringAfter("cd ")
                        val newDirectory = FSTree(dirName)
                        currentDirectory.addChild(newDirectory)
                        currentDirectory = newDirectory
                    }
                }
            }
        return fsTree
    }

    fun part1(fsTree: FSTree): Int {
        val results = mutableListOf<Int>()
        getDirectorySizes(fsTree, results)
        return results.filter { it < 100000 }.sumOf { it }
    }

    fun part2(fsTree: FSTree): Int {
        val results = mutableListOf<Int>()
        getDirectorySizes(fsTree, results)
        return results.filter { it > 30000000 - (70000000 - results.max()) }.min()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")

//    check((parseInput(testInput)) == 95437)
    check(part1(buildFSTree(testInput)) == 95437)
    check(part2(buildFSTree(testInput)) == 24933642)

    val input = readInput("$day")

    println(part1(buildFSTree(input)))
    println(part2(buildFSTree(input)))
}


// Filesystem Tree that represent a directory, it's sub-directories and size
private data class FSTree(val directoryName: String) {
    var size = 0
    var parent: FSTree? = null
    var subDirectories: MutableList<FSTree> = mutableListOf()

    fun addChild(node: FSTree) {
        subDirectories.add(node)
        node.parent = this
    }

    // Debug
    override fun toString(): String {
        var s = "$directoryName $size \n"
        if (!subDirectories.isEmpty()) {
            s += "\t" + subDirectories.map { it.toString() } + "\n"
        }
        return s
    }
}

private fun getDirectorySizes(directory: FSTree, results: MutableList<Int>): Int {
    if (!directory.subDirectories.isEmpty()) {
        val sumOfChildren = directory.subDirectories.sumOf { getDirectorySizes(it, results) }
        val totalSize = directory.size + sumOfChildren
        results.add(totalSize)
        return totalSize
    } else {
        // End condition
        results.add(directory.size)
        return directory.size
    }
}

private fun String.isFile(): Boolean {
    return """[0-9]+ .*""".toRegex().matches(this)
}

private fun String.isChangeDirectory():Boolean{
    return """\$ cd .+""".toRegex().matches(this)
}

private fun String.isMoveOutOfCurrentDirectory(): Boolean {
    return this == "\$ cd .."
}