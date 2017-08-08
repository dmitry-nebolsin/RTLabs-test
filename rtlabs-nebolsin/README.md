# Тестовое задание Небольсина Д.В.

### Как развернуть
```
По дефолту приложение коннектится к базе MongoDB на сервере localhost:27017
Это можно изменить в файле application.properties. 
Для запуска никаких структур в базе не требуется.
При нажатии "Заполнить базу" в базе будут созданы и заполнены 3 коллекции - claim, person, service. 
Если они существовали - они будут очищены, а потом заполнены рандомизированным содержимым.

Примеры, как подружить JavaFX и Spring Boot взяты из статьи https://habrahabr.ru/post/265511/ - классы AbstractJavaFxApplicationSupport и ControllersConfiguration
В репозиторий закоммичен целиком проект IntelliJ IDEA, запускать можно просто собранный jar 
```


### Сборка
```
mvn clean package
```

### Запуск
```
java -jar target/test-application-rtlabs-0.0.1.jar
Или запускать из IDE через класс Application
```
