# Library Management System

This is a Library Management System API built using Spring Boot. The system allows librarians to manage books, patrons, and borrowing records.

## Project Structure

The project consists of the following components:

- **Entities**: Contains the entity classes representing the domain model (e.g., Book, Patron, BorrowingRecord).
- **Repository**: Interfaces extending JpaRepository for database operations.
- **Service**: Business logic implementation.
- **Controller**: REST API endpoints.
- **Exception**: Custom exception handling.
- **Validation**: Input validation for API requests.
- **Tests**: Unit tests for services and controllers.
- **Documentation**: README.md and other relevant documentation.

## Setup and Run

To run the application locally, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd library-management-system`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Access the API endpoints via `http://localhost:8080/api`

## API Endpoints

- `GET /api/books`: Retrieve a list of all books.
- `GET /api/books/{id}`: Retrieve details of a specific book by ID.
- `POST /api/books`: Add a new book to the library.
- `PUT /api/books/{id}`: Update an existing book's information.
- `DELETE /api/books/{id}`: Remove a book from the library.
- `GET /api/patrons`: Retrieve a list of all patrons.
- `GET /api/patrons/{id}`: Retrieve details of a specific patron by ID.
- `POST /api/patrons`: Add a new patron to the system.
- `PUT /api/patrons/{id}`: Update an existing patron's information.
- `DELETE /api/patrons/{id}`: Remove a patron from the system.
- `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book.
- `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book by a patron.

## Testing

Unit tests are implemented for service classes and controllers. You can run the tests using the following command:

