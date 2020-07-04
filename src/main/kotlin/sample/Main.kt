package sample

import kotlin.system.exitProcess

fun main(rawArgs: Array<String>) {
    println("hi")
    Utils.puts("puts")
    Utils.putskv("putskv", "putskv")

    val args = rawArgs.toList()

    val cmd = args[0]
    val cmdArgs = Utils.slice(
        args,
        1, args.size
    )

    val model = Model()

    when (cmd) {
        "add" -> {
            val opts = Utils.parseArgs(
                cmdArgs,
                listOf("a", "b")
            )

            Utils.puts(
                model.add(
                    Integer.valueOf(opts["a"]),
                    Integer.valueOf(opts["b"])
                )
            )
        }
        "cat" -> {
            model.cat()
        }
        else -> {
            println("invalid command (${cmd})")
            exitProcess(1)
        }
    }
}
