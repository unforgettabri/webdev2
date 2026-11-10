# WEBDEV2 — Week 11
## Lab 11: Capstone Milestone 3 — Complete Core REST API

### 1. Implementation
- Added DTO input validation.
- Implemented centralized exception handling.
- Created consistent JSON error responses.
- Configured CORS for frontend access.
- Added pagination, sorting, and species filtering.
- Created API documentation and Postman test collection.

### 2. Testing Results

| Test | Result |
|---|---|
| Pagination and sorting | Passed |
| Input validation | Passed |
| 404 error handling | Passed |
| Species filtering | Passed |
| CORS configuration | Passed |
| Maximum page size | Passed |
| Duplicate pet name (409) | Passed |
| Maven build | Successful |
| Postman collection JSON validation | Passed |

### 3. Documentation
- `API.md` — API endpoints, parameters, validation, and error responses.
- `api_tests.json` — Postman collection with automated API tests.

### 4. Database
Used the existing MySQL 8.4 database through Docker.

### 5. Conclusion
Successfully implemented and verified the core REST API improvements for Capstone Milestone 3.