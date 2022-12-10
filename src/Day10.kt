fun main() {

    val day = "Day10"

    fun part1(input: List<String>): Int {
        val sim = Simulator(input)

        var cycle = 0
        var total = 0

        while (cycle < 240) {
            cycle++
            if (cycle in listOf(20, 60, 100, 140, 180, 220)) {
                total += cycle * sim.register
            }
            sim.simulateStep()
        }
        return total
    }

    fun part2(input: List<String>) {
        val sim = Simulator(input)
        var cycle = 0
        var pixel = 1
        var sprite = 0

        var lcd = List(6) { mutableListOf("") }.map { list ->
            repeat(39) { list.add("") }
            list
        }

        while (cycle < 240) {
            cycle++
            val visible = sprite.visible(pixel)
            lcd.draw(pixel, visible)

            sim.simulateStep()

            pixel += 1
            sprite = sim.register
        }
        lcd.display()
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")

    check(part1(testInput) == 13140)
//    check(part2(testInput) == 0)

    val input = readInput("$day")

    println(part1(input))
    println(part2(input))
}

private fun List<MutableList<String>>.display() {
    this.forEach { row ->
        row.forEach { s ->
            print(s)
        }
        println()
    }
}

private fun List<MutableList<String>>.draw(pixel: Int, visible: Boolean) {
    val rowsize = 40
    val row = (pixel - 1) / rowsize
    var col = (pixel - 1) % rowsize
    this[row][col] = if (visible) "#" else "."
}

private fun Int.visible(pixel: Int): Boolean {
    var p = (pixel - 1) % 40
    return (this == p || this - 1 == p || this + 1 == p)
}

private class Simulator(input: List<String>){
    var instructions = expandInstructions(input)
    var register = 1
    var pointer = 0

    fun simulateStep(): Int {
        val i = instructions[pointer]

        if (i != "noop") {
            val v = i.substringAfter(" ").toInt()
            register += v
        }
        pointer++
        return register
    }

    fun expandInstructions(input: List<String>): MutableList<String> {
        var newInstructions = mutableListOf<String>()

        input.forEach { instruction ->
            if (instruction == "noop") {
                newInstructions.add(instruction)
            } else {
                newInstructions.add("noop")
                newInstructions.add(instruction)
            }
        }
        return newInstructions
    }
}