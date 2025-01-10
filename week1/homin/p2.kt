fun main() {
    val (a, b) = readln().split(" ").map { it.toInt() }

    when {
        a > b -> println(">")
        a < b -> println("<")
        else -> println("==")
    }
}
