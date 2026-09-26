# Use Case Diagram

```mermaid
usecase-beta
actor Salesperson
actor Manager
systemBoundary inventorySystem
Adjust("Log Stock Adjustment")
ManageInventory("Manage Inventory")
ViewVariants("View Product Variants")
ViewReports("View Reports")
ManageProducts("Manage Products")
ManageVariants("Manage Variants")
ManageBrands("Manage Brands")
ManageStores("Manage Stores")
ArchiveBrands("Archive Brands")
ArchiveProducts("Archive Products")
ArchiveVariants("Archive Variants")
end
Salesperson --> Adjust
Salesperson --> ManageInventory
Salesperson --> ViewVariants
Manager --|> Salesperson
Manager --> ManageBrands
Manager --> ManageStores
Manager --> ManageProducts
Manager --> ManageVariants
Manager --> ViewReports
Manager --> ArchiveBrands
Manager --> ArchiveProducts
Manager --> ArchiveVariants

ArchiveBrands ..> : include ArchiveProducts
ArchiveProducts ..> : include ArchiveVariants
```
