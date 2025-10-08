# Demo project microservices (Train)

### Создаем Контейнеры
Необходимо наличие docker-compose. 
В терминале переходим в корень проекта и выполняем
docker-compose up (поднимаются все контейнеры проекта с необходимыми зависимостями и переменными окружения)

### Работа с Kafka в Docker-контейнере
* подключиться к терминалу контейнера:
  [docker exec -it job4j_train-kafka-1 /bin/bash]
* Получить список топиков (при первом запуске контейнера пустой):
  [kafka-topics.sh --list --bootstrap-server localhost:9092]
* Создать топик:
  [kafka-topics.sh --create --topic {имя топика} --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092]
* Создать консольный продюсер для тестирования:
  [kafka-console-producer.sh --bootstrap-server localhost:9092 --topic {имя топика}]
* Создать консольный консюмер для теста:
  [kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic {имя топика} --from-beginning]

### Добавление данных
При старте приложения в БД данные отсутствуют, для добавления сущностей воспользуйтесь
endpoint-ом http://localhost:9900/ (POST), добавьте параметры запроса, соответствующие сущности TrainDto
в теле запроса передайте файл .jpg пример:
http://localhost:9900/?nameSeries=ВЛ40&trainNumber=99999998&buildingMileage=70115&dataBuild=1990-01-01&lastRepair=ТО-2&condition=в_работе
тело: (form-data) key -> 'file', value -> your_file.jpg

### Взаимодействие через SOAP интерфейс
Воспользуйтесь удобным вам клиентом, скрипт для настройки соединения будет доступен по
http://localhost:9900/ws/trains.wsdl - для взаимодействия напрямую с сервисом storage и
http://localhost:8081/ws/trains.wsdl - для работу через user_service

### Взаимодействие через REST-API
В user_service пользователю доступны два endpoint:
http://localhost:8081/train/one (GET) в качестве параметра запроса передается восьмизначный номер поезда (key 'message')
http://localhost:8081/train/all (GET) без параметров.

