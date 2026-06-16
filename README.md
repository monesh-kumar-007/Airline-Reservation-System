# ✈️ Layered Airline Reservation System

> A high-performance, console-based Airline Reservation System built with **Java (OODP Principles)** and backed by **Oracle Database XE** — featuring a clean, decoupled MVC/Layered architecture.

---

## 📑 Table of Contents

- [🏗️ Project Architecture](#️-project-architecture)
- [🗄️ Database Architecture](#️-database-architecture--specifications)
- [🔒 Security](#-enterprise-grade-security)
- [🛠️ Prerequisites & Installation](#️-prerequisites--installation)
- [💻 Compilation & Execution](#-compilation--execution)
- [✨ Features](#-features)

---

## 🏗️ Project Architecture

```
AIRLINE RESERVATION SYSTEM/
│
├── README.md                           # Project documentation & overview
├── .gitignore                          # Excludes compiled binaries and local modules
│
├── database/                           # SQL engine
│   └── Airline Database.session.sql   # Relational schemas & structural setup
│
└── backend-java/                       # Core Java System Environment
    ├── bin/                            # Generated JVM class bytecode
    ├── lib/                            # Dependencies (ojdbc11.jar)
    └── src/                            # Decoupled source code
        ├── AirlineReservationApp.java  # Main application entry point
        ├── TestOracle.java             # Infrastructure validation tester
        ├── model/                      # State entities (User, Flight, Booking)
        ├── service/                    # Business logic (AuthService, ReservationSystem)
        ├── ui/                         # Presentation screens (MainMenu, UserDashboard)
        └── util/                       # Driver configs & safety tools (DBUtil, InputValidator)
```

---

## 🗄️ Database Architecture & Specifications

> This application runs strictly on **Oracle Database Relational Engine**, optimized for Oracle XE (versions **18c / 21c**).

### 🔍 Connection Breakdown — `DBUtil.java`

| Parameter | Value | Description |
|-----------|-------|-------------|
| **Driver Class** | `oracle.jdbc.driver.OracleDriver` | Pure Java JDBC driver by Oracle |
| **Connection String** | `jdbc:oracle:thin:@//localhost:1521/XEPDB1` | Full JDBC URL |
| `oracle:thin` | — | Pure Java driver over TCP/IP sockets |
| `localhost` | — | Local sandbox network interface |
| `1521` | — | Default Oracle network port listener |
| `XEPDB1` | — | Default Oracle Pluggable Database (PDB) target |
| **Admin User** | `system` | Default administrative user |

---

## 🔒 Enterprise-Grade Security

Hardcoded credentials are **eliminated** from source. The system reads your password dynamically at runtime via a system-level environment variable:

```java
private static final String PASSWORD = System.getenv("DB_PASSWORD");
```

> ⚠️ Never commit your `DB_PASSWORD` to version control. Always set it as an environment variable before running.

---

## 🛠️ Prerequisites & Installation

### Step 1 — Install Oracle Database XE

Download and install [Oracle Database Express Edition](https://www.oracle.com/database/technologies/xe-downloads.html).  
During setup, ensure it configures to **default port `1521`**.

---

### Step 2 — Set Up Local Database Schemas

Connect to your Oracle instance and execute the SQL script at `database/Airline Database.session.sql`:

```sql
CREATE TABLE USERS (
    id              NUMBER PRIMARY KEY,
    username        VARCHAR2(100) NOT NULL,
    email           VARCHAR2(150) UNIQUE NOT NULL,
    password        VARCHAR2(100) NOT NULL,
    role            VARCHAR2(30) NOT NULL
);

CREATE TABLE FLIGHTS (
    flight_id        NUMBER PRIMARY KEY,
    airline          VARCHAR2(100) NOT NULL,
    source           VARCHAR2(100) NOT NULL,
    destination      VARCHAR2(100) NOT NULL,
    flight_date      VARCHAR2(50) NOT NULL,
    departure_time   VARCHAR2(50) NOT NULL,
    seats_available  NUMBER NOT NULL,
    price            NUMBER(10, 2) NOT NULL
);

CREATE TABLE BOOKINGS (
    booking_id      NUMBER PRIMARY KEY,
    flight_id       NUMBER REFERENCES FLIGHTS(flight_id),
    passenger_name  VARCHAR2(100) NOT NULL,
    user_id         NUMBER REFERENCES USERS(id),
    seat_no         NUMBER NOT NULL,
    status          VARCHAR2(30) NOT NULL
);
```

---

### Step 3 — Inject the JDBC Driver

Drop the official **`ojdbc11.jar`** file into the `backend-java/lib/` folder so the Java runtime can load the database drivers.

```
backend-java/
└── lib/
    └── ojdbc11.jar   ✅ Place it here
```

---

## 💻 Compilation & Execution

> Navigate into the `backend-java/` folder first:
> ```bash
> cd backend-java
> ```

---

### 1️⃣ Set the Environment Secret

Securely inject your Oracle password into your terminal session before running:

**Windows PowerShell:**
```powershell
$env:DB_PASSWORD="your_actual_oracle_password_here"
```

**Windows CMD:**
```cmd
set DB_PASSWORD=your_actual_oracle_password_here
```

---

### 2️⃣ Compile the Codebase

**Windows PowerShell:**
```powershell
javac -d bin -cp "lib/ojdbc11.jar" src/*.java src/**/*.java
```

**Windows CMD:**
```cmd
javac -d bin -cp "lib/ojdbc11.jar" src/AirlineReservationApp.java src/TestOracle.java src/model/*.java src/service/*.java src/ui/*.java src/util/*.java
```

---

### 3️⃣ Test Database Connectivity

Validate your Oracle connection pipeline **before** launching the full app:

```bash
java -cp "bin;lib/ojdbc11.jar" TestOracle
```

---

### 4️⃣ Run the Application

Boot the main application loop:

```bash
java -cp "bin;lib/ojdbc11.jar" AirlineReservationApp
```

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🔐 **Strict Validation Layers** | Regex-pattern checks filtering emails and numeric-infused secure passwords at sign-up |
| 🔄 **Transaction-Safe Logic** | Thread-safe seat allocations with rolling reservation management |
| 🛡️ **SQL Injection Resilient** | All database mutations execute over structured `PreparedStatement` boundaries |

---

## 🧩 Tech Stack

![Java](https://img.shields.io/badge/Java-OOP%20%7C%20OODP-orange?style=flat-square&logo=java)
![Oracle DB](https://img.shields.io/badge/Oracle%20DB-XE%2018c%2F21c-red?style=flat-square&logo=oracle)
![JDBC](https://img.shields.io/badge/JDBC-ojdbc11-blue?style=flat-square)

---

<div align="center">
  <sub>Built with ❤️ using Java & Oracle DB XE</sub>
</div>
