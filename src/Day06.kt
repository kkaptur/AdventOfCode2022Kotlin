import java.io.File

fun main() {

    val input = File("src", "Day06.txt").readText()

    fun getIndexAfterNDistinctCharacters(distinctCharactersNumber: Int, input: String): Int {
        input.windowed(distinctCharactersNumber, 1).forEachIndexed { index, s ->
            if (s.toSet().size == distinctCharactersNumber) return index + distinctCharactersNumber
        }
        return 0
    }

    fun part1(input: String): Int = getIndexAfterNDistinctCharacters(4, input)

    fun part2(input: String): Int = getIndexAfterNDistinctCharacters(14, input)

    println(part1(input))
    println(part2(input))
}
