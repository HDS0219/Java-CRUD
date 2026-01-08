# Java CRUD Application with SQLite

A simple command-line CRUD (Create, Read, Update, Delete) application built with Java and SQLite for managing user data.

## 📋 Features

- **Create**: Insert new users with name, email, and phone number
- **Read**: View all registered users in a formatted list
- **Update**: Modify existing user information
- **Delete**: Remove users by ID
- **Persistent Storage**: All data is stored in a local SQLite database

## 🛠️ Technologies Used

- **Java** (JDK 11 or higher recommended)
- **SQLite** - Lightweight database
- **JDBC** - Java Database Connectivity
- **SQLite JDBC Driver** - For database connection

## 📦 Prerequisites

Before running this application, ensure you have:

1. **Java Development Kit (JDK)** installed (version 11 or higher)
2. **SQLite JDBC Driver** (`sqlite-jdbc-X.X.X.jar`)

### Installing SQLite JDBC Driver

1. Download the SQLite JDBC driver from: https://github.com/xerial/sqlite-jdbc/releases
2. Add the JAR file to your project:
   - **IntelliJ IDEA**: File → Project Structure → Libraries → Add (+) → Select the JAR file
   - **Eclipse**: Right-click project → Build Path → Add External Archives → Select the JAR file
   - **Command Line**: Include the JAR in your classpath when compiling and running

## 🚀 Getting Started

### Installation

1. Clone or download this repository
2. Ensure the SQLite JDBC driver is added to your project
3. Compile and run the `JavaCrud.java` file

### Running the Application

**Using an IDE:**
- Simply run the `main` method in `JavaCrud.java`

**Using Command Line:**
```bash
# Compile
javac -cp .:sqlite-jdbc-X.X.X.jar JavaCrud.java

# Run (Linux/Mac)
java -cp .:sqlite-jdbc-X.X.X.jar JavaCrud

# Run (Windows)
java -cp .;sqlite-jdbc-X.X.X.jar JavaCrud
```

## 💻 Usage

When you run the application, you'll see a menu with the following options:

```
===== USER CRUD MENU =====
1 - INSERT A NEW USER
2 - DELETE A USER
3 - UPDATE USER
4 - SEE ALL USERS
5 - EXIT
```

### Example Workflow

1. **Insert a user**: Choose option 1 and enter name, email, and phone number
2. **View all users**: Choose option 4 to see the complete list
3. **Update a user**: Choose option 3, enter the user ID and new information
4. **Delete a user**: Choose option 2 and enter the user ID to remove
5. **Exit**: Choose option 5 to close the application

## 🗄️ Database Structure

The application creates a SQLite database file (`data.db`) with the following table structure:

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_num TEXT
);
```

## 📁 Project Structure

```
.
├── JavaCrud.java       # Main application file
├── data.db            # SQLite database (created automatically)
└── README.md          # This file
```

## 🔧 Code Structure

The application consists of the following main methods:

- `connect()`: Establishes database connection and creates the table if it doesn't exist
- `insertData()`: Adds a new user to the database
- `updateData()`: Updates an existing user's information
- `selectData()`: Retrieves and displays all users
- `deleteData()`: Removes a user from the database
- `main()`: Provides the interactive menu interface

## ⚠️ Error Handling

The application includes basic error handling for:
- Database connection issues
- SQL execution errors
- Invalid user input

## 📝 License

This project is open source and available for educational purposes.

## 👤 Author

Created as a learning project to demonstrate Java CRUD operations with SQLite.

---

**Note**: The database file (`data.db`) will be created automatically in the project's root directory when you first run the application.