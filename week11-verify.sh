#!/bin/bash

BASE="http://localhost:8080/api/pets"
PASS=0
FAIL=0

check() {
    NAME="$1"
    EXPECTED="$2"
    shift 2

    ACTUAL=$(curl -s -o /tmp/week11-response -w "%{http_code}" "$@")

    if [ "$ACTUAL" = "$EXPECTED" ]; then
        echo "[PASS] $NAME (HTTP $ACTUAL)"
        PASS=$((PASS + 1))
    else
        echo "[FAIL] $NAME (Expected $EXPECTED, got $ACTUAL)"
        cat /tmp/week11-response
        echo
        FAIL=$((FAIL + 1))
    fi
}

echo "=========================================="
echo "       WEEK 11 API VERIFICATION"
echo "=========================================="

check "Pagination and sorting" 200 \
    "$BASE?page=0&size=5&sort=name&direction=asc"

check "Species filtering" 200 "$BASE?species=Dog"

check "Get pet by ID" 200 "$BASE/1"

check "Invalid POST validation" 400 \
    -X POST "$BASE" -H "Content-Type: application/json" -d '{}'

check "Missing pet handling" 404 "$BASE/999999"

check "Maximum page-size cap" 200 "$BASE?size=999"

check "CORS preflight" 200 \
    -X OPTIONS "$BASE" \
    -H "Origin: http://localhost:3000" \
    -H "Access-Control-Request-Method: GET"

check "Valid POST creation" 201 \
    -X POST "$BASE" \
    -H "Content-Type: application/json" \
    -d '{"name":"Week11 Test Pet","species":"Cat","age":2,"ownerId":1}'

if [ "$PASS" -gt 0 ]; then
    ID=$(grep -o '"id":[0-9]*' /tmp/week11-response | head -1 | cut -d: -f2)
else
    ID=""
fi

if [ -n "$ID" ]; then
    check "PUT update" 200 \
        -X PUT "$BASE/$ID" \
        -H "Content-Type: application/json" \
        -d '{"name":"Week11 Updated Pet","species":"Cat","age":3,"ownerId":1}'

    check "PATCH update" 200 \
        -X PATCH "$BASE/$ID" \
        -H "Content-Type: application/json" \
        -d '{"age":4}'

    check "DELETE pet" 204 -X DELETE "$BASE/$ID"
else
    echo "[FAIL] CRUD update and delete skipped (creation failed)"
    FAIL=$((FAIL + 3))
fi

echo
echo "=========================================="
echo "RESULT: $PASS PASSED / $((PASS + FAIL)) TESTS"
if [ "$FAIL" -eq 0 ]; then
    echo "WEEK 11 VERIFICATION SUCCESSFUL"
else
    echo "WEEK 11 VERIFICATION FAILED"
fi
echo "=========================================="

rm -f /tmp/week11-response
exit "$FAIL"
