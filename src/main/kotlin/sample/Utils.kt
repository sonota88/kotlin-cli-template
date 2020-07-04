package sample

class Utils {

    companion object {

        fun puts(x: Any?) {
            println(x)
        }

        fun putskv(k: String, v: Any) {
            puts(
                "${k} (${v.toString()})"
            )
        }

        fun inspect(obj: Any?): String {
            if (obj == null) {
                return "null"
            } else {
                if (obj is Int) {
                    return obj.toString()
                } else if (obj is String) {
                    return inspectString(obj)
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
            val dq = "\""
            return (
                dq +
                s
                    .replace("\\", "\\\\")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\"", "\\\"")
                    .replace("\t", "\\t") +
                dq
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
            val dq = "\""
            var s = "{"

            var i = -1
            map.forEach {
                i += 1
                if (1 <= i) {
                    s += ", "
                }
                s += dq + it.key + dq + ": " + dq + it.value + dq
            }

            return s + "}"
        }
    }

}
