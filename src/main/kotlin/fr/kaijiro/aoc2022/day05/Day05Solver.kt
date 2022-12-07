package fr.kaijiro.aoc2022.day05

import java.util.LinkedList
import java.util.stream.Collectors

class Day05Solver(data: String, cratePickingMethod: String) {

    private val stacks = HashMap<Int, LinkedList<String>>()

    init {
        val instructionRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        var hasReversed = false

        data.lines().forEach {
            if(it.startsWith(' ') || it.startsWith('[')){
                // Start situation

                // Not interested with the numbers row
                if(it.trim().startsWith("1")){
                    return@forEach
                }

                it.chunked(4).forEachIndexed { index, part ->
                    if(part.trim().isNotEmpty()){
                        if (!stacks.containsKey(index + 1)) {
                            stacks[index + 1] = LinkedList<String>()
                        }

                        stacks[index + 1]?.add(part.trim())
                    }
                }
            } else if(it.isEmpty()){
                // Start situation is over, instructions are coming
                // But first, let's reverse all LinkedList, so they look like a stack

                // Let's just not reverse the lists again once we reach the end of the file
                if(!hasReversed){
                    stacks.mapValuesTo(stacks) { mapEntry -> LinkedList(mapEntry.value.reversed()) }
                    hasReversed = true
                }
            } else {
                // Instructions
                if(instructionRegex.matches(it)){
                    val (_, quantity, originStackIndex, destinationStackIndex) = instructionRegex.find(it)!!.groupValues

                    println("Moving $quantity crates from stack n°$originStackIndex to stack n°$destinationStackIndex")

                    if(cratePickingMethod == "single"){
                        for(i in 1..quantity.toInt()){
                            val movedElement = stacks[originStackIndex.toInt()]!!.pollLast()
                            stacks[destinationStackIndex.toInt()]!!.add(movedElement)
                        }
                    } else if(cratePickingMethod == "many"){
                        val originStack = stacks[originStackIndex.toInt()]!!
                        val destinationStack = stacks[destinationStackIndex.toInt()]!!

                        val movedElements = originStack.slice((originStack.size - quantity.toInt()) until (originStack.size))
                        destinationStack.addAll(movedElements)

                        stacks[originStackIndex.toInt()] = LinkedList(originStack.dropLast(quantity.toInt()))
                    }
                }
            }
        }
    }

    fun solve(): String {
        println(stacks)

        return this.stacks.values.map { it.last().trim('[', ']') }.stream().collect(Collectors.joining())
    }
}