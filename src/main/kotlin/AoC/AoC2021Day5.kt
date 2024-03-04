package AoC

import java.io.File
import kotlin.math.absoluteValue

// AoC 2021 day 5 part 1
//Före
val dataDay5 = File("src/main/kotlin/AoC/day5part1").readLines()
class Day5Part1(){
    fun day5part1(input: List<String>): Int {
        val listOfAllCoordinatesFromPoints = listOfPoint(input)
            .map { it.getAllPointsCowered(false) }
            .filter { it.isNotEmpty() }
            .flatten()


        return listOfAllCoordinatesFromPoints
            .groupBy { it }
            .map { it.value.size }
            .count{ it > 1}
    }
    fun day5part2(input: List<String>): Int {
        val listOfAllCoordinatesFromPoints = listOfPoint(input)
            .map { it.getAllPointsCowered(true) }
            .filter { it.isNotEmpty() }
            .flatten()


        return listOfAllCoordinatesFromPoints
            .groupBy { it }
            .map { it.value.size }
            .count{ it > 1}
    }

    class Point(val x1:Int, val y1:Int, val x2:Int, val y2:Int ){


        //funktion för del 2
        private fun calculateDiagonalPoints(maxRange: Int, minRange: Int, startingX: Int,startingY: Int, direction:Int): List<String> {
            val temp:MutableList<String> = mutableListOf()
            for (number in 0..maxRange-minRange) {
                when (direction){
                    1 -> temp += "${startingX - number},${startingY - number}"
                    2 -> temp += "${startingX - number},${startingY + number}"
                    3 -> temp += "${startingX + number},${startingY - number}"
                    4 -> temp += "${startingX + number},${startingY + number}"
                }
            }
            return temp.toList()
        }

        fun getAllPointsCowered(includeDiagonalLines:Boolean):List<String>{
            return when {
                (x1 == x2) -> calculateAllStraightPoints(x1, y1, y2, true)
                (y1 == y2) -> calculateAllStraightPoints(y1, x1, x2, false)
                else -> // kod för part 2
                    if (includeDiagonalLines) {
                        when {
                            (x1 > x2 && y1 > y2) -> return calculateDiagonalPoints(x1,x2,x1,y1,1)
                            (x1 > x2 && y1 < y2) -> return calculateDiagonalPoints(x1,x2,x1,y1,2)
                            (x1 < x2 && y1 > y2) -> return calculateDiagonalPoints(y1,y2,x1,y1,3)
                            else -> return calculateDiagonalPoints(y2,y1,x1,y1,4)
                        }
                    } else listOf()
            }
        }


        private fun calculateAllStraightPoints(constant:Int,n1:Int,n2:Int,constantIsX:Boolean):List<String>{
            val temp:MutableList<String> = mutableListOf()
            for (coordinate  in Integer.min(n1,n2)..Integer.max(n1,n2)){
                if (constantIsX) temp += ("$constant,$coordinate") else temp += ("$coordinate,$constant")
            }
            return temp.toList()
        }
    }

    fun listOfPoint(inputData:List<String>): List<Point> {
        val allPoint: MutableList<Point> = mutableListOf()
        fun getCoordinates(list:String):List<String>{
            return list.split(",")
        }

        fun createPoint(number:List<String>): Point {
            return Point(number[0].toInt(),number[1].toInt(),number[2].toInt(),number[3].toInt())
        }

        val temp = inputData
            .map { it.replace(" -> ", ",") }
            .map { it.split(" ") }

        temp.forEach { allPoint.add(createPoint(getCoordinates(it[0]))) }


        return allPoint.toList()
    }


}
//Efter
class Day5(){

    fun day5part1updated(input: List<String>): Int{

        val listOfPoints = input.map { pairCoordinates(it) }.filterDiagonalLines()

        return listOfPoints.amountOfCrossingLines()
    }

    fun day5part2updated(input: List<String>): Int {

        val listOfPoints = input.map { pairCoordinates(it) }

        return listOfPoints.amountOfCrossingLines()


    }


    class Point(val x:Int, val y:Int){}

    fun List<Pair<Point,Point>>.amountOfCrossingLines():Int{
        return this.flatMap {  getCoordinatesInLine(it) }.groupBy { it }.filter { it.value.size > 1 }.size
    }
    fun List<Pair<Point,Point>>.filterDiagonalLines(): List<Pair<Point, Point>> {
        return this.filter { (point1, point2) ->
            point1.x == point2.x || point1.y == point2.y }
    }


    fun pairCoordinates(input:String): Pair<Point, Point> {
        val temp = input.split(" -> ")
        val (firstX, firstY) = temp[0].split(",").map { it.trim().toInt() }
        val (secondX, secondY) = temp[1].split(",").map { it.trim().toInt() }

        return Pair(Point(firstX, firstY), Point(secondX, secondY))
    }

    fun getCoordinatesInLine(pair: Pair<Point, Point>): List<String> {
        //Bresenham's Line Algorithm för att hitta sträckan mellan två punkter
        val coordinates = mutableListOf<String>()

        var x1 = pair.first.x
        var y1 = pair.first.y

        val x2 = pair.second.x
        val y2 = pair.second.y

        val dx = (x2 - x1).absoluteValue
        val dy = (y2 - y1).absoluteValue
        val sx = if (x1 < x2) 1 else -1
        val sy = if (y1 < y2) 1 else -1
        var err = dx - dy

        while (true) {
            coordinates.add("($x1,$y1)")

            if (x1 == x2 && y1 == y2) break

            val e2 = 2 * err
            if (e2 > -dy) {
                err -= dy
                x1 += sx
            }
            if (e2 < dx) {
                err += dx
                y1 += sy
            }
        }

        return coordinates.toList()
    }

}


// inspiration https://www.ericburden.work/blog/2021/12/06/advent-of-code-2021-day-5/
//              https://todd.ginsberg.com/post/advent-of-code/2021/day5/
//              https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day05.kt

// AoC 2021 day 5 part 2 lösningen finns i part1

//Före lösningen för part 2 finns inne i day 1. En funktion + extra kod i getAllPoints funktionen

//Efter lösningen finns i Day5()