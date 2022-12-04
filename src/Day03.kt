fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        var firstCompartment = emptySet<Char>()
        var secondCompartment = emptySet<Char>()
        input.forEach { rucksack ->
            firstCompartment = rucksack.subSequence(0, (rucksack.length / 2)).toSet()
            secondCompartment = rucksack.subSequence((rucksack.length / 2), rucksack.length).toSet()
            firstCompartment.filter { secondCompartment.contains(it) }.forEach {
                score += if (it.isUpperCase())
                    it.code - 65 + 27
                else
                    it.code - 97 + 1
            }
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        input.chunked(3).forEach { group ->
            group[0].toCharArray().toSet()
                .filter { group[1].contains(it) }
                .filter { group[2].contains(it) }
                .forEach {
                    score += if (it.isUpperCase())
                        it.code - 65 + 27
                    else
                        it.code - 97 + 1
                }
        }
        return score
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
