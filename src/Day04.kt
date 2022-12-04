fun main() {
    fun getRanges(input: List<String>): Pair<List<Int>, List<Int>> = Pair(
        input[0].split("-").let { IntRange(it[0].toInt(), it[1].toInt()) }.toList(),
        input[1].split("-").let { IntRange(it[0].toInt(), it[1].toInt()) }.toList()
    )

    fun part1(input: List<String>): Int = input.count { pair ->
        with(pair.split(",")) {
            val ranges = getRanges(this)
            ranges.first.minus(ranges.second.toSet()).isEmpty()
                    || ranges.second.minus(ranges.first.toSet()).isEmpty()
        }
    }

    fun part2(input: List<String>): Int = input.count { pair ->
        with(pair.split(",")) {
            val ranges = getRanges(this)
            ranges.first.any { ranges.second.contains(it) }
        }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
