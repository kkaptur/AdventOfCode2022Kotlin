fun main() {
    val winningPairs = listOf(listOf("A", "B"), listOf("B", "C"), listOf("C", "A"))
    fun part1(input: List<String>): Int {
        var totalScore = 0
        var myShapeTranslated = ""
        var round = emptyList<String>()
        var roundTranslated = emptyList<String>()
        input.forEach {
            round = it.split(" ")
            when (round[1]) {
                "X" -> {
                    totalScore += 1
                    myShapeTranslated = "A"
                }
                "Y" -> {
                    totalScore += 2
                    myShapeTranslated = "B"
                }
                "Z" -> {
                    totalScore += 3
                    myShapeTranslated = "C"
                }
            }
            roundTranslated = listOf(round[0], myShapeTranslated)
            when {
                (myShapeTranslated == round[0]) -> totalScore += 3
                winningPairs.contains(roundTranslated) -> totalScore += 6
            }
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        var myShape = ""
        var round = emptyList<String>()
        input.forEach {
            round = it.split(" ")
            when (round[1]) {
                "X" -> {
                    totalScore += 0
                    myShape = winningPairs.find { pair -> pair[1] == round[0] }?.get(0) ?: ""
                }
                "Y" -> {
                    totalScore += 3
                    myShape = round[0]
                }
                "Z" -> {
                    totalScore += 6
                    myShape = winningPairs.find { pair -> pair[0] == round[0] }?.get(1) ?: ""
                }
            }
            when (myShape) {
                "A" -> totalScore += 1
                "B" -> totalScore += 2
                "C" -> totalScore += 3
            }
        }
        return totalScore
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

