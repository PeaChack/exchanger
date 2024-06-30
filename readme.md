# Тестовое задание для ЗАО Водород

*Автор: Булыга Дмитрий*

## Методы взаимодействия

В результате выполнения задания были реализованы следующие энд-поинты:
### GET

##### /api/currency/load
Загрузка курсов валют в систему, на выбранную дату. Если дата не указана, данные загружаются на текущую дату. Метод возвращает загруженные курсы, а в случае неудачи ошибку.

Параметры: 
- date 

##### /api/currency/rate
Возврат курса валют на указанную дату для валюты, по её коду.

Параметры:
- date
- code

### Использованные технологии

- Spring Boot
- Spring Data JPA
- Spring Web MVC
- Liquibase
- MapStruct
- JUnit 5
- Mockito
- Lombok

### База данных
H2 Database