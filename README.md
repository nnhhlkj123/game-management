# Game Management Web Application

This is a simple web application built with **Java Spring Boot** as part of the Backend Engineer Intern entry test at **Smilegate Vietnam**.

## 📋 Assignment Description

> Build a web application to manage game information with:
>
> - A **Game List** page to view and search games  
> - A **Game Registration** page to create and edit games  
> - API endpoints to manage games  
> - Multilingual game names support  
> - Any database of your choice

## ✅ Features

- List all games with filters (by keyword, category)
- Create and edit games with multilingual names (EN, KO, JA)
- Validate input: at least one name is required
- Delete multiple games at once
- Data stored in **H2 in-memory** database
- Simple and clean UI using **Bootstrap**

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring MVC + Thymeleaf
- Spring Data JPA
- H2 Database
- Bootstrap 5

##🚀 How to Build and Run (using Spring Tool Suite)

Clone or import project into Spring Tool Suite (STS)
File → Import → Maven > Existing Maven Projects → Select project folder.

Run the application
In the Boot Dashboard or via Run As > Spring Boot App.
Access the app:

Game List: http://localhost:8080/games

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/game-managementtest

User: sa, Password: (leave blank)

👤 Developer
Full name: Nguyễn Ngọc Hiếu Hảo

Email: nguyenngochieuhao@gmail.com

Submission for: Smilegate Vietnam - Backend Engineer Intern Test
