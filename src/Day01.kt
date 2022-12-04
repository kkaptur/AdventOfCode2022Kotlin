fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        var highestSum = 0
        input.forEach {
            if (it.isNotBlank()) sum += it.toInt()
            if (sum > highestSum) highestSum = sum
            if (it.isBlank()) sum = 0
        }
        return highestSum
    }

    fun part2(input: List<String>): Int {
        val highestSums = mutableListOf(0, 0, 0)
        var currentSum = 0
        var minOfHighestSums = 0
        var minOfHighestSumsIndex = 0
        var isLastLine = false
        input.forEachIndexed { index, it ->
            if (it.isNotBlank()) currentSum += it.toInt()
            isLastLine = index == input.lastIndex
            if (it.isBlank() || isLastLine) {
                minOfHighestSums = highestSums.min()
                    .also { minSum -> minOfHighestSumsIndex = highestSums.indexOf(minSum) }
                if (currentSum > minOfHighestSums) highestSums[minOfHighestSumsIndex] = currentSum
                currentSum = 0
            }
        }
        return highestSums.sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
