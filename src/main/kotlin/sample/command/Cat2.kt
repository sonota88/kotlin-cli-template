package sample.command

import java.nio.charset.StandardCharsets
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Cat2 {
    fun main() {
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
