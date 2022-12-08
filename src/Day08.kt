fun main() {

    val day = "Day08"

    fun part1(input: List<String>): Int {
        var visible = 0
        var forest = input.map { it -> it.toList().map { it.digitToInt() } }

        // Skip the edges
        for (i in 1..forest.size - 2) {
            for (j in 1..forest.size - 2) {
                var horizontal = forest[i]
                var vertical = getCol(j, forest)

                val treesLeft = horizontal.subList(1, j)
                val treesRight = horizontal.subList(j + 1, horizontal.size - 1)
                val treesLeftUp = vertical.subList(1, i)
                val treesRightDown = vertical.subList(i + 1, vertical.size - 1)

                val edgeLeft = horizontal[0]
                val edgeRight = horizontal[horizontal.size - 1]
                val edgeUp = vertical[0]
                val edgeDown = vertical[horizontal.size - 1]

                val tree = horizontal[j]

                val visibleLeft = isTreeVisible(edgeLeft, treesLeft, tree)
                val visibleRight = isTreeVisible(edgeRight, treesRight, tree)
                val visibleUp = isTreeVisible(edgeUp, treesLeftUp, tree)
                val visibleDown = isTreeVisible(edgeDown, treesRightDown, tree)

                if (visibleLeft || visibleRight || visibleUp || visibleDown) {
                    visible++
                }
            }
        }
//        println("visible trees found: $visible")
        val result = visible + forest.size * 4 - 4
//        println("visible trees + perimeter : $result")
        return result
    }

    fun part2(input: List<String>): Int {
        var maxScore = 0
        var forest = input.map { it -> it.toList().map { it.digitToInt() } }

        // Iterate over all the items
        for (i in forest.indices) {
            for (j in forest.indices) {
                var row = forest[i]
                var column = getCol(j, forest)

                val treesLeft = row.subList(0, j)
                val treesRight = row.subList(j + 1, row.size)
                val treesLeftUp = column.subList(0, i)
                val treesRightDown = column.subList(i + 1, column.size)

                val tree = row[j]

                val scoreL = calculateScore(treesLeft.reversed(), tree)
                val scoreR = calculateScore(treesRight, tree)
                val scoreU = calculateScore(treesLeftUp.reversed(), tree)
                val scoreD = calculateScore(treesRightDown, tree)

                var score = scoreL * scoreR * scoreU * scoreD
                if (score > maxScore) maxScore = score
            }
        }
//        println("score: $maxScore")
        return maxScore
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${day}_test")

    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("$day")

    println(part1(input))
    println(part2(input))
}

private fun isTreeVisible(edge: Int, trees: List<Int>, tree: Int): Boolean {
    // A tree is visible if all the other trees between it and an edge of the grid are shorter than it
    var visible = false

    if (trees.isEmpty() && tree > edge) {
        visible = true
    }
    if (trees.isNotEmpty() && tree > edge) {
        visible = tree > trees.max()
    }
    return visible
}

private fun getCol(num: Int, input: List<List<Int>>): List<Int> {
    var r = mutableListOf<Int>()
    input.forEachIndexed { i, listOfTrees ->
        r.add(i, listOfTrees[num])
    }
    return r
}

private fun calculateScore(trees: List<Int>, tree: Int): Int {

    var score = 0
    for (i in trees) {
        if (i < tree) {
            score++
        }
        else if (i >= tree){
            score++
            break
        }
    }
    return score
}