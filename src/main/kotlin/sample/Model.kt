package sample

import java.nio.charset.StandardCharsets
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Model {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun cat() {
        while (true) {
            val line : String? = readLine()
            if (line == null) {
                break
            }
            Utils.puts(line)
        }
    }

    fun cat_v2() {
        InputStreamReader(
            System.`in`,
            StandardCharsets.UTF_8
        ).use { isr ->
                OutputStreamWriter(
                    System.out,
                    StandardCharsets.UTF_8
                ).use { osw ->
                    while (true) {
                        val c = isr.read()
                        if (c < 0) {
                            break
                        }
                        osw.write(c)
                    }
                }
        }
    }
}
