package sample

import kotlin.system.exitProcess

fun main(rawArgs: Array<String>) {
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
        "cat_v2" -> {
            model.cat_v2()
        }
        else -> {
            println("invalid command (${cmd})")
            exitProcess(1)
        }
    }
}
