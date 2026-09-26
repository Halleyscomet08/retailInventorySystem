# API Reference

These are the planned APIs for interfacing with the system.

## Brand

### GET: "/brand/{brandId}"

Returns a brandDTO object:

{
"brandName": "{brandName}"
}

**Response:**
200 OK - returns the brand object
404 ResourceNotFound - Brand with given ID does not exist.

although this is dto: may need to pass things like the id lmfao fuck if i know

### POST: "/brand"

Creates a new brand. Receives the following JSON:
{
"brandName": {brandName},
"category": "{category name}",
"productName": "{productName"
}

**Response:**
200 OK - returns the brand object
400 Bad Request - invalid/missing fields
400 Bad Request - Fields are not right type

### PATCH: "/brand/{brandId}/archive"

Archives a specific Brand. Planned to also archive the associated Products, and
soft deletes the associated Product Variants.

**Responses:**
200 OK - returns "Archive complete"
404 Resource Not Found - Brand with given ID does not exist.

## Product (api/product)

### GET: "/product"

Returns a list of all _active_ products.

**Response:**
200 OK - List of all products

### GET: "/product/{productId}"

Returns a product object.

Response:
200 OK - returns the product object
404 ResourceNotFound - Product or Brand with given ID does not exist.

### POST: "/product"

Creates a Product entry given the following:

Input:
{
"brandId": {brandId},
"category: "{category name}",
"productName": "{productName)"
}

**Response:**
200 OK - returns the brand object
400 Bad Request - invalid/missing fields
400 Bad Request - Fields are not right type

### PUT: "/product/{productId}"

Updates a product object. Input is the same format as the above. To modify
the status of a product, see "PATCH: "/product/(productId}/archive"").

**Response:**
200 OK - returns the brand object
404 Resource Not Found: Product with given ID does not exist.
400 Bad Request - invalid/missing fields
400 Bad Request - Fields are not right type

### PATCH: "/product/{productId}/archive"

TODO: add all tables and bring them here
