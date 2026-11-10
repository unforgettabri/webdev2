# WEBDEV2 — Week 10
## Lab 10: Capstone Milestone 3 — RESTful CRUD API

### 1. Project Overview
Implemented a RESTful CRUD API for clinic pets using Spring Boot, Spring Data JPA, and MySQL.

### 2. API Endpoints

| Method | Endpoint | Description | Expected Status |
|---|---|---|---|
| GET | `/api/pets` | Retrieve all pets | 200 OK |
| GET | `/api/pets/{id}` | Retrieve a pet by ID | 200 OK |
| POST | `/api/pets` | Create a pet | 201 Created |
| PUT | `/api/pets/{id}` | Replace pet details | 200 OK |
| PATCH | `/api/pets/{id}` | Partially update pet details | 200 OK |
| DELETE | `/api/pets/{id}` | Delete a pet | 204 No Content |

### 3. Validation and Error Handling

| Scenario | HTTP Status |
|---|---|
| Pet not found | 404 Not Found |
| Duplicate pet name | 409 Conflict |
| Invalid or missing required input | 400 Bad Request |

### 4. Implementation
- Created request and response DTOs.
- Implemented service and controller layers.
- Added CRUD operations for clinic pets.
- Added owner validation and duplicate-name checking.
- Used HTTP status codes for successful requests and errors.
- Kept entity objects out of API request and response bodies.

### 5. Test Results

All nine API tests passed:
1. Retrieve all pets.
2. Retrieve a pet by ID.
3. Create a pet.
4. Replace pet details.
5. Partially update pet details.
6. Delete a pet.
7. Retrieve a deleted pet.
8. Submit a duplicate pet name.
9. Submit invalid input.

### 6. Screenshots

Evidence is saved in the `screenshots/` folder:

- `week10-01.png` — Application running
- `week10-02.png` — GET all pets
- `week10-03.png` — GET pet by ID
- `week10-04.png` — POST create pet
- `week10-05.png` — PUT update pet
- `week10-06.png` — PATCH update pet
- `week10-07.png` — DELETE pet
- `week10-08.png` — 404 Not Found
- `week10-09.png` — 409 Conflict
- `week10-10.png` — 400 Bad Request

