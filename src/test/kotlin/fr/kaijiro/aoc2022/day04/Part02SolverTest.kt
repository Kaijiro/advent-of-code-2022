package fr.kaijiro.aoc2022.day04

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part02SolverTest {

    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day04/day04_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day04/day04_input.txt").bufferedReader().readText()

    @Test
    fun `it should count how many overlapping affectations exists`(){
        val solver = Day04Solver(examplePuzzleInput)

        val result = solver.solveForPart2()

        Assertions.assertEquals(4, result)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val solver = Day04Solver(realPuzzleInput)

        val result = solver.solveForPart2()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}