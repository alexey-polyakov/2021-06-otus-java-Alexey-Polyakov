### Задание 3 - Аннотации. ДЗ

Написать свой тестовый фреймворк.<br>
Поддержать свои аннотации @Test, @Before, @After.<br>
Запускать вызовом статического метода с именем класса с тестами.<br>
Т.е. надо сделать:<br>
Cоздать три аннотации - @Test, @Before, @After.<br>
Создать класс-тест, в котором будут методы, отмеченные аннотациями.<br>
Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить методы
отмеченные аннотациями и пункта 1.<br>
Алгоритм запуска должен быть следующий:: метод(ы) Before текущий метод Test метод(ы) After для каждой такой "тройки"
надо создать СВОЙ объект класса-теста.<br>
Исключение в одном тесте не должно прерывать весь процесс тестирования.<br>
На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло успешно,
сколько упало, сколько было всего)<br>