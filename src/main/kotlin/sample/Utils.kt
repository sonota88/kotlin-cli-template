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
    }
}
