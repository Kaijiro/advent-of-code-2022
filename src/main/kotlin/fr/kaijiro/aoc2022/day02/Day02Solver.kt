package fr.kaijiro.aoc2022.day02

class Day02Solver(private val rounds: List<Round>) {

    fun solve(): Int {
        return rounds.sumOf { it.resolve().points } + rounds.sumOf { it.playerPlay.points }
    }

    companion object {
        fun initForPart1(data: String): Day02Solver {
            val rounds = data.lines().filter { it != "" }.map {
                val (opponentInput, playerInput) = it.split(' ')
                val opponentPlay = RockPaperScissorElement.fromInput(opponentInput)
                val playerPlay = RockPaperScissorElement.fromInput(playerInput)

                return@map Round(opponentPlay, playerPlay)
            }

            return Day02Solver(rounds)
        }

        fun initForPart2(data: String): Day02Solver {

            val rounds = data.lines().filter { it != "" }.map {
                val (opponentInput, roundResultInput) = it.split(' ')
                val opponentPlay = RockPaperScissorElement.fromInput(opponentInput)
                val roundResult = RoundResult.fromInput(roundResultInput)

                return@map Round.buildFromOpponentPlayAndResult(opponentPlay, roundResult)
            }

            return Day02Solver(rounds)
        }
    }
}

class Round(private val opponentPlay: RockPaperScissorElement, val playerPlay: RockPaperScissorElement) {
    fun resolve(): RoundResult {
        if(playerPlay.loseTo(opponentPlay)) return RoundResult.LOSS
        else if(playerPlay.drawTo(opponentPlay)) return RoundResult.DRAW
        else if(playerPlay.winTo(opponentPlay)) return RoundResult.WIN

        throw Exception("Unexpected situation $playerPlay, $opponentPlay")
    }

    companion object {
        fun buildFromOpponentPlayAndResult(opponentPlay: RockPaperScissorElement, result: RoundResult): Round {
            return when (result) {
                RoundResult.DRAW -> Round(opponentPlay, opponentPlay)
                RoundResult.LOSS -> Round(opponentPlay, opponentPlay.winTo())
                RoundResult.WIN -> Round(opponentPlay, opponentPlay.loseTo())
                else -> throw Exception("Smelly smelly ...")
            }
        }
    }
}

class RoundResult(val points: Int) {

    companion object {
        val WIN = RoundResult(6)
        val DRAW = RoundResult(3)
        val LOSS = RoundResult(0)

        fun fromInput(input: String): RoundResult {
            return when (input) {
                "X" -> LOSS
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw Exception("Invalid input $input")
            }
        }
    }
}

abstract class RockPaperScissorElement {
    companion object {

        private val rock = Rock()
        private val paper = Paper()
        private val scissors = Scissors()

        fun fromInput(input: String): RockPaperScissorElement {
            when (input) {
                in rock.acceptedInputs -> return rock
                in paper.acceptedInputs -> return paper
                in scissors.acceptedInputs -> return scissors
            }

            throw Exception("What the fuck are you doing ?")
        }
    }

    abstract val points: Int
    abstract val acceptedInputs: Array<String>

    abstract fun loseTo(): RockPaperScissorElement
    abstract fun drawTo(): RockPaperScissorElement
    abstract fun winTo(): RockPaperScissorElement

    abstract fun loseTo(other: RockPaperScissorElement): Boolean
    abstract fun drawTo(other: RockPaperScissorElement): Boolean
    abstract fun winTo(other: RockPaperScissorElement): Boolean

    class Rock : RockPaperScissorElement() {
        override val points: Int
            get() = 1
        override val acceptedInputs: Array<String>
            get() = arrayOf("A", "X")

        override fun loseTo(): RockPaperScissorElement = Paper()
        override fun loseTo(other: RockPaperScissorElement): Boolean = this.loseTo().javaClass == other.javaClass

        override fun drawTo(): RockPaperScissorElement = this
        override fun drawTo(other: RockPaperScissorElement): Boolean = this.drawTo().javaClass == other.javaClass

        override fun winTo(): RockPaperScissorElement = Scissors()
        override fun winTo(other: RockPaperScissorElement): Boolean = this.winTo().javaClass == other.javaClass
    }

    class Paper : RockPaperScissorElement() {
        override val points: Int
            get() = 2
        override val acceptedInputs: Array<String>
            get() = arrayOf("B", "Y")

        override fun loseTo(): RockPaperScissorElement = Scissors()
        override fun loseTo(other: RockPaperScissorElement): Boolean = this.loseTo().javaClass == other.javaClass

        override fun drawTo(): RockPaperScissorElement = this
        override fun drawTo(other: RockPaperScissorElement): Boolean = this.drawTo().javaClass == other.javaClass

        override fun winTo(): RockPaperScissorElement = Rock()
        override fun winTo(other: RockPaperScissorElement): Boolean = this.winTo().javaClass == other.javaClass
    }

    class Scissors : RockPaperScissorElement() {
        override val points: Int
            get() = 3
        override val acceptedInputs: Array<String>
            get() = arrayOf("C", "Z")

        override fun loseTo(): RockPaperScissorElement = Rock()
        override fun loseTo(other: RockPaperScissorElement): Boolean = this.loseTo().javaClass == other.javaClass

        override fun drawTo(): RockPaperScissorElement = this
        override fun drawTo(other: RockPaperScissorElement): Boolean = this.drawTo().javaClass == other.javaClass

        override fun winTo(): RockPaperScissorElement = Paper()
        override fun winTo(other: RockPaperScissorElement): Boolean = this.winTo().javaClass == other.javaClass
    }
}
