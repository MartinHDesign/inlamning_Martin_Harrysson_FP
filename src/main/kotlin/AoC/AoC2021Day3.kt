package AoC

import java.io.File

// AoC 2021 day 3 part 1
//Före
val dataDay3 = File("src/main/kotlin/AoC/day3part1").readLines()

fun day3part1(input: List<String>):Int{

    fun zeroOrOne(index:Int):String{
        return if (input.filter { s -> s[index].equals('1') }.count() > input.size/2) "1" else "0"
    }

    fun oneOrZero(index:Int):String{
        return if (input.filter { s -> s[index].equals('1') }.count() > input.size/2) "0" else "1"
    }

    fun binary(x:Int):String{
        var temp = ""
        for (i in 0..input.first().length-1){
            temp += if (x == 1) zeroOrOne(i) else oneOrZero(i)
        }
        return temp
    }
    val gamma = binary(1).toInt(2)
    val epsilon = binary(0).toInt(2)

    return gamma*epsilon

}
//Efter
fun day3part1Updated(input: List<String>):Int{
    // input.first() ger första strängen i listan. indices ger en range av strängen tex "01234".indices= 0..4
    // indices används istället för en for loop for(i 0..4) på en lista. Ersätter for loopen i min första lösning

    val gamma =
        input.first().indices
            .map { index -> if (input.count { it[index].equals('1') } > input.size/2)  '1' else '0'}
            .joinToString("")

    val epsilon =
        input.first().indices
            .map { index -> if (input.count { it[index].equals('1') } > input.size/2)  '0' else '1'}
            .joinToString("")

    return gamma.toInt(2) * epsilon.toInt(2)

}
// inspiration https://blog.jetbrains.com/kotlin/2021/12/advent-of-code-2021-in-kotlin-day-3/
//              https://todd.ginsberg.com/post/advent-of-code/2021/day3/
//              https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day03.kt

// AoC 2021 day 3 part 2
//Före
fun day3part2(input: List<String>):Int{

    fun numberOneDominant(list:MutableList<String>, index:Int):String{
        return if (
            list.filter { s -> s[index].equals('1') }.count()
            == list.filter { s -> s[index].equals('0') }.count()) "1"
        else if (list.filter { s -> s[index].equals('1') }.count() > list.size/2) "1"
        else "0"
    }

    fun numberZeroDominant(list:MutableList<String>, index:Int):String{
        return  if (list.filter { s -> s[index].equals('1') }.count()
            == list.filter { s -> s[index].equals('0') }.count()) "0"
        else if (list.filter { s -> s[index].equals('1') }.count() > list.size/2) "0"
        else "1"
    }


    fun findOxygenValue():String{

        tailrec fun iterate(list: MutableList<String>, index:Int = 0):MutableList<String>{
            val temp: MutableList<String> = when (numberOneDominant(list,index)){
                "1" -> list.filter { s -> s[index].equals('1') }.toMutableList()
                "0" -> list.filter { s -> s[index].equals('0') }.toMutableList()
                else -> list
            }
            return if (temp.size == 1) temp else iterate(temp, index+1)
        }
        return iterate(input.toMutableList()).first()
    }

    fun findCO2Value():String{

        tailrec fun iterate(list: MutableList<String>, index:Int = 0):MutableList<String>{
            val temp: MutableList<String> = when (numberZeroDominant(list,index)){
                "1" -> list.filter { s -> s[index].equals('1') }.toMutableList()
                "0" -> list.filter { s -> s[index].equals('0') }.toMutableList()
                else -> list
            }
            return if (temp.size == 1) temp else iterate(temp, index+1)
        }
        return iterate(input.toMutableList()).first()
    }


    return findOxygenValue().toInt(2) * findCO2Value().toInt(2)
}
//Efter
class day3() {

    fun day3part2Updated(list: List<String>): Int {
        val oxygen = list.filterDominantNumber("1","0")
        val CO2 = list.filterDominantNumber("0","1")

        return oxygen * CO2
    }

    private fun List<String>.filterZeroOrOne(moreOf:String, lessOf:String, index:Int):String{
        return  if (this.filter { s -> s[index].equals('1') }.count()
            == this.filter { s -> s[index].equals('0') }.count()) moreOf
        else if (this.filter { s -> s[index].equals('1') }.count() > this.size/2) moreOf
        else lessOf
    }

    private fun List<String>.filterDominantNumber(moreOf:String, lessOf:String):Int{

        tailrec fun iterate(list: MutableList<String>, index:Int = 0):MutableList<String>{
            val temp: MutableList<String> = when (list.filterZeroOrOne(moreOf,lessOf,index)){
                "1" -> list.filter { s -> s[index].equals('1') }.toMutableList()
                "0" -> list.filter { s -> s[index].equals('0') }.toMutableList()
                else -> list
            }
            return if (temp.size == 1) temp else iterate(temp, index+1)
        }
        return iterate(this.toMutableList()).first().toInt(2)
    }
}

// inspiration https://blog.jetbrains.com/kotlin/2021/12/advent-of-code-2021-in-kotlin-day-3/
//              https://todd.ginsberg.com/post/advent-of-code/2021/day3/
//              https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day03.kt