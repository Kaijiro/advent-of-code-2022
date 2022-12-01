package fr.kaijiro.aoc2022.day01

class Day01Solver(data: String) {

    private val caloriesForEachElf: ArrayList<IntArray> = ArrayList()

    init {
        val elements = data.split(Regex("\r\n|\n"))
        var buffer: ArrayList<Int> = ArrayList()
        elements.forEach {
            println("Element is $it")

            if(it != ""){
                buffer.add(Integer.parseInt(it))
            } else {
                caloriesForEachElf.add(buffer.toIntArray())
                buffer = ArrayList()
            }
        }
    }

    fun solvePart1(): Int = caloriesForEachElf.maxOf { it.sum() }

    fun solvePart2(): Int = caloriesForEachElf.map { it.sum() }
        .sorted()
        .takeLast(3)
        .sum()
}