
# Проект по автоматизации API для [JsonPlaceholder](https://jsonplaceholder.typicode.com/)


## **Содержание:**

* [Технологии и инструменты](#star-технологии-и-инструменты)

* [Реализованные проверки](#open_file_folder-реализованные-проверки)

* [Запуск из терминала](#computer-запуск-из-терминала)

* [Сборка в Jenkins](#сборка-в-jenkins)

* [Allure отчет](#allure-отчет)

* [Интеграция с Allure TestOps](#интеграция-с-allure-testops)

* [Уведомление в Telegram при помощи бота](#уведомление-в-telegram-при-помощи-бота)

  <br>


---
## :star: Технологии и инструменты:

<p>
<a href="https://www.jetbrains.com/idea/"><img width="45" alt="IntelliJ IDEA" src="media/icons/Idea.svg"></a>
<a href="https://www.java.com/ru/"><img width="45" alt="Java" src="media/icons/java.svg"></a>
<a href="https://junit.org/"><img width="45" alt="JUnit5" src="media/icons/Junit5.svg"></a>
<a href="https://ru.selenide.org/"><img width="45" alt="Selenide" src="media/icons/RestAssured.svg"></a>
<a href="https://allurereport.org/"><img width="45" alt="Allure" src="media/icons/Allure.svg"></a>
<a href="https://gradle.org/"><img width="45" alt="Gradle" src="media/icons/gradle-original.svg"></a>
<a href="https://www.jenkins.io/"><img width="45" alt="Jenkins" src="media/icons/jenkins.svg"></a>
<a href="https://qameta.io/"><img width="45" alt="Allure_TO" src="media/icons/Allure_TO.svg"></a>
</p>  
<br>


---
## :open_file_folder: Реализованные проверки:

- API:
  - GET /posts - получение списка постов
  - POST /posts - создание поста
  - GET /posts/{id} - получение поста по ID
  - GET /posta/{id} - получение поста по несуществующему ID
  - PUT /posts/{id} - обновление содержания поста
  - PATCH /posts/{id} - частичное обновление содержания поста
  - DELETE /posts/{id} - удаление поста
  <br>


---
## :computer: Запуск из терминала:

### Локальный запуск

Для локального запуска тестов используйте следующую команду:
```
./gradlew clean test
```

---
## <img width="25" alt="Jenkins" src="media/icons/jenkins.svg"> Сборка в Jenkins:

<img alt="Сборка в Jenkins" src="media/images/jenkins.png">  
<br>


---
## <img width="25" alt="Allure" src="media/icons/Allure.svg"> Allure отчет:

**[Ссылка на Allure-отчет](https://jenkins.autotests.cloud/job/jsonplaceholder-api-kholomkina/3/allure/)**

**Overview:**

<img alt="Allure отчет" src="media/images/allure.png">
<br><br>

**Tests:**

<img alt="Allure отчет: тесты" src="media/images/allure-tests.png">  
<br>


---
## <img width="25" alt="AllureTestOps" src="media/icons/Allure_TO.svg"> Интеграция с Allure TestOps:

**[Ссылка на Allure TestOps](https://allure.autotests.cloud/project/5060/test-cases?treeId=9890)**


**Тест-кейсы:**

<img alt="Интеграция с Allure TestOps" src="media/images/AllureTestOps.png">
<br><br>

**Dashboard прогонов:**

<img alt="Интеграция с Allure TestOps: прогоны" src="media/images/AllureTestOps-прогоны.png">  
<br>


---
## <img width="25" alt="telegram" src="media/icons/telegram.svg"> Уведомление в Telegram при помощи бота:

<img alt="Интеграция с Telegram" src="media/images/telegram.png">
<br>

