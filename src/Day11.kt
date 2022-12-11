fun main() {

    val day = "Day11"

    // Hardcode input
    fun input(): List<Monkey> {
        var monkey0 = Monkey(
            ArrayDeque(listOf(89, 84, 88, 78, 70)),
            { a: Long -> a * 5 },
            mapOf(true to 6, false to 7),
            7
        )

        var monkey1 = Monkey(
            ArrayDeque(listOf(76, 62, 61, 54, 69, 60, 85)),
            { a: Long -> a + 1 },
            mapOf(true to 0, false to 6),
            17
        )

        var monkey2 = Monkey(
            ArrayDeque(listOf(83, 89, 53)),
            { a: Long -> a + 8 },
            mapOf(true to 5, false to 3),
            11
        )

        var monkey3 = Monkey(
            ArrayDeque(listOf(95, 94, 85, 57)),
            { a: Long -> a + 4 },
            mapOf(true to 0, false to 1),
            13
        )

        var monkey4 = Monkey(
            ArrayDeque(listOf(82, 98)),
            { a: Long -> a + 7 },
            mapOf(true to 5, false to 2),
            19
        )

        var monkey5 = Monkey(
            ArrayDeque(listOf(69)),
            { a: Long -> a + 2 },
            mapOf(true to 1, false to 3),
            2
        )

        var monkey6 = Monkey(
            ArrayDeque(listOf(82, 70, 58, 87, 59, 99, 92, 65)),
            { a: Long -> a * 11 },
            mapOf(true to 7, false to 4),
            5
        )

        var monkey7 = Monkey(
            ArrayDeque(listOf(91, 53, 96, 98, 68, 82)),
            { a: Long -> a * a },
            mapOf(true to 4, false to 2),
            3
        )

        return listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)
    }

    fun testInput(): List<Monkey> {
        var monkey1 = Monkey(
            ArrayDeque(listOf(79, 98)),
            { a: Long -> a * 19 },
            mapOf(true to 2, false to 3),
            23
        )

        var monkey2 = Monkey(
            ArrayDeque(listOf(54, 65, 75, 74)),
            { a: Long -> a + 6 },
            mapOf(true to 2, false to 0),
            19
        )

        var monkey3 = Monkey(
            ArrayDeque(listOf(79, 60, 97)),
            { a: Long -> a * a },
            mapOf(true to 1, false to 3),
            13
        )

        var monkey4 = Monkey(
            ArrayDeque(listOf(74)),
            { a: Long -> a + 3 },
            mapOf(true to 0, false to 1),
            17
        )
        return listOf(monkey1, monkey2, monkey3, monkey4)
    }

    fun part1(input: List<Monkey>): Int {
        var rounds = 20
        repeat(rounds) {
            input.forEach { monkey ->
                for (item in monkey.items) {
                    monkey.inspected++
                    val worryLevel = monkey.operation.invoke(item) / 3
                    val nextMonkeyIndex = monkey.test.get(worryLevel % monkey.divisibleBy == 0L)
                    input[nextMonkeyIndex!!].items.addLast(worryLevel)
                }
                monkey.items.clear()
            }
        }
        return input.map { monkey -> monkey.inspected }
            .sortedDescending()
            .take(2)
            .reduce(Int::times)
    }

    fun part2(input: List<Monkey>): Long {
//        MrSimbax in the subreddit:
//        Part 2 is basically part 1 but operations are done modulo N, where N is the product of all numbers from the input divisibility rules.
//        This works because if N = n * m then a % n == x implies (a % N) % n == x.
//        For completeness, this also holds for N = lcm(n, m), which may be smaller than n * m if the divisors are composite.
//        In the puzzle all divisors are primes though so lcm(n, m) == n * m.
        val magicNumber = input.map { it.divisibleBy }.reduce(Int::times)

        var rounds = 10_000
        repeat(rounds) {
            input.forEach { monkey ->
                for (item in monkey.items) {
                    monkey.inspected++
                    val worryLevel = monkey.operation.invoke(item) % magicNumber
                    val nextMonkeyIndex = monkey.test.get(worryLevel % monkey.divisibleBy == 0L)
                    input[nextMonkeyIndex!!].items.addLast(worryLevel)
                }
                monkey.items.clear()
            }
        }
        return input.map { monkey -> monkey.inspected.toLong() }
            .sortedDescending()
            .take(2)
            .reduce(Long::times)
    }

    // test if implementation meets criteria from the description, like:
    check(part1(testInput()) == 10605)
    check(part2(testInput()) == 2713310158)

    println(part1(input()))
    println(part2(input()))
}

data class Monkey(
    val items: ArrayDeque<Long>,
    val operation: (Long) -> Long,
    val test: Map<Boolean, Int>,
    var divisibleBy: Int,
    var inspected: Int = 0
)