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
                val className = obj::class.toString()
                throw RuntimeException("not yet impl (${className})")
            }
        }

    }

}
