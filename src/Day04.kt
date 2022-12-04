fun main() {

//        "2-4,6-8"
//        [[2-4],[6-8]]
//        [[2,3,4],[6,7,8]]

    fun parseInput(input: List<String>) = input
        .map { p -> p.split(',') }
        .map { (r1, r2) -> listOf(r1.toRange(), r2.toRange()).sortedBy { it.size } }

    fun part1(input: List<String>): Int {
        return parseInput(input)
            .map { (l1, l2) -> l1.subtract(l2).size }
            .count { i -> i == 0 }
    }


    fun part2(input: List<String>): Int {
        return parseInput(input)
            .map { (l1, l2) -> (l1+l2).size - (l1+l2).toSet().size}
            .count { it > 0 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    // test if implementation meets criteria from the description, like:
    val testInput2 = readInput("Day04_test")
    check(part2(testInput2) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

private fun String.toRange(): List<Int> {
    return this.split('-').toRange()
}

private fun List<String>.toRange(): List<Int> {
    return (first().toInt()..last().toInt()).toList()
}
