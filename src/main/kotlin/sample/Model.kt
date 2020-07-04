package sample

import sample.command.Cat2

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

    fun cat2() {
        Cat2().main()
    }
}
