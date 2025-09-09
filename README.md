
# UserInteractionApplication

A simple Spring Boot application to manage **users** and **scores**, with a **leaderboard feature**, backed by **PostgreSQL**. The application exposes REST APIs for creating users, posting scores, retrieving user scores, and fetching the top 3 leaderboard scores.

---

## Features

- Create users and store their information.
- Post scores for users.
- Get all scores for a specific user (username + userId + score).
- Get **top 3 scores** across all users (leaderboard) with username and userId.
- Simple, lightweight, and fully compatible with Swagger for API testing.

---

## Technologies Used

- **Java 17 / 20** (Spring Boot)
- **Spring Web** – REST API endpoints
- **Spring Data JPA** – database access layer
- **PostgreSQL** – relational database
- **Lombok** – boilerplate reduction for getters/setters
- **Spring Boot DevTools** – hot reload
- **Swagger / Springdoc OpenAPI** – API documentation

---

## Project Structure

```

UserInteractionApplication
├─ src/main/java/com/example/UserInteractionApplication
│ ├─ controller
│ │ └─ UserController.java
│ │ └─ ScoreController.java
│ ├─ model
│ │ └─ User.java
│ │ └─ Score.java
│ ├─ repository
│ │ └─ UserRepository.java
│ │ └─ ScoreRepository.java
│ ├─ service
│ │ └─ UserService.java
│ │ └─ ScoreService.java
│ └─ UserInteractionApplication.java
├─ src/main/resources
│ └─ application.properties
│ └─ application-prod.properties
└─ pom.xml

````

---

## Database Setup (PostgreSQL / pgAdmin)

1. Create a database named `user_interaction_db`.

2. Ensure you have two tables:

**User Table**

| Column | Type | Notes |
|---|---|---|
| id | BIGINT | Primary Key, auto-gen |
| name | VARCHAR(255) | User name |

**Score Table**

| Column | Type | Notes |
|---|---|---|
| id | BIGINT | Primary Key, auto-gen |
| value | INT | Score value |
| user_id | BIGINT | Foreign key referencing `user` |

---

## `application.properties` and `application-prod.properties`

### `application.properties` (development)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_interaction_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Use this only for the first run to create tables automatically
spring.jpa.hibernate.ddl-auto=create
````

**Important:** `create` will drop and recreate all tables every time you start the app. Use only for the first run.

### `application-prod.properties` (after first run)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_interaction_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# After tables are created, switch to 'update' to avoid dropping existing data
spring.jpa.hibernate.ddl-auto=update
```

**Why switch to `update`?**

  - `update` preserves existing data and updates table structures if new fields are added.
  - Prevents accidental loss of all data on app restart.
  - Recommended for production or after first successful run.

-----

## Step-by-Step Instructions

1.  Clone the repository:

    ```bash
    git clone <your-repo-url>
    ```

2.  Open in IntelliJ IDEA.

3.  Make sure PostgreSQL is running. Create database `user_interaction_db`.

4.  Configure `application.properties`:

      - Use `ddl-auto=create` for the first run only.

5.  Run `UserInteractionApplication.java` as Spring Boot App.

6.  After the first run, switch to `application-prod.properties` or manually change:

    ```properties
    spring.jpa.hibernate.ddl-auto=update
    ```

7.  Test APIs via Swagger or Postman:

    ```bash
    http://localhost:8080/swagger-ui.html
    ```

-----

## API Endpoints

### Users

  - **`POST /users`** – create a user
      - **Body:** `{ "name": "Alice" }`
  - **`GET /users/{id}`** – get user info by ID

### Scores

  - **`POST /scores`** – add a score for a user
      - **Query parameters:** `userId` and `scoreValue`
      - **Example:** `POST /scores?userId=1&scoreValue=90`
  - **`GET /scores`** – get all scores for a user
      - **Query parameter:** `userId`
      - **Example:** `GET /scores?userId=1`
  - **`GET /scores/leaderboard`** – get top 3 scores across all users

-----

## Example JSON Responses

### `POST /users`

```json
{
  "id": 1,
  "name": "Alice"
}
```

### `GET /scores?userId=1`

```json
[
  { "userId": 1, "username": "Alice", "score": 90 },
  { "userId": 1, "username": "Alice", "score": 75 }
]
```

### `POST /scores?userId=1&scoreValue=100`

```json
{
  "userId": 1,
  "username": "Alice",
  "score": 100
}
```

### `GET /scores/leaderboard`

```json
[
  { "userId": 3, "username": "Charlie", "score": 120 },
  { "userId": 1, "username": "Alice", "score": 100 },
  { "userId": 2, "username": "Bob", "score": 95 }
]
```

-----

## Swagger UI

Access the interactive API documentation at:

```bash
http://localhost:8080/swagger-ui.html
```

You can test all POST/GET APIs from Swagger directly.

