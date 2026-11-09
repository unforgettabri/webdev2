# Task 5: Debugging JPA Errors

## 1. Stack Trace Analysis
The application errors indicate problems with entity persistence and database constraints. The stack trace helps identify which operation failed and where the error originated.

## 2. SQL Log Analysis
Hibernate SQL logs help identify the SQL statements being executed. These logs make it easier to locate missing values, incorrect entity mappings, and database constraint violations.

## 3. Identified Problems and Fixes

| Problem | Fix |
|---|---|
| Missing `@GeneratedValue` | Add `@GeneratedValue(strategy = GenerationType.IDENTITY)` to the ID field. |
| Missing species field | Add the `species` field with its getter and setter. |
| Null microchip number | Provide a valid microchip number and enforce `@NotBlank` and `nullable = false`. |

## 4. Conclusion
Proper JPA annotations, entity fields, and validation prevent persistence errors and help maintain database consistency.