package fr.kaijiro.aoc2022.day04

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part1SolverTest {

    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day04/day04_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day04/day04_input.txt").bufferedReader().readText()

    @Test
    fun `it should count how many complete overlapping affectations exists`(){
        val solver = Day04Solver(examplePuzzleInput)

        val result = solver.solveForPart1()

        Assertions.assertEquals(2, result)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val solver = Day04Solver(realPuzzleInput)

        val result = solver.solveForPart1()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}