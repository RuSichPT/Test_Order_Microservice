# Test_Order_Microservice
Practice java (inobitec)

Задание №1. Разработка Java RESTful сервиса

Необходимо разработать тестовый RESTful сервис “ORDER”, отвечающий за хранение
информации о заказах в Медицинской Информационной Системе (МИС). Схема базы
состоит из двух таблиц:
1. ORDER, отвечающая за хранение заказов
2. ORDER_ITEM, отвечающая за хранение детализации позиций заказов
Скрипт по созданию тестовых таблиц представлен в приложении №1. Скрипт по наполнению таблиц в тестовых данных представлен в приложении №3.

Сервис должен реализовывать методы CRUD (от создание (англ. create), чтение (read), модификация (update), удаление (delete)) в соответствии с представленной в
приложении №2 спецификацией API в формате OpenAPI. Рекомендуемый инструмент для чтения спецификации https://editor.swagger.io 

	● Используемый стек технологий: Java 11 + Spring Boot + MyBatis + PostgreSQL 

	● Оформление результата: Maven Project + исходный код в репозиторий на GitHub

Задание №2.1. Сервлеты, запросы формата XML

Необходимо расширить программу, разработанную в задаче №1 практики таким
образом, чтобы в ней появилась дополнительная точка входа в виде нового сервлета.
Реализовать аналогичное взаимодействие клиента и сервера через методы CRUD (от
создание (англ. create), чтение (read), модификация (update), удаление (delete)), через
новую точку входа. В качестве формата сообщений использовать XML.

Задание №2.2. Сервлет-фильтры, контроль доступа к ресурсам сервиса

Необходимо расширить программу, разработанную в задаче №2.1 практики таким
образом, чтобы перед точкой входа, реализуемой в виде сервлета добавился
сервлет-фильтр. Данный фильтр должен применяться для контроля доступа к
ресурсам сервиса со стороны клиента.

1. Необходимо в базу данных проекта добавить новую таблицу SESSION с полями:

	● ID integer, первичный ключ, счетчик сессий

	● SESSION_ID varchar(24), уникальный идентификатор сессии

	● START_TIME datetime, время открытия сессии

	● TIMEOUT_MINUTES integer, время действия сессии в минутах

2. Фильтр должен считывать из заголовка клиентского запроса уникальный
идентификатор сессии, представленный в формате ключ-значение. Ключ - константа
“SESSION-ID”. Значение - некая строка из 4 групп по 4 символа. Например:
SESSION-ID=1234-AAFF-BB55-DD22

3. Фильтр должен проверять как наличие самого поля SESSION-ID в заголовке, так и
проверку значения этого поля. Если поле SESSION-ID в заголовке отсутствует,
необходимо вернуть ошибку “Invalid session”.

4. Если поле SESSION-ID в заголовке присутствует, то необходимо проанализировать
его значение, сравнив с активными сессиями из базы данных:

	● Сессия с таким значением SESSION_ID ищется в базе данных

	● Если такая запись не находится, то возвращаем ошибку “Unknown session”

	● Если такая запись находится, то необходимо проверить не истекло ли время
	сессии. Если истекло, то возвращаем ошибку “Session expired”

	● Если время не истекло, то доступ разрешен, передаем управление из фильтра
	в сервлет

5. Приветствуется оптимизация количества запросов к базе данных путем создания в
памяти web-приложения кэша сессий.