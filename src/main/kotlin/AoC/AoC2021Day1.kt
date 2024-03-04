package AoC

import java.io.File

// AoC 2021 day 1 a
val data1a: List<String> = File("src/main/kotlin/AoC/day1a").readLines()
val dataDay1 = data1a.map { t -> t.toInt() }
//Före

fun day1part1(input:List<Int>):Int{

    tailrec fun iterate(input:List<Int>, amount:Int=0, index:Int=1):Int{
        return if (index == input.size){
            amount
        } else
            if (input[index] > input[index-1]){
                iterate(input,amount+1,index+1)
            } else
                iterate(input,amount,index+1)
    }

    return iterate(input)
}


//Efter

fun day1part1updated(inputList:List<Int>):Int{
    return inputList.zipWithNext().count{it.first < it.second}
}
// Jag kollade framförallt på https://todd.ginsberg.com/post/advent-of-code/2021/day1/
// Då denna kod kunde göras så simpel så var det svårt att undvika att inte "kopiera" just den här
// men jag testade igenom och lärde mig .zipWithNext()


// AoC 2021 day 1 part 2

//Före
fun day1Part2(input:List<String>):Int{
    val data: List<Int> = input.map { n -> n.toInt() }

    tailrec fun iterate(index:Int=0,range:Int=2, temp:MutableList<Int> = mutableListOf()): MutableList<Int>{
        return if (index+range == data.size)
            temp
        else{
            temp += data.filterIndexed { i, _ -> i in index..index+range }.sum()
            iterate(index+1,temp=temp)
        }
    }
    return day1part1(iterate())
}


//Efter
fun day1part2updated(inputList:List<Int>):Int{
    return inputList.windowed(3,1){it.sum()}.zipWithNext().count(){it.first < it.second}
}
// Jag kollade framförallt på https://todd.ginsberg.com/post/advent-of-code/2021/day1/
// Då denna kod kunde göras så simpel så var det svårt att undvika att inte "kopiera" just den här
// men jag testade igenom och lärde mig .windowed vilket är en funktion som jag verkligen gillar!