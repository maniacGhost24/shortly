# 🔗 Shortly — URL Shortener

A production-ready URL shortener built using Spring Boot, PostgreSQL, and Docker, deployed on Render.

---

## 🚀 Live Demo

**Base URL:**
👉 https://shortly-o2ss.onrender.com

---

## 📌 Features

* 🔗 Shorten long URLs into compact links
* ↪️ Redirect using short codes
* 📊 Click tracking & analytics
* ⏱️ Track creation & last accessed timestamps
* ⚡ Optimized database interactions
* 🧱 Clean API contract with DTOs
* ❌ Global exception handling
* 🐳 Fully Dockerized backend
* ☁️ Deployed on cloud (Render)

---

## 🏗️ Tech Stack

* Backend: Java, Spring Boot
* Database: PostgreSQL
* ORM: Hibernate (JPA)
* Build Tool: Maven
* Containerization: Docker
* Deployment: Render

---

## 📂 Project Structure

```
src/
 ├── controller/      # REST endpoints
 ├── service/         # Business logic
 ├── repository/      # Database access
 ├── model/           # JPA entities
 ├── dto/             # Request/response objects
 └── exception/       # Global exception handling
```

---

## ⚙️ API Endpoints

### 🔹 Shorten URL

**POST** `/api/shorten`

Request:

```json
{
  "url": "https://google.com"
}
```

Response:

```json
{
  "shortUrl": "https://your-app.onrender.com/abc123"
}
```

---

### 🔹 Redirect

**GET** `/{shortCode}`

* Redirects to original URL
* Increments click count

---

### 🔹 Get Analytics

**GET** `/api/stats/{shortCode}`

Response:

```json
{
  "originalUrl": "https://google.com",
  "shortCode": "abc123",
  "clickCount": 5,
  "createdAt": "2026-04-29T22:00:00",
  "lastAccessed": "2026-04-29T22:30:00"
}
```

---

## 🧠 Key Design Decisions

* **Base62 encoding** used for deterministic short code generation
* Avoided random collisions by deriving code from DB ID
* Used **DTO pattern** for clean API contracts
* Implemented **global exception handling** for consistent responses
* Reduced DB writes using **transactional updates**
* Environment-based configuration using Spring profiles

---

## 🐳 Running Locally (Docker)

### 1. Build and run

```bash
docker-compose up --build
```

### 2. Access API

```
http://localhost:8080
```

---

## ⚙️ Environment Variables

```
DB_URL=jdbc:postgresql://host:5432/db
DB_USER=your_username
DB_PASS=your_password
BASE_URL=http://localhost:8080
SPRING_PROFILES_ACTIVE=prod
```

---

## 📊 Example Flow

1. POST `/api/shorten`
2. Receive short URL
3. Open short URL → redirect
4. GET `/api/stats/{code}` → view analytics

---

## 🚀 Deployment

* Dockerized using multi-stage builds
* Deployed on Render with managed PostgreSQL
* Uses environment variables for configuration

---

## 🔥 Future Improvements

* Custom aliases (`/my-link`)
* Link expiration
* Redis caching
* Rate limiting
* Authentication

---

## 👨‍💻 Author

Built as a backend learning project focusing on real-world system design and deployment.

---
