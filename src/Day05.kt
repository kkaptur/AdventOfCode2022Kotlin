import java.io.File
import java.util.*

fun main() {
    val input = File("src", "Day05.txt").readText().split("\n\n")
    val testInput = File("src", "Day05_test.txt").readText().split("\n\n")

    fun getStacksWithElements(input: List<String>): MutableList<Stack<String>> =
        mutableListOf<Stack<String>>().apply {
            addAll(input[0].lines().last().filter { str -> str.isDigit() }.map { Stack() })
            input[0]
                .lines()
                .dropLast(1)
                .reversed()
                .map {
                    it.drop(1)
                        .windowed(1, 4)
                        .forEachIndexed { index, crate ->
                            if (crate.isNotBlank()) this[index].push(crate)
                        }
                }
        }


    fun getNumberData(input: List<String>): List<List<Int>> =
        input[1].lines().map {
            Regex("[0-9]+").findAll(it)
                .map(MatchResult::value)
                .map { result -> result.toInt() }
                .toList()
        }

    fun part1(input: List<String>): String {
        val stacks = getStacksWithElements(input)
        getNumberData(input).forEach { numbers ->
            for (move: Int in 1..numbers[0]) {
                stacks[numbers[2] - 1].push(stacks[numbers[1] - 1].pop())
            }
        }
        return stacks.joinToString("") { it.peek().toString() }
    }

    fun part2(input: List<String>): String {
        val stacks = getStacksWithElements(input)
        getNumberData(input).forEach { numbers ->
            val movedElements = mutableListOf<String>()
            for (move: Int in 1..numbers[0]) {
                movedElements.add(stacks[numbers[1] - 1].pop())
            }
            stacks[numbers[2] - 1].addAll(movedElements.reversed())
        }
        return stacks.joinToString("") { it.peek().toString() }
    }

    println(part1(input))
    println(part2(input))
}
