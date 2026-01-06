# 🔗 URL Shortener Application (Spring Boot)

A **production-ready URL Shortener backend** built using **Spring Boot**, designed to generate short URLs, handle fast redirections, and allow users to **manage their created links without requiring login**, using a **JWT-based identification mechanism**.

👉 **Live Application:**
🔗 [https://url.shivaganesh.me](https://url.shivaganesh.me)

---

## 🚀 Key Highlights

* **Spring Boot–centric backend architecture**
* **JWT token mechanism without authentication/login**
* Fast and reliable **URL redirection**
* Secure API design with stateless token handling
* Clean separation of backend and frontend deployments
* Deployed and accessible via a public domain

---

## 🧠 Core Idea

Traditional URL shorteners require users to **sign up and log in** to manage their links.
This project takes a **different and user-friendly approach**:

> 🔐 **Each user is automatically assigned a JWT token when they create a short URL.**
> This token uniquely identifies the user and allows them to manage their URLs **without any login or account creation**.

---

## 🔐 JWT Without Login – How It Works

1. User creates a short URL
2. Backend generates a **JWT token**
3. Token is returned and stored on the client
4. All future operations (view, delete, manage URLs) use this token
5. Backend validates token and fetches only that user’s URLs

### ✅ Benefits

* No signup or login friction
* Stateless backend
* Secure user-level access
* Token-based ownership of URLs
* Better UX with minimal complexity

---

## 🧩 Backend Architecture (Spring Boot)

### 🔧 Technologies Used

* **Spring Boot**
* **Spring Web**
* **Spring Security (JWT)**
* **RESTful APIs**
* **Database for URL persistence**
* **Deployed on Render**

### 📌 Key Backend Responsibilities

* Generate unique short codes
* Map short codes to original URLs
* Handle HTTP redirection
* Issue and validate JWT tokens
* Restrict URL access to token owners
* Provide APIs for URL management

---

## 🔄 URL Redirection Flow

```
https://url.shivaganesh.me/r/{shortCode}
        ↓
Spring Boot Backend
        ↓
Redirects to Original URL
```

* Redirection handled entirely by backend
* No frontend logic involved in redirect
* Optimized for low latency

---

## 🌐 Frontend

The frontend provides:

* URL creation interface
* Dashboard for managing short URLs
* Seamless integration with backend APIs

> The primary focus of this project is **backend design and implementation**, especially around **JWT-based user identification and secure URL management**.

---

## 📡 API Overview

| Method | Endpoint         | Description              |
| ------ | ---------------- | ------------------------ |
| POST   | `/url/add`       | Create short URL         |
| GET    | `/url/{code}`    | Redirect to original URL |
| GET    | `/url/user/urls` | Fetch URLs using JWT     |
| DELETE | `/url/{id}`      | Delete user-owned URL    |

*(JWT token passed via headers)*

---

## 🧪 Security Design

* Stateless JWT authentication
* No session storage
* Token validation on every request
* URL access restricted per token
* Safe redirection handling

---

## 🏗 Deployment

* **Backend:** Render (HTTPS enabled)
* **Frontend:** Vercel
* **Custom Domain:** Enabled
* **CORS & HTTPS:** Configured properly

---

## 📌 Live Demo

🔗 **[https://url.shivaganesh.me](https://url.shivaganesh.me)**

Try creating a short URL and managing it without logging in.

---

## 📈 Future Enhancements

* Click analytics
* Expiring URLs
* Rate limiting
* Custom aliases
* Admin dashboard

---

## 👤 Author

**Shiva Ganesh Reddy Linga**
