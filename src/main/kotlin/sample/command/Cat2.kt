package sample.command

import java.nio.charset.StandardCharsets
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Cat2 {
    fun main(showAll: Boolean) {
        InputStreamReader(
            System.`in`,
            StandardCharsets.UTF_8
        ).use { isr ->
                OutputStreamWriter(
                    System.out,
                    StandardCharsets.UTF_8
                ).use { osw ->
                    while (true) {
                        val n = isr.read()
                        if (n < 0) {
                            break
                        }

                        if (showAll) {
                            val c = n.toChar()
                            if (c == '\t') {
                                osw.write("^I")
                            } else if (c == '\r') {
                                osw.write("^M")
                            } else if (c == '\n') {
                                osw.write("$\n")
                            } else {
                                osw.write(n)
                            }
                        } else {
                            osw.write(n)
                        }
                    }
                }
        }
    }
}
