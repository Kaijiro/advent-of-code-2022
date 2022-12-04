package fr.kaijiro.aoc2022.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part2SolverTest {
    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day03/day03_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day03/day03_input.txt").bufferedReader().readText()

    @Test
    fun `it should check the elves inventories find the duplicated items and sum their priorities`(){
        val solver = Day03Solver(examplePuzzleInput)

        val result = solver.solveForPart2()

        Assertions.assertEquals(result, 70)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val solver = Day03Solver(realPuzzleInput)

        val result = solver.solveForPart2()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}