fun main() {
    fun part1(input: List<String>): Int {
        var highestSum = 0
        var sum = 0
        input.forEach {
            if (it.isBlank()) {
                if (sum > highestSum) highestSum = sum
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        return highestSum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)


    val input = readInput("Day01")
    println(part1(testInput))
    println(part2(testInput))
}
