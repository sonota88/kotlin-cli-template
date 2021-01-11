package sample

class Utils {

    companion object {

        const val DQ = "\""
        const val LF = "\n"

        fun puts(x: Any?) {
            println(x)
        }

        fun puts_e(x: Any?) {
            System.err.println(x)
        }

        fun putskv(k: String, v: Any?) {
            val vstr = if (v == null) "null" else v.toString()
            puts("${k} (${vstr})")
        }

        fun p(x: Any?) {
            puts(Utils.inspect(x))
        }

        fun pkv(k: String, v: Any) {
            putskv(k, inspect(v))
        }

        fun parseArgs(
            args: List<String>
        ) : Map<String, String>{
            return parseArgs(
                args,
                listOf()
            )
        }

        fun parseArgs(
            args: List<String>,
            names: List<String>
        ) : Map<String, String>{
            var opts = hashMapOf<String, String>()
            if (args.size == names.size) {
                // no optional
            } else if (args.size > names.size) {
                opts = parseOptionals(
                    slice(args, names.size, args.size)
                )
            }

            for ((i, name) in names.withIndex()) {
                opts[name] = args[i]
            }

            return opts
        }

        private fun parseOptionals(args: List<String>)
            : HashMap<String, String>
        {
            var opts = hashMapOf<String, String>()

            for (arg in args) {
                val i = arg.indexOf("=")
                if (1 <= i) {
                    val k = arg.substring(0, i)
                    val v = arg.substring(i + 1, arg.length)
                    opts[k] = v
                }
            }

            return opts
        }

        fun slice(
            xs: List<String>, from: Int, until: Int
        ): List<String>
        {
            val size = until - from
            val newXs = MutableList<String>(size) { "" }

            for ( (i, x) in xs.withIndex() ) {
                if (from <= i && i < until) {
                    newXs.set(i - from, x)
                }
            }

            return newXs
        }

        fun inspect(obj: Any?): String {
            if (obj == null) {
                return "null"
            } else {
                if (obj is Int) {
                    return obj.toString()
                } else if (obj is String) {
                    return inspectString(obj)
                } else if (obj is Char) {
                    return obj.toString()
                } else if (obj is Boolean) {
                    return obj.toString()
                } else if (isList(obj)) {
                    return inspectList(obj)
                } else if (isArray(obj)) {
                    return inspectArray(obj)
                } else if (isMap(obj)) {
                    return inspectMap(obj)
                } else {
                    val className = obj::class.toString()
                    throw RuntimeException("not yet impl (${className})")
                }
            }
        }

        fun inspectString(s: String): String {
            return (
                DQ +
                s
                    .replace("\\", "\\\\")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\"", "\\\"")
                    .replace("\t", "\\t") +
                DQ
            )
        }

        fun isList(obj: Any): Boolean {
            val className = (obj::class).toString()
            return (
                className == "class java.util.ArrayList" ||
                className == "class java.util.Arrays\$ArrayList" ||
                className == "class java.util.Collections\$SingletonList"
            )
        }

        fun inspectList(obj: Any): String {
            val xs = obj as List<Any>
            var s = "["

            for ( (i, x) in xs.withIndex() ) {
                if (1 <= i) {
                    s += ", "
                }
                s += inspect(x)
            }

            return s + "]"
        }

        fun isArray(obj: Any): Boolean {
            return (obj::class).toString() == "class kotlin.Array"
        }

        fun inspectArray(obj: Any): String {
            val xs = obj as Array<Any>
            return inspectList(xs.toList())
        }

        fun isMap(obj: Any): Boolean {
            return (obj::class).toString() == "class java.util.HashMap"
        }

        fun inspectMap(obj: Any): String {
            val map = obj as Map<Any, Any>
            var s = "{"

            var i = -1
            map.forEach {
                i += 1
                if (1 <= i) {
                    s += ", "
                }
                s += DQ + it.key + DQ + ": " + DQ + it.value + DQ
            }

            return s + "}"
        }

        fun readStdinAll(): String {
            var s: String = ""

            while (true) {
                val line : String? = readLine()
                if (line == null) {
                    break
                }
                s += line + LF
            }

            return s
        }

    }

}
