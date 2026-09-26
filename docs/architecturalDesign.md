# System Architecture

This is shown as a flowchart to focus more on how components are grouped together.
This is mostly an overview for the planned interactions, future documentation to
be written is planned to focus on how components will interact: in particular
zooming into the services section.

```mermaid
flowchart TD
  subgraph Controllers
    ProductController
    BrandController
    ColorController
    SizeController
    VariantsController
    InventoryController
    StoreController
  end
  subgraph Services
    ProductService
    BrandService
    ColorService
    SizeService
    VariantsService
    InventoryService
    StoreService
  end
  subgraph Repositories
    ProductRepository
    BrandRepository
    ColorRepository
    SizeRepository
    VariantsRepository
    InventoryRepository
    StoreRepository
  end
  subgraph Entities
    Product
    Brand
    Color
    Size
    Variants
    Inventory
    Store
  end

  ProductController --> ProductService
  BrandController --> BrandService
  ColorController --> ColorService
  SizeController --> SizeService
  VariantsController --> VariantsService
  InventoryController --> InventoryService
  StoreController --> StoreService

  %% Service Associations
  ProductService --> ProductRepository
  ProductService --> BrandService
  BrandService --> BrandRepository
  ColorService --> ColorRepository
  SizeService --> SizeRepository
  VariantsService --> VariantsRepository
  VariantsService --> ProductService
  VariantsService --> ColorService
  VariantsService --> SizeService
  InventoryService --> InventoryRepository
  InventoryService --> VariantsService
  InventoryService --> StoreService
  StoreService --> StoreRepository

  %% Repository Associations
  ProductRepository --> Product
  BrandRepository --> Brand
  ColorRepository --> Color
  SizeRepository --> Size
  VariantsRepository --> Variants
  InventoryRepository --> Inventory
  StoreService --> Store
```
