package sample

import sample.command.Cat2
import sample.command.Expr

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

    fun cat2(showAll: Boolean) {
        Cat2().main(showAll)
    }

    fun expr(tokens: List<String>): Int {
        return Expr(tokens).main()
    }
}
