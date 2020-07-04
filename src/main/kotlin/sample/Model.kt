package sample

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
}
