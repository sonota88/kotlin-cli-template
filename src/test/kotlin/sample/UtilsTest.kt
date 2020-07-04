package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

public class UtilsTest {
    @Test
    fun inspectNull(){
        assertEquals(
            "null",
            Utils.inspect(null)
        )
    }

    @Test
    fun inspectInt(){
        assertEquals(
            "123",
            Utils.inspect(123)
        )
    }

    @Test
    fun inspectString(){
        val dq = "\""

        assertEquals(
            dq + "fdsa" + dq,
            Utils.inspect("fdsa")
        )
    }
}
