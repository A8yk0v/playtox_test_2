# playtox_test_2

Проект выполнен согласно тестовому заданию.
Только неиспользованна транзакция при покупке и проект не собирается в .war

Проект компилируется и запускается со встроенным tomcat сервером.
Командой gradlew bootRun.

Конфиги на использованную базу данных прописаны в файле application.properties
```java
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.url=jdbc:mysql://localhost:3306/db_playtox_test_2
   spring.datasource.username=playtox_test_2
   spring.datasource.password=Playtox_test_2
```

Hibernate настроен на автоматическое создание схемы БД.
После запуска приложения (когда hibernate создать схему) в ручную нужно добавить пользователя с ролью "admin"
```sql
INSERT INTO user (id, login, password, role) VALUES(100, 'admin', 'admin', 'admin');
```
Использованная БД:
```sql
create database db_playtox_test_2;
create user 'playtox_test_2'@'%' identified by 'Playtox_test_2';

USE db_playtox_test_2;

```