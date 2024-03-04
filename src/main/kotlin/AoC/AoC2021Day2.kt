package AoC

import java.io.File

// AoC 2021 day 2 part 1
//Före
val dataDay2: List<String> = File("src/main/kotlin/AoC/day2part1").readLines()

fun day2part1(input:List<String>):Int{

    fun getNumberFromString(input:String):Int{
        val regexPattern = "(\\D+)(\\d+)".toRegex()
        val regExResult = regexPattern.find(input)
        return regExResult!!.destructured.component2().toInt()
    }

    val forward = input.filter { it.contains("forward ") }.map { getNumberFromString(it)}.sum()
    val up = input.filter { it.contains("up ") }.map { getNumberFromString(it)}.sum()
    val down = input.filter { it.contains("down ") }.map { getNumberFromString(it)}.sum()

    return forward * (down - up)
}

//Efter


fun day2part1Updated(input: List<String>): Int {
    //använder en when istället för filter och split istället för regex
    var horizontal = 0
    var depth = 0

    fun splitList(input:String): List<String> {
        return input.split(" ")
    }

    fun direction(list:List<String>){
        when(list.first()){
            "forward" -> horizontal += list.last().toInt()
            "up" -> depth -= list.last().toInt()
            "down" -> depth += list.last().toInt()
        }
    }

    input.forEach { direction(splitList(it)) }

    return horizontal*depth
}
//  inspirationer https://todd.ginsberg.com/post/advent-of-code/2021/day2/
//                  https://github.com/asm0dey/aoc-2021/blob/main/src/Day02.kt

// AoC 2021 day 2 part2
//Före
fun day2part2(input:List<String>):Int{

    fun getNumberFromString(input:String):Int{
        val regexPattern = "(\\D+)(\\d+)".toRegex()
        val regExResult = regexPattern.find(input)
        return regExResult!!.destructured.component2().toInt()
    }

    var horizontal = 0
    var depth = 0
    var aim = 0

    input.forEach{
            s -> when{
        s.contains("forward") -> {
            horizontal += getNumberFromString(s)
            if (aim !=0)
                depth += getNumberFromString(s)*aim
        }
        s.contains("up") -> aim -= getNumberFromString(s)
        s.contains("down") -> aim += getNumberFromString(s)
    }

    }
    return horizontal*depth

}
//Efter
fun day2part2Updated(input: List<String>): Int {
    // använder split för att får värdena istället för regex
    var horizontal = 0
    var depth = 0
    var aim = 0

    fun splitList(input:String): List<String> {
        return input.split(" ")
    }

    fun direction(list:List<String>){
        when(list.first()){
            "forward" -> {
                horizontal += list.last().toInt()
                if (aim != 0)
                    depth += list.last().toInt()*aim
            }
            "up" -> aim -= list.last().toInt()
            "down" -> aim += list.last().toInt()
        }
    }

    input.forEach { direction(splitList(it)) }

    return horizontal*depth
}
//  inspirationer https://todd.ginsberg.com/post/advent-of-code/2021/day2/
//                  https://github.com/asm0dey/aoc-2021/blob/main/src/Day02.kt