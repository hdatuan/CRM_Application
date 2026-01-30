<div align="center">

# CRM Application
### A Modern Customer Relationship Management System

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Servlet](https://img.shields.io/badge/Servlet-Using%20v3.1-orange?style=for-the-badge)](https://jakarta.ee/specifications/servlet/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)


</div>

---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [Authors](#authors)

---

## Overview
**CRM Application** is a robust, enterprise-grade project management tool designed to streamline internal operations. It bridges the gap between project managers and their teams by offering a centralized dashboard for tracking jobs, assigning tasks, and monitoring real-time progress.


This application replaces scattered spreadsheets with a unified, role-based system that ensures data integrity and operational transparency.

---

## Features
### Core Modules
*   **Dynamic Dashboard**: Visual analytics showing task distribution (Not Started vs In Progress vs Completed).
*   **User Management**: Full CRUD operations for system users with role assignment.
*   **Role-Based Access Control (RBAC)**: secure permission system (e.g., Admin, Manager, Staff).
*   **Job Management**: Create, edit, and archive projects with timeline tracking.
*   **Task Management**: Granular task assignment linked to specific Jobs and Users.
*   **User Profile**: Personal dashboard for users to update credentials and view assigned work.

---

## System Architecture
The project strictly follows the **Standard MVC (Model-View-Controller)** pattern to ensure scalability and maintainability.

| Layer | Responsibility | Components |
|-------|---------------|------------|
| **View** | User Interface | JSP, Bootstrap, JSTL |
| **Controller** | Request Handling | Java Servlets, Filters |
| **Service** | Business Logic | Java Classes (Service Layer) |
| **Repository** | Data Access | JDBC, SQL Queries |
| **Model** | Data Transfer | POJO Entities |

---

## Tech Stack
### Backend
*   **Language**: Java 8+
*   **Core**: Java Servlet API, JSP
*   **Database**: MySQL 8.0
*   **Security**: Filter-based Authentication & Session Management

### Frontend
*   **Framework**: Bootstrap 4
*   **Scripting**: jQuery, JavaScript
*   **Visualization**: Morris.js (Charts), Toast (Notifications), DataTables

---

## Getting Started

### Prerequisites
*   [Java Development Kit (JDK 8+)](https://www.oracle.com/java/technologies/downloads/)
*   [Apache Tomcat 9.0](https://tomcat.apache.org/download-90.cgi)
*   [MySQL Server](https://dev.mysql.com/downloads/installer/)
*   [Eclipse IDE for EE Developers](https://www.eclipse.org/downloads/packages/) (Recommended)

### Installation
1.  **Clone the repository**
    ```sh
    git clone https://github.com/hdatuan/CRM_Application.git
    cd CRM_Application
    ```

2.  **Database Setup**
    *   Open MySQL Workbench or CLI.
    *   Execute the script located at `/database/schema.sql` (if available) or create a database named `crm_app`.
    *   Update database credentials in `src/main/resources/db.properties`:
        ```properties
        db.url=jdbc:mysql://localhost:3306/crm_app
        db.user=root
        db.password=your_password
        ```

3.  **IDE Setup (Eclipse)**
    *   `File` -> `Import` -> `Existing Projects into Workspace`.
    *   Select the root directory.
    *   Right-click project -> `Run As` -> `Run on Server` -> Select Tomcat 9.0.

4.  **Access the App**
    *   Open Browser: `http://localhost:8080/crm_app`
    *   Default Admin Credentials: `admin@gmail.com` / `123456` (Example)

---

## Project Structure

```bash
crm_app/
├── 📂 src/main/java/hdatuan   # Core Backend Logic
│   ├── 📂 config              # DB Connections (MySQLConfig)
│   ├── 📂 controller          # Servlets (Login, Task, User...)
│   ├── 📂 entity              # POJOs (User, Role, Job, Task)
│   ├── 📂 filter              # Auth Filters
│   ├── 📂 repository          # JDBC Data Access
│   └── 📂 service             # Business Logic Layer
│
├── 📂 src/main/resources      # Configuration files
│   ├── db.properties          # Configure your database
│   └── db.properties.example  # Example of db.properties
│
├── 📂 src/main/webapp         # Frontend Assets
│   ├── 📂 bootstrap           # CSS Framework
│   ├── 📂 css / js            # Custom Styles & Scripts
│   ├── 📂 plugins             # 3rd Party Libs (Charts, Tables)
│   └── 📂 WEB-INF             # Protected Configuration
│       ├── 📂 views           # JSP View Templates (Protected)
│       └── 📂 lib             # JAR Dependencies
│
└── 📄 README.md               # Project Documentation
```


---

## Contributing
Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## Authors
**hdatuan** - *Backend Architecture & Development*

