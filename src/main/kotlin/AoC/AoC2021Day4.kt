package AoC

import java.io.File

// AoC 2021 day 4 part 1
val dataDay4 = File("src/main/kotlin/AoC/day4part1").readLines()
//Före
fun day4part1(input:List<String>):Int{
    class BingoLott(val number:List<List<String>>, var drawnNumber:List<Int> = listOf()){}

    fun createMatrix(list:List<String>):List<List<String>>{
        val returnList:MutableList<List<String>> = mutableListOf()

        list.forEach{
            (it.split(","))
                .forEach { returnList.add(it
                    .trim()
                    .replace("  "," ")
                    .split(" ")) }}

        return returnList.toList()
    }

    fun checkVerticalRows(ticket:BingoLott, drawnNumbers:List<Int>):Boolean{
        for (index in 0..4){
            if (
                ticket.number.first().indices
                    .map { t -> ticket.number[t][index] }
                    .filter { n -> drawnNumbers.contains(n.toInt()) }.size
                == 5) return true
        }
        return false
    }
    fun checkHorizontalRows(ticket:BingoLott, drawnNumbers:List<Int>):Boolean{
        for (index in 0..4){
            if (
                ticket.number.first().indices
                    .map { t -> ticket.number[index][t] }
                    .filter { n -> drawnNumbers.contains(n.toInt()) }.size
                == 5) return true
        }
        return false
    }

    fun calculateValueOfTicket(ticket:BingoLott):Int{
        var sum = 0
        var minus = 0
        ticket.number
            .forEach{ l ->
                l.forEach { v ->
                    sum += v.toInt()
                    if (ticket.drawnNumber.contains(v.toInt()) )
                        minus += v.toInt() }
            }

        return (sum - minus) * ticket.drawnNumber.last()
    }
    fun drawBingoNumber(list:List<Int>, bingoLottLista: List<BingoLott>):BingoLott{
        list.indices
            .map { range -> list.subList(0,range) }
            .forEach{ drawnNumber -> bingoLottLista
                .forEach{if (checkHorizontalRows(it,drawnNumber) or checkVerticalRows(it,drawnNumber)) {
                    it.drawnNumber = drawnNumber
                    return it
                }} }

        return BingoLott(listOf(listOf()))
    }

    val listOfTickets:MutableList<BingoLott> = mutableListOf()

    val drawnNumbers = input.first().split(",").map { t -> t.toInt() }
    val numberForTickets = input.drop(1).filter { s -> s.isNotEmpty() }

    numberForTickets.windowed(5,5).forEach{listOfTickets.add(BingoLott(createMatrix(it)))}

    return calculateValueOfTicket(drawBingoNumber(drawnNumbers, listOfTickets))

}
//Efter
class day4part1Updated() {

    fun day4part1updated(input:List<String>):Int{
        val drawnNumber:List<Int> = input.first().split(",").map { t -> t.toInt() }
        val allBingoTickets = input.convertToListInt().CreateBingoLotter()

        return drawnNumber.CalculateScore(drawnNumber,allBingoTickets)
    }

    class BingoLott(val number: List<List<Int>>, var winingDrawnNumbers: List<Int> = mutableListOf()) {

        fun checkWiningTicket(drawnNumbers: List<Int>): Boolean {
            return if (number.any {
                        rows -> rows.all { it in drawnNumbers } ||
                        (0..4).any { index -> number.all { row -> row[index] in drawnNumbers } } }){
                if (winingDrawnNumbers.isEmpty()) winingDrawnNumbers = drawnNumbers
                return true
            } else false
        }

    }
    fun List<String>.convertToListInt():List<Int>{
        return this
            .drop(1)
            .filter { it.isNotEmpty() }
            .joinToString(", ")
            .replace(",", "")
            .replace("  "," ")
            .split(" ")
            .map { it.toInt() }
    }

    fun List<Int>.CalculateScore(drawnNumbers: List<Int>,allBingoTickets:List<BingoLott>):Int {

        val returnList = this.indices.flatMap { range ->
            drawnNumbers.take(range + 1).let { numbers ->
                allBingoTickets.filter { it.checkWiningTicket(numbers) }
            }
        }
        val firstWinningBingoLott = returnList.first()
        val sumOfTicket = firstWinningBingoLott.number.flatten().sum()
        val sumOfMarkenNumbers = firstWinningBingoLott.winingDrawnNumbers.filter { it in returnList.first().number.flatten() }.sum()
        val lastDrawnNumber = firstWinningBingoLott.winingDrawnNumbers.last()


        return (sumOfTicket - sumOfMarkenNumbers ) * lastDrawnNumber
    }


    fun List<Int>.CreateBingoLotter(): List<BingoLott> {
        return this.chunked(5).chunked(5).map { BingoLott(it) }
    }

}

// inspiration https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day04.kt
//              https://todd.ginsberg.com/post/advent-of-code/2021/day4/
//              https://github.com/antonarhipov/advent-of-code-2021/blob/main/src/Day04.kt



// AoC 2021 day 4 part 2
//Före
fun day4part2(input:List<String>):Int{
    class BingoLott(val number:List<List<String>>, var drawnNumber:List<Int> = listOf()){}

    fun createMatrix(list:List<String>):List<List<String>>{
        val returnList:MutableList<List<String>> = mutableListOf()

        list.forEach{
            (it.split(","))
                .forEach { returnList.add(it
                    .trim()
                    .replace("  "," ")
                    .split(" ")) }}

        return returnList.toList()
    }

    fun checkVerticalRows(ticket:BingoLott, drawnNumbers:List<Int>):Boolean{
        for (index in 0..4){
            if (
                ticket.number.first().indices
                    .map { t -> ticket.number[t][index] }
                    .filter { n -> drawnNumbers.contains(n.toInt()) }.size
                == 5) return true
        }
        return false
    }
    fun checkHorizontalRows(ticket:BingoLott, drawnNumbers:List<Int>):Boolean{
        for (index in 0..4){
            if (
                ticket.number.first().indices
                    .map { t -> ticket.number[index][t] }
                    .filter { n -> drawnNumbers.contains(n.toInt()) }.size
                == 5) return true
        }
        return false
    }
    fun calculateValueOfTicket(ticket:MutableList<BingoLott>):Int{
        var sum = 0
        var minus = 0
        ticket.last().number
            .forEach{ l ->
                l.forEach { v ->
                    sum += v.toInt()
                    if (ticket.last().drawnNumber.contains(v.toInt()) )
                        minus += v.toInt() }
            }

        return (sum - minus) * ticket.last().drawnNumber.last()
    }
    fun drawBingoNumber(list:List<Int>, bingoLottLista: List<BingoLott>,winningTickets:MutableList<BingoLott> ):MutableList<BingoLott>{
        list.indices
            .map { range -> list.subList(0,range) }
            .forEach{ drawnNumber -> bingoLottLista
                .forEach{if (checkHorizontalRows(it,drawnNumber) or checkVerticalRows(it,drawnNumber)) {
                    if (it.drawnNumber.isEmpty()){
                        it.drawnNumber = drawnNumber
                        winningTickets.add(it)
                    }
                }} }

        return winningTickets
    }

    val listOfTickets:MutableList<BingoLott> = mutableListOf()

    val drawnNumbers = input.first().split(",").map { t -> t.toInt() }
    val numberForTickets = input.drop(1).filter { s -> s.isNotEmpty() }
    val winningTickets:MutableList<BingoLott> = mutableListOf()

    numberForTickets.windowed(5,5).forEach{listOfTickets.add(BingoLott(createMatrix(it)))}

    return calculateValueOfTicket(drawBingoNumber(drawnNumbers, listOfTickets , winningTickets))

}
//Efter
class day4part2Updated() {

    fun day4part2updated(input:List<String>):Int{
        val drawnNumber:List<Int> = input.first().split(",").map { t -> t.toInt() }
        val allBingoTickets = input.convertToListInt().CreateBingoLotter()

        val winningTickets = drawnNumber.filterWinningTickets(allBingoTickets).last()

        return winningTickets.calculateScore()
    }


    class BingoLott(val number: List<List<Int>>, var winingDrawnNumbers: List<Int> = mutableListOf()) {

        fun checkWiningTicket(drawnNumbers: List<Int>): Boolean {
            return number.any { rows -> rows.all { it in drawnNumbers } ||
                    (0..4).any { index -> number.all { row -> row[index] in drawnNumbers } } }
        }


        fun calculateScore():Int{
            var sum = 0
            var minus = 0
            number
                .forEach{ l ->
                    l.forEach { v ->
                        sum += v
                        if (winingDrawnNumbers.contains(v) )
                            minus += v }
                }

            return (sum - minus) * winingDrawnNumbers.last()
        }

    }
    fun List<String>.convertToListInt():List<Int>{
        return this
            .drop(1)
            .filter { it.isNotEmpty() }
            .joinToString(", ")
            .replace(",", "")
            .replace("  "," ")
            .split(" ")
            .map { it.toInt() }
    }

    fun List<Int>.filterWinningTickets(bingoLottLista: List<BingoLott>): Set<BingoLott> {
        var winningTickets:MutableList<BingoLott> = mutableListOf()
        this.indices
            .map { range -> this.subList(0,range) }
            .forEach{ drawnNumber -> bingoLottLista
                .forEach{if (it.checkWiningTicket(drawnNumber)) {
                    if (it.winingDrawnNumbers.isEmpty()){
                        it.winingDrawnNumbers = drawnNumber
                        winningTickets.add(it)
                    }
                }} }

        return winningTickets.toSet()

    }

    fun List<Int>.CreateBingoLotter(): List<BingoLott> {
        return this.chunked(5).chunked(5).map { BingoLott(it) }
    }
}

// inspiration https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day04.kt
//              https://todd.ginsberg.com/post/advent-of-code/2021/day4/
//              https://github.com/antonarhipov/advent-of-code-2021/blob/main/src/Day04.kt