# Clinic Pet REST API

## Base URL
`http://localhost:8080/api/pets`

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/pets` | Get all pets |
| GET | `/api/pets/{id}` | Get a pet by ID |
| POST | `/api/pets` | Create a pet |
| PUT | `/api/pets/{id}` | Replace a pet |
| PATCH | `/api/pets/{id}` | Partially update a pet |
| DELETE | `/api/pets/{id}` | Delete a pet |

## Pagination and Filtering

Example:
`GET /api/pets?page=0&size=5&sort=name&direction=asc`

Optional parameters:
- `page`: Page number (default: 0)
- `size`: Results per page (default: 20, maximum: 100)
- `sort`: Field used for sorting (default: id)
- `direction`: `asc` or `desc`
- `species`: Filter pets by species

## Create Pet Example

```json
{
  "name": "Mochi",
  "species": "Dog",
  "age": 2,
  "ownerId": 1
}