package fr.kaijiro.aoc2022.day04

class Day04Solver(data: String) {

    private val assignments: List<Pair<Assignment, Assignment>> = data.lines().filter { it != "" }.map {
        val (firstAssignmentInput, secondAssignmentInput) = it.split(",")

        val (startSector1, lastSector1) = firstAssignmentInput.split("-")
        val (startSector2, lastSector2) = secondAssignmentInput.split("-")

        return@map Pair(Assignment(startSector1.toInt(), lastSector1.toInt()), Assignment(startSector2.toInt(), lastSector2.toInt()))
    }

    fun solveForPart1(): Int {
        return this.assignments.count {
            val (firstAssignment, secondAssignment) = it

            if(firstAssignment.totalOverlap(secondAssignment) || secondAssignment.totalOverlap(firstAssignment)){
                println("Found : ${firstAssignment.start}-${firstAssignment.end} / ${secondAssignment.start}-${secondAssignment.end}")
            }

            return@count firstAssignment.totalOverlap(secondAssignment) || secondAssignment.totalOverlap(firstAssignment)
        }
    }

    fun solveForPart2(): Int {
        return this.assignments.count {
            val (firstAssignment, secondAssignment) = it

            if(firstAssignment.singleSectorOverlap(secondAssignment) || secondAssignment.singleSectorOverlap(firstAssignment)){
                println("Found : ${firstAssignment.start}-${firstAssignment.end} / ${secondAssignment.start}-${secondAssignment.end}")
            }

            return@count firstAssignment.singleSectorOverlap(secondAssignment) || secondAssignment.singleSectorOverlap(firstAssignment)
        }
    }
}

private class Assignment(val start: Int, val end: Int){
    fun totalOverlap(otherAssignment: Assignment): Boolean {
        return otherAssignment.start in this.start..this.end
                && otherAssignment.end in this.start..this.end
    }

    fun singleSectorOverlap(otherAssignment: Assignment): Boolean {
        return otherAssignment.start in this.start..this.end
                || otherAssignment.end in this.start..this.end
    }
}
