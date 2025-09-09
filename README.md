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

