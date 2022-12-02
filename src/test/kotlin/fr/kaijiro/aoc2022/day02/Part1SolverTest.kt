package fr.kaijiro.aoc2022.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part1SolverTest {

    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day02/day02_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day02/day02_input.txt").bufferedReader().readText()

    @Test
    fun `it should sum the point of the rock paper scissors rounds`(){
        val day02Solver = Day02Solver.initForPart1(examplePuzzleInput)

        val result = day02Solver.solve()

        Assertions.assertEquals(15, result)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val day02Solver = Day02Solver.initForPart1(realPuzzleInput)

        val result = day02Solver.solve()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}