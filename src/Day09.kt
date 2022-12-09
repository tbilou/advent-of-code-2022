import kotlin.math.abs
import kotlin.math.sign

fun main() {

    val day = "Day09"

    fun getNextHead(direction: String, head: Coord): Coord {
        return when (direction) {
            "U" -> Coord(head.x, head.y + 1)
            "D" -> Coord(head.x, head.y - 1)
            "L" -> Coord(head.x - 1, head.y)
            "R" -> Coord(head.x + 1, head.y)
            else -> error("check your input!")
        }
    }

    fun part1(input: List<String>): Int {
        var head = Coord(0, 0)
        var tailPosition = Coord(0, 0)
        var lastHead = Coord(0, 0)
        var visited = mutableSetOf<Coord>()

        input.forEach { instruction ->
            var (direction, times) = instruction.split(" ")
            repeat(times.toInt()) {
                lastHead = head
                head = getNextHead(direction, head)
                val dist = distance(head, tailPosition)
                if (dist.x > 1 || dist.y > 1) {
                    visited.add(tailPosition)
                    tailPosition = lastHead
                }
            }
        }
        visited.add(tailPosition)
        return visited.size
    }

    fun part2(input: List<String>): Int {
        var visited = mutableSetOf<Coord>()
        var knots = List(9) { Coord(0, 0) }
        var head = Coord(0, 0)
        input.forEach { instruction ->
            var (direction, times) = instruction.split(" ")
            repeat(times.toInt()) {
                var nextHead = getNextHead(direction, head)
                head = nextHead
                knots = knots.map { knot ->
                    val updated = knot.update(nextHead)
                    nextHead = updated
                    updated
                }
                visited.add(knots.last())
            }
        }
        return visited.size
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")
    val testInput2 = readInput("${day}_test2")

    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
    check(part2(testInput2) == 36)

    val input = readInput("$day")

    println(part1(input))
    println(part2(input))
}

private fun distance(a: Coord, b: Coord): Coord {
    return Coord(abs(a.x - b.x), abs(a.y - b.y))
}

private data class Coord(var x: Int, var y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }

    fun update(knot: Coord): Coord {
        var magnitude = distance(knot, this)

        if (magnitude.x > 1 || magnitude.y > 1) {
            var signx = if (this.x > knot.x) -1 else 1
            var signy = if (this.y > knot.y) -1 else 1

            // we have the magnitude but we need direction
            val vector = Coord(magnitude.x * signx, magnitude.y * signy)
            // vector of magnitude 1
            val normalizedVector = Coord(1 * vector.x.sign, 1 * vector.y.sign)
            return Coord(x + normalizedVector.x, y + normalizedVector.y)
        }
        return this
    }
}