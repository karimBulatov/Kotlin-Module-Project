import java.util.Scanner

fun main() {
    val archives = mutableListOf<Archive>()

    val mainMenu = Menu("Список архивов:", mutableListOf())

    mainMenu.addOption("Создать архив") {
        print("Введите название архива: ")
        val name = Scanner(System.`in`).nextLine().trim()
        if (name.isNotEmpty()) {
            archives.add(Archive(name))
            println("Архив '$name' создан.")
        } else {
            println("Ошибка: имя архива не может быть пустым.")
        }
    }

    mainMenu.addOption("Просмотреть архивы") {
        if (archives.isEmpty()) {
            println("Список архивов пуст.")
        } else {
            val archiveMenu = Menu("Выберите архив:", mutableListOf())
            archives.forEach { archive ->
                archiveMenu.addOption(archive.name) {
                    openArchiveMenu(archive)
                }
            }
            archiveMenu.show()
        }
    }

    mainMenu.show()
}

fun openArchiveMenu(archive: Archive) {
    val archiveMenu = Menu("Архив: ${archive.name}", mutableListOf())

    archiveMenu.addOption("Создать заметку") {
        print("Введите название заметки: ")
        val title = Scanner(System.`in`).nextLine().trim()
        if (title.isEmpty()) {
            println("Ошибка: название заметки не может быть пустым.")
            return@addOption
        }

        print("Введите текст заметки: ")
        val content = Scanner(System.`in`).nextLine().trim()
        if (content.isEmpty()) {
            println("Ошибка: текст заметки не может быть пустым.")
            return@addOption
        }

        archive.notes.add(Note(title, content))
        println("Заметка '$title' добавлена.")
    }

    archiveMenu.addOption("Просмотреть заметки") {
        if (archive.notes.isEmpty()) {
            println("Список заметок пуст.")
        } else {
            val noteMenu = Menu("Выберите заметку:", mutableListOf())
            archive.notes.forEach { note ->
                noteMenu.addOption(note.title) {
                    println("\nЗаметка: ${note.title}\n${note.content}")
                }
            }
            noteMenu.show()
        }
    }

    archiveMenu.show()
}
