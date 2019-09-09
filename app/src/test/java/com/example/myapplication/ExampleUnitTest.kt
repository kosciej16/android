package com.example.myapplication

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import org.junit.Test

import org.junit.Assert.*
import java.io.StringReader

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


data class Cos(val abc: String) {
}
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val str = """cosik = '[{"places": [{"abc": "0"}, {"abc": "1"}]}]' """
        val r = Regex("cosik = '(.*)'")
        val match = r.find(str)
        val c = match?.groups?.get(1)?.value
        println(c)
//        val parsed = Klaxon().parseArray<JsonObject>(StringReader(c))
        val parsed = Klaxon().parseJsonArray(StringReader(c))
        println(parsed)
//        parsed.forEach{println(it.toString())}
        val tmp = parsed["places"][0] as JsonArray<JsonObject>
        tmp.forEach {
            println(it.get("abc"))
        }
//        println(tmp)
//        tmp.forEach{println(it.toString())}
//        val parser: Parser = Parser.default()
//        val array = parser.parse(c!!) as JsonArray<JsonObject>

//        val result = parsed?.map {
//            it.array<Cos>("places")
//        }
//        println(result)
//        print(tmp)
//        for (cos in parsed) {
//           println(cos)
//        }
        val som = Klaxon().parseFromJsonArray<Cos>(tmp)
        println(som)
        //        val parsed = Klaxon().parseArray<Cos>(StringReader(c))
    }

    @Test
    fun funny() {
        val list = "".split(',')
        assert(list.size == 1)

    }
}
