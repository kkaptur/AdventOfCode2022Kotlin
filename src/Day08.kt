import java.io.File
import java.util.Collections.max

class Position(
    val topElements: MutableList<Int>,
    val bottomElements: MutableList<Int>,
    val leftElements: MutableList<Int>,
    val rightElements: MutableList<Int>
) {
    companion object {
        fun of(
            currentColumnElements: MutableList<Int>,
            currentRowElements: MutableList<Int>,
            rowIndex: Int,
            elementIndex: Int,
            listSize: Int,
            rowSize: Int
        ): Position {
            return Position(
                topElements = currentColumnElements.subList(0, rowIndex),
                bottomElements = currentColumnElements.subList(rowIndex + 1, listSize),
                leftElements = currentRowElements.subList(0, elementIndex),
                rightElements = currentRowElements.subList(elementIndex + 1, rowSize)
            )
        }
    }

    fun allElements(): MutableList<Int> {
        val fullList = mutableListOf<Int>()
        fullList.addAll(topElements)
        fullList.addAll(bottomElements)
        fullList.addAll(leftElements)
        fullList.addAll(rightElements)
        return fullList
    }
}

fun main() {

    val input = File("src", "Day08.txt").readLines()
    val inputTest = File("src", "Day08_test.txt").readLines()

    fun getPopulatedTable(input: List<String>): MutableList<MutableList<Int>> {
        val lists = mutableListOf<MutableList<Int>>()
        input.map { row ->
            lists.add(mutableListOf<Int>().apply {
                addAll(row.map { it.digitToInt() })
            })
        }
        return lists
    }

    fun part1(input: List<String>): Int {
        val lists = getPopulatedTable(input)
        var visibleTreesNumber = 0

        //trees on the edges
        visibleTreesNumber += 2 * lists.size + (lists[0].size - 2) * 2

        //trees in the interior
        lists.forEachIndexed { rowIndex, elementsList ->
            if (rowIndex == 0 || rowIndex == lists.size - 1) return@forEachIndexed
            elementsList.forEachIndexed { elementIndex, element ->
                if (elementIndex != 0 && elementIndex != elementsList.size - 1) {
                    val currentColumn = mutableListOf<Int>()
                    lists.forEach { list -> currentColumn.add(list[elementIndex]) }
                    val elementPosition = Position.of(
                        currentColumn,
                        elementsList,
                        rowIndex,
                        elementIndex,
                        lists.size,
                        elementsList.size
                    )
                    if (max(elementPosition.topElements) < element
                        || max(elementPosition.bottomElements) < element
                        || max(elementPosition.leftElements) < element
                        || max(elementPosition.rightElements) < element
                    ) visibleTreesNumber += 1
                }
            }
        }
        return visibleTreesNumber
    }


    fun part2(input: List<String>): Int {
        val lists = getPopulatedTable(input)
        var visibleTreesNumber = 0

        val scenicScores = mutableListOf<Int>()
        //trees on the edges
        visibleTreesNumber += 2 * lists.size + (lists[0].size - 2) * 2

        //trees in the interior
        lists.forEachIndexed { rowIndex, elementsList ->
            if (rowIndex == 0 || rowIndex == lists.size - 1) return@forEachIndexed
            elementsList.forEachIndexed { elementIndex, element ->
                if (elementIndex != 0 && elementIndex != elementsList.size - 1) {
                    var scoreTop = 0
                    var scoreBottom = 0
                    var scoreLeft = 0
                    var scoreRight = 0
                    val currentColumn = mutableListOf<Int>()
                    lists.forEach { list ->
                        currentColumn.add(list[elementIndex])
                    }
                    val elementPosition = Position.of(
                        currentColumn,
                        elementsList,
                        rowIndex,
                        elementIndex,
                        lists.size,
                        elementsList.size
                    )
                    for (e in elementPosition.topElements.reversed()) {
                        scoreTop += 1;if (e >= element) break
                    }
                    for (e in elementPosition.bottomElements) {
                        scoreBottom += 1; if (e >= element) break
                    }
                    for (e in elementPosition.leftElements.reversed()) {
                        scoreLeft += 1; if (e >= element) break
                    }
                    for (e in elementPosition.rightElements) {
                        scoreRight += 1; if (e >= element) break
                    }
                    scenicScores.add(scoreTop * scoreBottom * scoreLeft * scoreRight)
                }
            }
        }

        return scenicScores.max()
    }

    check(part1(inputTest) == 21)
    check(part2(inputTest) == 8)

    println(part1(input))
    println(part2(input))
}
