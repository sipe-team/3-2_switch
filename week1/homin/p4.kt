fun main() {
    val num = readln().split(" ").map{ it.toInt() }
    val arr = readln().split(" ").map{ it.toInt() }
    println(arr.filter { it < num[1] }.joinToString(" "))
}