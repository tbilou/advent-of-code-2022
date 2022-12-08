fun main() {

    val day = "Day08"

    fun part1(input: List<String>): Int {
        var visible = 0
        var l = input.map { it -> it.toList().map { it.digitToInt() } }

        // Skip the edges
        for (i in 1..l.size - 2) {
            for (j in 1..l.size - 2) {
                var row = l[i]
                var column = getCol(j, l)

                val treesLeft = row.subList(1, j)
                val treesRight = row.subList(j + 1, row.size - 1)
                val treesLeftUp = column.subList(1, i)
                val treesRightDown = column.subList(i + 1, column.size - 1)

                val edgeLeft = row[0]
                val edgeRight = row[row.size - 1]
                val edgeUp = column[0]
                val edgeDown = column[row.size - 1]

                val tree = row[j]

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
        val result = visible + l.size * 4 - 4
//        println("visible trees + perimeter : $result")
        return result
    }

    fun part2(input: List<String>): Int {
        var maxScore = 0
        var l = input.map { it -> it.toList().map { it.digitToInt() } }

        // Iterate over all the items
        for (i in l.indices) {
            for (j in l.indices) {
                var row = l[i]
                var column = getCol(j, l)

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