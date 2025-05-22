# Research-Oriented University Platform

A web application for managing academic research activities within a university. Built with Spring Boot and PostgreSQL, it allows admins to manage users and roles, while students can become researchers, publish articles, and subscribe to faculty journals.
---

##  Features

- **Role-based access**: Admin, Student, Teacher, Librarian, Manager  
- **Students can become researchers**  
- **Publish research papers** linked to specific faculties  
- **Subscribe to research journals** and receive notifications for new content  
- **Sort articles** by publication date, length, and more  
- **CRUD functionality** for managing papers and profiles  
- Basic **login & authentication** (Admin login fully implemented)

---

## Tech Stack

| Layer        | Technologies                      |
|--------------|-----------------------------------|
| **Backend**  | Java, Spring Boot (MVC)           |
| **Frontend** | JSP, HTML                         |
| **Database** | PostgreSQL                        |
| **Build Tool** | Maven                           |

---

## Project Structure
src/
├── main/
│ ├── java/com/rou/test2/ # Controllers and main application
│ └── resources/
│ └── application.properties # DB configuration
│ └── webapp/
│ └── web/WEB-INF/ # JSP views and web.xml
├── test/ # Unit tests (basic setup)

---

##  Getting Started

### 1. Clone the Repository

git clone https://github.com/MarzhanOmarbekova/Research-University-Platform.git
cd Research-University-Platform

### 2. Configure the Database
Make sure PostgreSQL is running locally.
Update the src/main/resources/application.properties with your DB credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password


### 3. Run the Application
If using Maven Wrapper (Windows):
mvnw spring-boot:run

Or (Linux/macOS):
./mvnw spring-boot:run

Then go to http://localhost:8080 in your browser.

## Developer Role
This project was built as an individual university assignment. I implemented the full backend logic using Spring Boot, designed the JSP-based frontend, and integrated PostgreSQL for data persistence. The project includes:

Connecting entity classes with the database

Creating logic for user roles and research features

Managing admin panel views and researcher workflows

Enabling user-specific actions like subscriptions and publication management

## Author
Marzhan Omarbekova
