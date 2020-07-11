package sample.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

public class ExprTest {
    fun execute(tokens: List<String>): Int {
        return Expr(tokens).main()
    }

    @Test
    fun test_isNumber_positive(){
        val sut = Expr(listOf())
        assertEquals(
            true,
            sut.isNumber("123")
        )
    }

    @Test
    fun test_isNumber_negative(){
        val sut = Expr(listOf())
        assertEquals(
            true,
            sut.isNumber("-123")
        )
    }

    @Test
    fun test_isNumber_not_a_number(){
        val sut = Expr(listOf())
        assertEquals(
            false,
            sut.isNumber("-123a")
        )
    }

    @Test
    fun test_mult(){
        assertEquals(
            6,
            execute(
                listOf("2", "*", "3")
            )
        )
    }

    @Test
    fun test_div(){
        assertEquals(
            2,
            execute(
                listOf("6", "/", "3")
            )
        )
    }

    @Test
    fun test_mod(){
        assertEquals(
            2,
            execute(
                listOf("8", "%", "3")
            )
        )
    }

    @Test
    fun test_add(){
        assertEquals(
            5,
            execute(
                listOf("2", "+", "3")
            )
        )
    }

    @Test
    fun test_sub(){
        assertEquals(
            2,
            execute(
                listOf("5", "-", "3")
            )
        )
    }

    @Test
    fun test_left_join(){
        assertEquals(
            7,
            execute(
                listOf("10", "-", "2", "-", "1")
            )
        )
    }

    @Test
    fun test_number_with_paren(){
        assertEquals(
            1,
            execute(
                listOf("(", "1", ")")
            )
        )
    }

    @Test
    fun test_01(){
        assertEquals(
            21,
            execute(
                listOf("(", "1", "+", "2", ")", "*", "(", "3", "+", "4", ")")
            )
        )
    }

    @Test
    fun test_02(){
        assertEquals(
            2,
            execute(
                listOf("(", "9", "-", "3", ")", "/", "(", "4", "-", "1", ")")
            )
        )
    }
}
