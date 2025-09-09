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


---

## Database Setup (PostgreSQL / pgAdmin)

1. Create a database named `user_interaction_db`.  

2. Ensure you have two tables:

**User Table**

| Column | Type          | Notes                   |
|--------|---------------|------------------------|
| id     | BIGINT        | Primary Key, auto-gen   |
| name   | VARCHAR(255)  | User name               |

**Score Table**

| Column  | Type    | Notes                            |
|---------|---------|---------------------------------|
| id      | BIGINT  | Primary Key, auto-gen           |
| value   | INT     | Score value                     |
| user_id | BIGINT  | Foreign key referencing `user`  |

3. Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_interaction_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

---

## API Endpoints
Users

POST /users – create a user

POST /users
Body: { "name": "Alice" }


GET /users/{id} – get user info by ID

## Scores

POST /scores – add a score for a user

POST /scores?userId=1&scoreValue=90


GET /scores – get all scores for a user

GET /scores?userId=1


GET /scores/leaderboard – get top 3 scores across all users

Example JSON Responses

POST /users

{
  "id": 1,
  "name": "Alice"
}


GET /scores?userId=1

[
  { "userId": 1, "username": "Alice", "score": 90 },
  { "userId": 1, "username": "Alice", "score": 75 }
]


POST /scores?userId=1&scoreValue=100

{
  "userId": 1,
  "username": "Alice",
  "score": 100
}


GET /scores/leaderboard

[
  { "userId": 3, "username": "Charlie", "score": 120 },
  { "userId": 1, "username": "Alice", "score": 100 },
  { "userId": 2, "username": "Bob", "score": 95 }
]

Swagger UI

Access API docs:

http://localhost:8080/swagger-ui.html


You can test all POST/GET APIs from Swagger directly.

Running the Project

Clone the repository:

git clone <your-repo-url>


Open in IntelliJ IDEA.

Make sure PostgreSQL is running and database properties are correct.

Run UserInteractionApplication.java as Spring Boot App.

Test APIs via Swagger or Postman.

Notes

userId is required for /scores endpoints.

Leaderboard returns top 3 scores along with userId and username.

No separate DTOs; all response objects are implemented inside existing controllers.
