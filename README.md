# Retail Inventory System

A REST API backend for managing clothing inventory across stores through the tracking of products (and their variants),
stores, inventory, and their movements in one centralized system.

This is a system that aims to provide users with a centralized master list for clothing products. It aims to solve the problem of
inventory visibility across two stores through the implementation of RESTful APIs.

## Features

- Add store branches, Products (and their variants), and track Inventory
- Log Inventory Movements to track inventory levels while providing historical context
- Export to Excel to be able to manipulate and analyse inventory data
- Track Inventory at the variant level per store location

## Techstack

- Java 21
- Spring Boot
- Maven
- MySQL

## How to run it locally

Dependencies: Maven, Java 21, MySQL

1. Clone this repository
2. Configure the database:
   - Head to src/main/resources/application.properties,
     rename spring.datasource.url to your empty database
   - Create a file named application-local.properties,
     set your username (spring.datasource.username) and password
     (spring.datasource.password).
3. run using `mvn spring-boot:run`.
