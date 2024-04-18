# Getting Started

You will receive guidance on how to set up your project locally from these instructions. Use the instructions below to launch a clone of the project for testing and development on your local computer.

## Prerequisites

- Java JDK 11 or newer
- Maven (if not included in your IDE)
- An IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Git

## Installation

1. **Clone the repository:**

2. **Open the project in your IDE:**

3. **Configure the application:**

   Modify the `src/main/resources/application.properties` file to adjust the database and other configurations as necessary. For development, you can use an in-memory database like H2.

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

   # H2 Console
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console
   ```

4. **Build the project:**

5. **Run the application:**


## Using the Application

### Uploading a CSV File

To upload a CSV file and store data:

1. **Start your Spring Boot application.**
2. **Use a REST client like Postman to make a POST request:**

   - URL: `http://localhost:8080/api/cells/upload`
   - Method: POST
   - Body: form-data
   - Key: `file`
   - Value: [Select your CSV file]

   This endpoint will parse and store the cell phone data from the CSV file into the database.

### Retrieving All Cell Phone Records

- **URL**: `http://localhost:8080/api/cells`
- **Method**: GET

This endpoint retrieves all cell phone records stored in the database.

# API Reference

## Endpoints

- **POST `/api/cells/upload`** - Uploads a CSV file and stores its data.
- **GET `/api/cells`** - Retrieves all stored cell phone records.

# Built With

- **[Spring Boot](https://spring.io/projects/spring-boot)** - The framework used
- **[Maven](https://maven.apache.org/)** - Dependency Management
- **[H2 Database](https://www.h2database.com/html/main.html)** - Used for the in-memory database
