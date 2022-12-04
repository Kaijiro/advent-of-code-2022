package fr.kaijiro.aoc2022.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part1SolverTest {
    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day03/day03_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day03/day03_input.txt").bufferedReader().readText()

    @Test
    fun `it should check the elves inventories find the duplicated items and sum their priorities`(){
        val solver = Day03Solver(examplePuzzleInput)

        val result = solver.solveForPart1()

        Assertions.assertEquals(result, 157)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val solver = Day03Solver(realPuzzleInput)

        val result = solver.solveForPart1()
        println("Result is $result")
        // No assertions, the test should only not crash
    }

}
