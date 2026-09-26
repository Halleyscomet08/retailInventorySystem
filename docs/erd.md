# Entity Relationship Diagram

```mermaid
  erDiagram
    Brand {
        Long brandId PK
        String brandName
        Enum(ACTIVE,ARCHIVED) status
    }
    Product {
        Long productId PK
        Brand brandId FK
        String productName
        Enum(ACTIVE,ARCHIVED) status
    }
    Color {
        Long colorId PK
        String colorName
    }
    Size {
        Long sizeId PK
        String sizeName
    }
    Variant {
        Long variantId PK
        Long productId FK
        Long colorId FK
        Long sizeId FK
        Long Price
    }
    Inventory {
        Long variantId PK, FK
        Long storeId PK, FK
        Long quantity
    }
    Store {
        Long storeID PK
        String storeName
    }
    Brand ||--o{ Product : owns
    Color ||--o{ Variant : has
    Size ||--o{ Variant : has
    Product ||--o{ Variant : contains
    Variant ||--|{ Inventory : tracks
    Store ||--|{ Inventory : "stores"


```
