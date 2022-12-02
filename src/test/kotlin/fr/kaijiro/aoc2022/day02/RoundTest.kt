package fr.kaijiro.aoc2022.day02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RoundTest {

    @Test
    fun `the input 'A' & 'X' should instantiate a rock element`(){
        val element1 = RockPaperScissorElement.fromInput("A")
        val element2 = RockPaperScissorElement.fromInput("X")

        Assertions.assertInstanceOf(RockPaperScissorElement.Rock::class.java, element1)
        Assertions.assertInstanceOf(RockPaperScissorElement.Rock::class.java, element2)
    }

    @Test
    fun `the input 'B' & 'Y' should instantiate a rock element`(){
        val element1 = RockPaperScissorElement.fromInput("B")
        val element2 = RockPaperScissorElement.fromInput("Y")

        Assertions.assertInstanceOf(RockPaperScissorElement.Paper::class.java, element1)
        Assertions.assertInstanceOf(RockPaperScissorElement.Paper::class.java, element2)
    }

    @Test
    fun `the input 'C' & 'Z' should instantiate a rock element`(){
        val element1 = RockPaperScissorElement.fromInput("C")
        val element2 = RockPaperScissorElement.fromInput("Z")

        Assertions.assertInstanceOf(RockPaperScissorElement.Scissors::class.java, element1)
        Assertions.assertInstanceOf(RockPaperScissorElement.Scissors::class.java, element2)
    }

    @Test
    fun `when player play rock, it should beat scissors`(){
        val round = Round(RockPaperScissorElement.Scissors(), RockPaperScissorElement.Rock())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.WIN, result)
    }

    @Test
    fun `when player play scissors, it should beat paper`(){
        val round = Round(RockPaperScissorElement.Paper(), RockPaperScissorElement.Scissors())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.WIN, result)
    }

    @Test
    fun `when player play paper, it should beat rock`(){
        val round = Round(RockPaperScissorElement.Rock(), RockPaperScissorElement.Paper())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.WIN, result)
    }

    @Test
    fun `when player play rock, it should draw with rock`(){
        val round = Round(RockPaperScissorElement.Rock(), RockPaperScissorElement.Rock())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.DRAW, result)
    }

    @Test
    fun `when player play scissors, it should draw with scissors`(){
        val round = Round(RockPaperScissorElement.Scissors(), RockPaperScissorElement.Scissors())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.DRAW, result)
    }

    @Test
    fun `when player play paper, it should draw with paper`(){
        val round = Round(RockPaperScissorElement.Paper(), RockPaperScissorElement.Paper())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.DRAW, result)
    }

    @Test
    fun `when player play rock, it should lose to paper`(){
        val round = Round(RockPaperScissorElement.Paper(), RockPaperScissorElement.Rock())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.LOSS, result)
    }

    @Test
    fun `when player play paper, it should lose to scissors`(){
        val round = Round(RockPaperScissorElement.Scissors(), RockPaperScissorElement.Paper())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.LOSS, result)
    }

    @Test
    fun `when player play scissors, it should lose to rock`(){
        val round = Round(RockPaperScissorElement.Rock(), RockPaperScissorElement.Scissors())

        val result = round.resolve()

        Assertions.assertEquals(RoundResult.LOSS, result)
    }

    @Test
    fun `when asked for a draw, player should play the same element as opponent`(){
        val round = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Rock(), RoundResult.DRAW)

        Assertions.assertEquals(RockPaperScissorElement.Rock().javaClass, round.playerPlay.javaClass)
    }

    @Test
    fun `when asked for a loss, player should play the element losing to the opponent element`(){
        val round1 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Rock(), RoundResult.LOSS)
        val round2 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Paper(), RoundResult.LOSS)
        val round3 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Scissors(), RoundResult.LOSS)

        Assertions.assertEquals(RockPaperScissorElement.Scissors().javaClass, round1.playerPlay.javaClass)
        Assertions.assertEquals(RockPaperScissorElement.Rock().javaClass, round2.playerPlay.javaClass)
        Assertions.assertEquals(RockPaperScissorElement.Paper().javaClass, round3.playerPlay.javaClass)
    }

    @Test
    fun `when asked for a win, player should play the element winning to the opponent element`(){
        val round1 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Rock(), RoundResult.WIN)
        val round2 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Paper(), RoundResult.WIN)
        val round3 = Round.buildFromOpponentPlayAndResult(RockPaperScissorElement.Scissors(), RoundResult.WIN)

        Assertions.assertEquals(RockPaperScissorElement.Paper().javaClass, round1.playerPlay.javaClass)
        Assertions.assertEquals(RockPaperScissorElement.Scissors().javaClass, round2.playerPlay.javaClass)
        Assertions.assertEquals(RockPaperScissorElement.Rock().javaClass, round3.playerPlay.javaClass)
    }
}
