# SongLingo

## About the Project

SongLingo is designed to help users improve their language skills
through music. Whether you're learning English or another
language, this app lets you leverage your favorite songs
to enhance your vocabulary and comprehension. You can save
words to your personalized catalogs with flashcards and learn
them whenever you want. The project is created using Java and
Spring Boot with a PostgreSQL database.
Additionally, SongLingo integrates with the Google Cloud Translation API
to provide automatic translation of song lyrics and individual
words, helping users easily understand and learn the meaning
of foreign language texts within the app.

## Features

- **User Mode:**
    - Browse and search for songs.
    - Translate lyrics or specific words within songs.
    - Save words to personalized flashcards for future review.
    - Organize flashcards into catalogs for systematic learning.

- **Admin Mode:**
    - Manage the song library by adding, updating, or deleting songs.
    - Ensure the song database is up-to-date and relevant for users.

## Technologies used:

- **Java 17**: The programming language used for development.
- **Spring Boot**: Framework for building the application.
    - **Spring Data JPA**: For database interactions.
    - **Spring Security**: For securing the application.
    - **Spring Boot Web**: For building web applications.
    - **Spring Boot Starter Test**: For testing the application.
- **Lombok**: To reduce boilerplate code by generating getters, setters, etc.
- **Hibernate**: ORM framework used with Spring Data JPA.
- **Google Cloud Translation API**: For translating song lyrics and individual words.
- **JSON Web Token (JWT)**: For authentication and authorization.
- **PostgreSQL**: The relational database management system used.
- **JUnit**: For unit testing.
- **Spring Data REST WebMVC**: For creating RESTful APIs.
- **Maven**
- **IntelliJ IDEA**
- **Postman**

## Prerequisites
Before you begin, ensure you have the following installed on your machine:
- **Postman**: https://www.postman.com/downloads/
- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/LukaszCh233/SongLingo.git
cd songLingo
```

### 2. Build and Start the Containers

```bash
docker-compose up --build
```
### 3. Stopping the Containers

```bash
docker-compose down
```
