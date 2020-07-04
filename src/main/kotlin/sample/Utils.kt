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

    }

}
