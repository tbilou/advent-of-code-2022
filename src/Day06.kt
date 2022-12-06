fun main() {

    val day = "Day06"

    fun hasNoDuplicates(s: String) = s.toList().groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty()

    fun part1(input: String): Int {
        var answer = 0
        val l = input.windowed(4)

        for (i in 1..l.size){
            if (hasNoDuplicates(l[i])) {
                answer = i + 4
                break
            }
        }
        return answer
    }

    fun part2(input: String): Int {
        var answer = 0
        val l = input.windowed(14)

        for (i in 1..l.size){
            if (hasNoDuplicates(l[i])) {
                answer = i + 14
                break
            }
        }
        return answer
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readText("${day}_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)

    val input = readText("$day")
    println(part1(input))
    println(part2(input))
}