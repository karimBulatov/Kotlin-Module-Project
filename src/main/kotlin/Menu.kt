import java.util.Scanner

class Menu(private val title: String, private val options: MutableList<Pair<String, () -> Unit>>) {

    fun addOption(name: String, action: () -> Unit) {
        options.add(name to action)
    }

    fun show() {
        while (true) {
            println("\n$title")
            options.forEachIndexed { index, option -> println("$index. ${option.first}") }
            println("${options.size}. Выход")

            val input = readInput()
            if (input == options.size) return
            if (input in options.indices) {
                options[input].second.invoke()
            } else {
                println("Ошибка: введите корректный номер.")
            }
        }
    }

    private fun readInput(): Int {
        while (true) {
            print("Введите номер: ")
            val input = Scanner(System.`in`).nextLine()
            try {
                return input.toInt()
            } catch (e: NumberFormatException) {
                println("Ошибка: введите число.")
            }
        }
    }
}