Инструкция по сборке приложения:
Скопировать проект с github через File->New->Project from Version Control... (нужно будет вставить ссылку с github)
Другой способ - скачать архив из релизов (https://github.com/yasunovv/NotesShiftLabYasunov/releases), затем его распаковать и открыть через File->Open...

Используемые используемые сторонние библиотеки:
1. Room - для хранения заметок
2. Compose navigation, lifecycle - для навигации в приложении
3. Kotlin immutable - для вставки списков в composable функции, чтобы они были пропускаемыми
4. viewmodel-ktx - для использования coroutine builder в рамках жизненного цикла ViewModel
5. Kotlinx serialization - для type safe навигации в compose
6. hilt/dagger - для доставки зависимостей
8. kapt, ksp - для кодогенерации в room, hilt
9. Модуль :core:designsystem - для использования дизайн системы, которая написана в рамках интенсива.
