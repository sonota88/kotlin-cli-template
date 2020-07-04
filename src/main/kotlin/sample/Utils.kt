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

    }

}
