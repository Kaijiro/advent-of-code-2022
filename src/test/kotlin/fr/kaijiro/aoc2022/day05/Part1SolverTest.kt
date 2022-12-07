package fr.kaijiro.aoc2022.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part1SolverTest {

    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day05/day05_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day05/day05_input.txt").bufferedReader().readText()

    @Test
    fun `it should give each crate on top of each row after the procedure is complete`(){
        val solver = Day05Solver(examplePuzzleInput, "single")

        val result = solver.solve()

        Assertions.assertEquals("CMZ", result)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val solver = Day05Solver(realPuzzleInput, "single")

        val result = solver.solve()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}