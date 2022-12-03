fun main() {
    fun part1(input: List<String>): Int {

//        "abcdaf" -> [[a,b,c],[d,a,f]] -> [a] -> [1]

        return input.map { it.toList().chunked(it.length/2) }
            .flatMap { e -> e.first().toSet().intersect(e.last().toSet()) }
            .sumOf { e -> getAsciiValue(e) }
    }


    fun part2(input: List<String>): Int {

//        [line1, line2, line3] -> [[a]] -> [a] -> [1]

        return input.map { it.toSet() }
            .windowed(3, 3) { it[0].intersect(it[1]).intersect(it[2]) }
            .flatten()
            .sumOf { getAsciiValue(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    // test if implementation meets criteria from the description, like:
    val testInput2 = readInput("Day03_test")
    check(part2(testInput2) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}


fun getAsciiValue(c: Char): Int {
    return when {
        c.isUpperCase() -> c.code - 38
        c.isLowerCase() -> c.code - 96
        else -> -1
    }
}

//        var res = mutableListOf<Char>()
//        input.forEach {
//            val splitList = it.toList().chunked(it.toList().size / 2)
//            res.add(splitList.first().intersect(splitList.last()).first())
//        }
//        val nums = res.map { it -> getAsciiValue(it) }
//
//        return nums.sum()