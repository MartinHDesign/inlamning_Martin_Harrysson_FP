package AoC

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class AoC2021KtTest {

    @Test
    fun day1part1Test() {
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day1a").readLines()
        val dataInt = data.map { t -> t.toInt() }
        val expectedValue = 7
        val unexpectedValue = 5

        val actualValue = day1part1(dataInt)
        assertEquals(expectedValue, actualValue)
        assertNotEquals(unexpectedValue,actualValue)
    }
    @Test
    fun day1part1UpdatedTest() {
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day1a").readLines()
        val dataInt = data.map { t -> t.toInt() }
        val expectedValue = 7
        val unexpectedValue = 5

        val actualValue = day1part1updated(dataInt)
        assertEquals(expectedValue, actualValue)
        assertNotEquals(unexpectedValue,actualValue)
    }

    @Test
    fun day1part2Test() {
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day1a").readLines()

        val expectedValue = 5
        val actualValue = day1Part2(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day1part2UpdatedTest() {
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day1a").readLines()

        val expectedValue = 5
        val actualValue = day1part2updated(data.map { t -> t.toInt() })

        assertEquals(expectedValue,actualValue)
    }


    @Test
    fun day2part1Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day2part1").readLines()

        val expectedValue = 150
        val actualValue = day2part1(data)

        assertEquals(expectedValue,actualValue)

    }
    @Test
    fun day2part1UpdatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day2part1").readLines()

        val expectedValue = 150
        val actualValue = day2part1Updated(data)

        assertEquals(expectedValue,actualValue)

    }

    @Test
    fun day2part2Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day2part1").readLines()

        val expectedValue = 900
        val actualValue = day2part2(data)

        assertEquals(expectedValue,actualValue)

    }

    @Test
    fun day2part2UpdatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day2part1").readLines()

        val expectedValue = 900
        val actualValue = day2part2Updated(data)

        assertEquals(expectedValue,actualValue)

    }

    @Test
    fun day3part1Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day3part1").readLines()
        val expectedValue = 198
        val actualValue = day3part1(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day3part1UpdatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day3part1").readLines()
        val expectedValue = 198
        val actualValue = day3part1Updated(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day3part2Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day3part1").readLines()
        val expectedValue = 230
        val actualValue = day3part2(data)

        assertEquals(expectedValue,actualValue)
    }


    @Test
    fun day3part2UpdatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day3part1").readLines()
        val expectedValue = 230
        val actualValue = day3().day3part2Updated(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day4part1Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day4part1").readLines()
        val expectedValue = 4512
        val actualValue = day4part1(data)

        assertEquals(expectedValue,actualValue)
    }
    @Test
    fun day4part1UpdatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day4part1").readLines()
        val expectedValue = 4512
        val actualValue = day4part1Updated().day4part1updated(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day4part2Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day4part1").readLines()
        val expectedValue = 1924
        val actualValue = day4part2(data)

        assertEquals(expectedValue,actualValue)
    }
    @Test
    fun day4part2updatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day4part1").readLines()
        val expectedValue = 1924
        val actualValue = day4part2Updated().day4part2updated(data)

        assertEquals(expectedValue,actualValue)
    }

    @Test
    fun day5part1Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day5part1").readLines()
        val expectedValue = 5
        val actualValue = Day5Part1().day5part1(data)

        assertEquals(expectedValue,actualValue)
    }
    @Test
    fun day5part1updatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day5part1").readLines()
        val expectedValue = 5
        val actualValue = Day5().day5part1updated(data)

        assertEquals(expectedValue,actualValue)
    }
    @Test
    fun day5part2Test(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day5part1").readLines()
        val expectedValue = 12
        val actualValue = Day5Part1().day5part2(data)

        assertEquals(expectedValue,actualValue)
    }
    @Test
    fun day5part2updatedTest(){
        val data:List<String> = File("src/test/kotlin/AoC/TestData/day5part1").readLines()
        val expectedValue = 12
        val actualValue = Day5().day5part2updated(data)

        assertEquals(expectedValue,actualValue)
    }
}