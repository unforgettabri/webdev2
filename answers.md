# Lab 4 — Server-Rendered CRUD Interface

## Task 1 — Book List
Created `book-list.html` using Thymeleaf `th:each` and `th:text` to display all books, with an empty-state message using `th:if`.

## Task 2 — Book Detail
Created `book-detail.html` using Thymeleaf expressions to display the book title, author, and nested category name using `book.category.name`.

## Task 3 — Shared Navigation Fragment
Created `fragments/navbar.html` with a reusable `navbar` fragment and included it in both the book list and detail pages using `th:replace`.

## Task 4 — Create Form
Created `book-form.html` using `th:object` and `th:field` to bind the form to a `Book` object. The form submits to `POST /books` and redirects to the book list after successful creation.

## Task 5 — Binding Errors
Updated the create endpoint to use `BindingResult`. Invalid numeric input in the price field redisplays the form and shows the binding error using `th:errors`.

## Testing
All five tasks were tested through the running Spring Boot application. The project successfully builds with Maven.
