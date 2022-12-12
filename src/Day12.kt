import java.util.*

fun main() {

    val day = "Day12"

    fun reconstructPath(
        cameFrom: HashMap<Pair<Int, Int>, Pair<Int, Int>>,
        node: Pair<Int, Int>
    ): MutableList<Pair<Int, Int>> {
        var path = mutableListOf(node)
        var current = node
        while (cameFrom.containsKey(current)) {
            current = cameFrom[current]!!
            path.add(current)
        }
        path.removeLast()
        return path
    }

    // Adapted the pseudo-code to kotlin https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
    fun a_star(start: Pair<Int, Int>, goal:Pair<Int,Int>, maze: Maze): List<Pair<Int, Int>> {
        var stack: Deque<Pair<Int, Int>> = java.util.ArrayDeque()

        stack.push(start)

        var cameFrom: HashMap<Pair<Int, Int>, Pair<Int, Int>> = HashMap()
        var gScore: HashMap<Pair<Int, Int>, Int> = HashMap()
        var fScore: HashMap<Pair<Int, Int>, Int> = HashMap()

        for (i in 0..maze.dimension().first - 1) { //row
            for (j in 0..maze.dimension().second - 1) { // column
                gScore[i to j] = Int.MAX_VALUE
                fScore[i to j] = Int.MAX_VALUE
            }
        }

        gScore[start] = 0
        fScore[start] = maze.h(maze.getValue(start))

        while (stack.isNotEmpty()) {
            val current = stack.sortedBy { fScore.get(it) }.first()

            if (current == goal) {
                val path = reconstructPath(cameFrom, current)
                return path
            }

            stack.remove(current)

            // Get neighbours
            val neighbours = maze.neighbours(current)
            neighbours.forEach { neighbour ->
                val tentativeScore = gScore.get(current)?.plus(maze.d(current, neighbour))
                if (tentativeScore!! < gScore.get(neighbour)!!) {
                    cameFrom.put(neighbour, current)
                    gScore[neighbour] = tentativeScore
                    fScore[neighbour] = tentativeScore + maze.h(maze.getValue(neighbour))
                    if (!stack.contains(neighbour)) {
                        stack.push(neighbour)
                    }
                }
            }
        }
        return emptyList()
    }

    fun part1(input: List<String>): Int {
        val maze = Maze(input)
        val start = maze.getStart()
        var goal = maze.getGoal()
        return a_star(start, goal, maze).size
    }

    fun part2(input: List<String>): Int {
        val maze = Maze(input)
        var goal = maze.getGoal()
        val all = maze.getAll('a')
            .map { start ->
                a_star(start, goal, maze)
            }.filter { it.isNotEmpty() }

        return all.map { it.size }.min()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("$day")
    println(part1(input))
    println(part2(input))
}


class Maze(input: List<String>) {
    val grid = input.map { it.toList().map { it } }

    fun getStart():Pair<Int,Int>{
        return getFirst('S')
    }
    fun getGoal():Pair<Int,Int>{
        return getFirst('E')
    }

    private fun getFirst(value: Char): Pair<Int, Int> {
        grid.forEachIndexed { row, list ->
            val col = list.indexOf(value)
            if (col != -1) {
                return Pair(row, col)
            }
        }
        error("Value not found")
    }

    fun getAll(search: Char): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                if (c == search) {
                    result.add(i to j)
                }
            }
        }
        return result
    }

    fun getValue(a: Pair<Int, Int>): Char {
        val c = grid[a.first][a.second]
        return when(c){
            'S' -> 'a'
            'E' -> 'z'
            else -> c
        }
    }

    // Our goal is to reach z. any character that gets us closer has priority
    fun h(height: Char): Int {
        return 'z'.code - height.code
    }

    // We want to minimize the distance between us and our neighbour.
    // letter closer to current are better. Initially I was allowing 0 when traveling between the same letter
    // but this doesn't lead to the shortest path. The has to be a penalty when staying in the same letter.
    // The more we travel in the same letter the more expensive it becomes.
    fun d(current: Pair<Int, Int>, neighbour: Pair<Int, Int>): Int {
        val vcurrent = this.getValue(current)
        val vneighbour = this.getValue(neighbour)

        return if (kotlin.math.abs(vcurrent - vneighbour) == 0) {
            1 // Add a penalty when staying in the same letter
        } else {
            kotlin.math.abs(vcurrent - vneighbour) + 1
        }
    }

    fun dimension(): Pair<Int, Int> {
        return Pair(grid.size, grid.first().size)
    }

    // Needs refactoring.
    // Neighbours that are not accessible (next letter) are not returned
    fun neighbours(coord: Pair<Int, Int>): List<Pair<Int, Int>> {
        var up = if (grid.getOrNull(coord.first - 1) != null) Pair(coord.first - 1, coord.second) else null
        var down = if (grid.getOrNull(coord.first + 1) != null) Pair(coord.first + 1, coord.second) else null
        var left =
            if (grid.get(coord.first).getOrNull(coord.second - 1) != null) Pair(coord.first, coord.second - 1) else null
        var right =
            if (grid.get(coord.first).getOrNull(coord.second + 1) != null) Pair(coord.first, coord.second + 1) else null

        if (up != null && getValue(up) - getValue(coord) > 1) {
            up = null
        }

        if (down != null && getValue(down) - getValue(coord) > 1) {
            down = null
        }

        if (left != null && getValue(left) - getValue(coord) > 1) {
            left = null
        }

        if (right != null && getValue(right) - getValue(coord) > 1) {
            right = null
        }
        return listOfNotNull(up, down, left, right) as List<Pair<Int, Int>>
    }
}