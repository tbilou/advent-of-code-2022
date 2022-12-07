fun main() {

    val day = "Day07"

    fun parseInput(input: List<String>): List<Int> {
        var path = "/root"
        var dirs = mutableListOf<String>("root")
        var paths:MutableMap<String, Int> = mutableMapOf()
        input
            .drop(1)
            .forEach { line ->
                when {
                    line.isMoveOutOfCurrentDirectory() -> path = path.substringBeforeLast("/")
                    line.isFile() -> paths.put(path+"/"+line.substringAfter(" "), line.substringBefore(" ").toInt())
                    line.isChangeDirectory() -> {
                        val dirName = line.substringAfter("cd ")
                        path += "/$dirName"
                        dirs.add(path)
                    }
                }
            }

        return dirs
            .map { dir -> paths.keys.filter { it.contains(dir) }
//                .also { println(it) }
                .map {key -> paths.get(key)}
//                .also { println(it) }
                .sumOf { it!!.toInt() }
            }
    }

    fun part1(input: List<String>): Int {
        val sizes = parseInput(input)
        return sizes.filter { it < 100000 }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val sizes = parseInput(input)
        return sizes.filter { it > 30000000 - (70000000 - sizes.max()) }.min()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")

    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("$day")

    println(part1(input))
    println(part2(input))
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