package fr.kaijiro.aoc2022.day03

class Day03Solver(data: String) {

    private val inventories: ArrayList<Pair<String, String>> = ArrayList()

    init {
        data.lines().filter { it != "" }.map {
            val entryLength = it.length
            val firstCompartment = it.slice(0 until entryLength / 2)
            val secondCompartment = it.slice(entryLength / 2 until entryLength)

            inventories.add(Pair(firstCompartment, secondCompartment))
        }
    }

    fun solveForPart1(): Int {
        return inventories.sumOf { inventory ->
            val duplicate = inventory.first.filter { inventory.second.contains(it) }[0]

            val itemPriority = computeItemPriority(duplicate)

            println("Item is $duplicate, priority is $itemPriority")

            return@sumOf itemPriority
        }
    }

    fun solveForPart2(): Int {
        val groups = ArrayList<Triple<String, String, String>>()
        var buffer1 = ""
        var buffer2 = ""

        inventories.forEachIndexed { index, pair ->
            if((index + 1) % 3 == 1) buffer1 = pair.first + pair.second
            else if((index + 1) % 3 == 2) buffer2 = pair.first + pair.second
            else if((index + 1) % 3 == 0) groups.add(Triple(buffer1, buffer2, pair.first + pair.second))
        }

        return groups.map {
            val (first, second, third) = it
            println("First group is $it")

            val groupBadge = first.filter { char -> second.contains(char) }.filter { char -> third.contains(char) }[0]

            return@map groupBadge
        }.sumOf { computeItemPriority(it) }
    }

    private fun computeItemPriority(itemCode: Char): Int {
        return if(itemCode.isLowerCase()) itemCode.code - 'a'.code + 1
        else itemCode.code - 'A'.code + 27
    }
}
