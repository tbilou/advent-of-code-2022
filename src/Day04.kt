fun main() {
    fun parseInput(input: List<String>) = input.map { p -> p.split(',') }
        .map { l1 ->
            l1.map { e -> e.split('-').toRange() }
                .sortedBy { it.size }
                .windowed(2, 2) { it[0].subtract(it[1]).size }
        }

    fun part1(input: List<String>): Int {

//        "2-4,6-8"
//        [[2-4],[6-8]]
//        [[2,3,4],[6,7,8]]

        return parseInput(input)
            .count { it.first() == 0 }
    }


    fun part2(input: List<String>): Int {
        return input.map { p -> p.split(',') }
            .map { l1 ->
                l1.map { e -> e.split('-').toRange() }
                    .windowed(2, 2) { it[0].plus(it[1]).size - it[0].plus(it[1]).toSet().size }
            }
            .count { it.first() > 0 }
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

private fun List<String>.toRange(): List<Int> {
    return (first().toInt()..last().toInt()).toList()
}
