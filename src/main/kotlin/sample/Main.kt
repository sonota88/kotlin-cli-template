package sample

fun main(rawArgs: Array<String>) {
    println("hi")
    Utils.puts("puts")
    Utils.putskv("putskv", "putskv")

    val model = Model()

    Utils.puts(
        model.add(
            Integer.valueOf(rawArgs[0]),
            Integer.valueOf(rawArgs[1])
        )
    )
}
