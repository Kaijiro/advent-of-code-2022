package fr.kaijiro.aoc2022.day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Part2SolverTest {

    private val examplePuzzleInput = this::class.java.getResourceAsStream("/day01/day01_example.txt").bufferedReader().readText()
    private val realPuzzleInput = this::class.java.getResourceAsStream("/day01/day01_input.txt").bufferedReader().readText()

    @Test
    fun `it should give the most calories carried by the top 3 elves`(){
        val part1Solver = Day01Solver(examplePuzzleInput)

        val result = part1Solver.solvePart2()

        val caloriesExpected = 45000
        Assertions.assertEquals(caloriesExpected, result)
    }

    @Test
    fun `it should solve the original puzzle`(){
        val part1Solver = Day01Solver(realPuzzleInput)

        val result = part1Solver.solvePart2()
        println("Result is $result")
        // No assertions, the test should only not crash
    }
}