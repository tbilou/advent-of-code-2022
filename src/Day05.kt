fun main() {

    val day = "Day05"

    fun part1(input: String): String {
        var parsedInput = parseInput(input)
        parsedInput.instuctions.forEach { instruction ->
            val fromStack = parsedInput.stacks.get(instruction.from - 1)
            val toStack = parsedInput.stacks.get(instruction.to - 1)
            repeat(instruction.amount) { toStack.add(fromStack.removeLast()) }
        }
        val result = parsedInput.stacks.fold("") { acc, item -> acc + item.last() }
        return result
    }

    fun part2(input: String): String {
        var parsedInput = parseInput(input)
        parsedInput.instuctions.forEach { instruction ->
            val fromStack = parsedInput.stacks.get(instruction.from - 1)
            val toStack = parsedInput.stacks.get(instruction.to - 1)
            toStack.addAll(fromStack.removeN(instruction.amount))
        }
        val result = parsedInput.stacks.fold("") { acc, item -> acc + item.last() }
        return result
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readText("${day}_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readText("$day")
    println(part1(input))
    println(part2(input))
}

private fun <String> ArrayDeque<String>.removeN(amount: Int): List<String> {
    var tmp = mutableListOf<String>()
    repeat(amount) {tmp.add(this.removeLast())}
    return tmp.reversed()
}

fun parseInput(input: String): Input {
    val stacks = mutableListOf<ArrayDeque<String>>()
    val list = input.split("\n\n")
    list.first().split('\n')
        .reversed()
        .also {
            val numColumns: Int = it.first().trim().last().digitToInt()
            for (i in 1..numColumns) {
                stacks.add(ArrayDeque(0))
            }
        }
        .let { it.subList(1, it.size) }
        .map { s ->
            s.chunked(4)
                .map { it.filter { it in 'A'..'Z' } }
                .mapIndexed { i, e -> if (e.isNotEmpty()) stacks[i].add(e) }
        }

    val operations = list.last().split('\n')
        .map { line -> parseOperation(line) }

    return Input(stacks, operations)
}

private fun parseOperation(line: String): Operation {
    val inputLineRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val (amount, from, to) = inputLineRegex
        .matchEntire(line)
        ?.destructured
        ?: throw IllegalArgumentException("Incorrect input line $line")
    return Operation(amount.toInt(), from.toInt(), to.toInt())
}

data class Operation(val amount: Int, val from: Int, val to: Int)
data class Input(val stacks: List<ArrayDeque<String>>, val instuctions: List<Operation>)
