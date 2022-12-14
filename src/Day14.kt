fun main() {

    val day = "Day14"

    fun expand(p1: Pair<Int, Int>, p2: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
        var result = mutableListOf<Pair<Int, Int>>()
        if (p1.first == p2.first) {
            // iterate over Y
            val l = listOf(p1.second, p2.second).sortedDescending()
            val steps = l.reduce(Int::minus)
            for (y in 0..steps) {
                result.add(p1.first to l.last() + y)
            }

        } else if (p1.second == p2.second) {
            // iterate over X
            val l = listOf(p1.first, p2.first).sortedDescending()
            val steps = l.reduce(Int::minus)
            for (x in 0..steps) {
                result.add(l.last() + x to p1.second)
            }
        } else error("check your input!")
        return result
    }

    fun parseInput(input: List<String>): List<Pair<Int, Int>> {
        return input.map { line ->
            line.split(" -> ")
                .map { it -> it.split(',') }
                .map { it.first().toInt() to it.last().toInt() }
                .also { println(it) }
                .windowed(2) { (a, b) -> expand(a, b) }.flatten()
        }
            .flatten()
    }

    fun canContinueToFall(grid: List<MutableList<String>>, p: Pair<Int, Int>): Boolean {
        return try {
            when (grid[p.second][p.first]) {
                "." -> true
                else -> false
            }
        } catch (exception: IndexOutOfBoundsException) {
            false
        }
    }

    fun step(grid: List<MutableList<String>>, sand: Pair<Int, Int>): Pair<Int, Int> {
        // Check if sand can fall down
        val down = sand.second + 1
        return when (grid[down][sand.first]) {
            "." -> sand.first to down //Air
            "#", "o" -> {
                val left = canContinueToFall(grid, sand.first - 1 to down)
                val right = canContinueToFall(grid, sand.first + 1 to down)
                if (left) {
                    sand.first - 1 to down
                } else if (right) {
                    sand.first + 1 to down
                } else {
                    sand
                }
            }
            else -> error("impossible condition. Check input!")
        }
    }

    fun buildGrid(list: List<Pair<Int, Int>>): List<MutableList<String>> {
        // Build grid
        val x = list.map { it.first }.max() * 2
        val y = list.map { it.second }.max()

        var grid = List(y + 3) { mutableListOf("") }
            .map { list ->
                repeat(x) { list.add(".") }
                list
            }

        // Add walls
        list.forEach { p -> grid[p.second][p.first] = "#" }

        return grid
    }


    fun part1(input: List<String>): Int {
        val list = parseInput(input)

        // Cave Layout
        val grid = buildGrid(list)

        // Sand Simulation
        val start = 500
        val abyss = grid.size - 1

        var currentPos = -1 to -1
        var nextPos = start to 0
        var steps = 0
        var dropSand = true

        // Simulate the drop of a grain of sand
        while (dropSand) {
            steps++
            while (currentPos != nextPos) {
                // check if we are falling into the abyss
                if (nextPos.second >= abyss) {
                    println("next on will go into the abyss. units:$steps")
                    dropSand = false
                    break
                }
                currentPos = nextPos
                nextPos = step(grid, nextPos)
                print("")
            }
            grid[nextPos.second][nextPos.first] = "o"
            currentPos = -1 to -1
            nextPos = start to 0
        }

        grid.print()
        return steps - 1
    }

    fun part2(input: List<String>): Int {
        val list = parseInput(input)

        // Cave Layout
        val grid = buildGrid(list)
        repeat(grid[0].size) {
            grid[grid.size - 1][it] = "#"
        }
        grid.print()

        // Sand Simulation
        val start = 500
        val goal = 500 to 0

        var currentPos = -1 to -1
        var nextPos = start to 0
        var steps = 0
        var dropSand = true

        // Simulate the drop of a grain of sand
        while (dropSand) {
            steps++
            while (currentPos != nextPos) {
                currentPos = nextPos
                nextPos = step(grid, nextPos)
                // check if we're still at the top
                if (nextPos.first == goal.first && nextPos.second == goal.second) {
                    println("We reached the goal. units:$steps")
                    dropSand = false
                    break
                }
            }
            grid[nextPos.second][nextPos.first] = "o"
            currentPos = -1 to -1
            nextPos = start to 0
        }

        grid.print()
        return steps
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 24)
    check(part2(testInput) == 93)

    val input = readInput("$day")
    println(part1(input))
    println(part2(input))
}

private fun List<MutableList<String>>.print() {
    this.forEach { row ->
        row.forEach { s ->
            print(s)
        }
        println()
    }
}