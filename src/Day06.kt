fun main() {

    val day = "Day06"

    fun hasNoDuplicates(s: String) = s.toList().size == s.toSet().size

    fun detectStartOfPacketMarker(l: List<String>, windowSize: Int): Int {
        for (i in 1..l.size) {
            if (hasNoDuplicates(l[i])) {
                return i + windowSize
            }
        }
        return -1
    }

    fun part1(input: String): Int {
        return detectStartOfPacketMarker(input.windowed(4), 4)
    }

    fun part2(input: String): Int {
        return detectStartOfPacketMarker(input.windowed(14), 14)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readText("${day}_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)

    val input = readText("$day")
    println(part1(input))
    println(part2(input))
}