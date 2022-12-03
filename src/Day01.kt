fun List<String>.split(): List<List<Int>> {
    val listOfLists = arrayListOf<List<Int>>()
    var tmp = arrayListOf<Int>()
    this.forEach {
        // if we find an empty line, store the current list and create a new one
        if (it.isBlank()) {
            listOfLists.add(tmp)
            tmp = arrayListOf()
        } else {
            tmp.add(it.toInt())
        }
    }
    listOfLists.add(tmp)
    return listOfLists
}

fun main() {

    // [1,2,3, ,4,4, ,1,4,5] -> [[1,2,3],[4,4],[1,4,5]]
    // [[1,2,3],[4,4],[1,4,5]] -> [6,8,10]
    // [6,8,10] -> 10

    fun part1(input: List<String>): Int {
        return input.split()
            .map { it -> it.fold(0) { total, i -> total + i } }
            .max()
    }

    fun part2(input: List<String>): Int {
        return input.split()
            .map { it -> it.fold(0) { total, i -> total + i } }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    // test if implementation meets criteria from the description, like:
    val testInput2 = readInput("Day01_test")
    check(part2(testInput2) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}





// Initial implementation

//fun part1(input: List<String>): Int {
//
//        var caloriesPerElf = mutableListOf<Int>()
//        caloriesPerElf.add(0, 0)
//        var elf = 0;
//        for (i in 0..input.size - 1) {
//            if (input.get(i).isNotEmpty()){
//                caloriesPerElf[elf]=caloriesPerElf.get(elf)+input.get(i).toInt()
////                caloriesPerElf[elf] = (caloriesPerElf.get(elf)+input.get(i).toInt())
//            }
//            else
//            {
//                elf++
//                caloriesPerElf.add(elf, 0)
//            }
//        }
//        return caloriesPerElf.max()

//       return caloriesPerElf[0]+caloriesPerElf[1]+caloriesPerElf[2]