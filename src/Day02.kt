fun main() {
    fun score(row: String): Int {
        return when (row) {
            "A X" -> 3 + 1
            "A Y" -> 6 + 2
            "A Z" -> 0 + 3
            "B X" -> 0 + 1
            "B Y" -> 3 + 2
            "B Z" -> 6 + 3
            "C X" -> 6 + 1
            "C Y" -> 0 + 2
            "C Z" -> 3 + 3
            else -> -1
        }
    }

    fun score2(row: String): Int {
        return when (row) {
            "A X" -> 0 + 3
            "A Y" -> 3 + 1
            "A Z" -> 6 + 2
            "B X" -> 0 + 1
            "B Y" -> 3 + 2
            "B Z" -> 6 + 3
            "C X" -> 0 + 2
            "C Y" -> 3 + 3
            "C Z" -> 6 + 1
            else -> -1
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { score(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { score2(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    // test if implementation meets criteria from the description, like:
    val testInput2 = readInput("Day02_test")
    check(part2(testInput2) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}