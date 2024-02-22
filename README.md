# PatternsTask

> This repository contains the solution for the CW №1 of the course. This is a simple souvenir control system
> implemented in Java. The system allows you to manage souvenirs and their manufacturers.

## Table of Contents

- [Requirements](#requirements)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Selected design patterns](#selected-design-patterns)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Testing](#testing)

## Requirements

Souvenirs

In the file storage (a set of files of arbitrary structure), there is information about souvenirs and their 
manufacturers. 

#### For souvenirs, the following information needs to be stored:
- Name;
- Manufacturer's details;
- Release date;
- Price.

#### For manufacturers, the following information needs to be stored:
- Name;
- Country.

#### Implement the following features:
- Add, edit, view all manufacturers and all souvenirs.
- Display information about souvenirs from a specified manufacturer.
- Display information about souvenirs made in a specified country.
- Display information about manufacturers whose prices for souvenirs are less than a specified amount.
- Display information about all manufacturers and, for each manufacturer, display information about all souvenirs 
they produce.
- Display information about manufacturers of a specified souvenir made in a specified year.
- For each year, display a list of souvenirs made in that year.
- Delete a specified manufacturer and their souvenirs.

P.S. Do not use databases (only files). To store data in the program use collections. 
Use streams for processing (or not used if it's simpler without them).
Note: Different manufacturers may have souvenirs with the same names. For example, the souvenir "Signature Mug" 
may be from the manufacturers "National Shipbuilding University" and "PrivatBank".

## Technologies Used

The application utilizes the following technologies:

- Java 21
- Maven
- TestNG
- Lombok

## Project Structure

The project has a Three-Tier Architecture:

| Layer                                  | Responsibilities                                                | 
|----------------------------------------|-----------------------------------------------------------------|
| **Presentation layer (Controllers)**   | Accepts requests from clients and send response.                |
| **Application logic layer (Services)** | Provide validation and logic to operate on the data.            |
| **Data access layer (Repositories)**   | Represents a connection between "database" and the application. |

The project contains following classes:

- `org.vitaliistf.souvenirs` package contains the main class for the application.
- `org.vitaliistf.souvenirs.config` package includes class for reading app properties.
- `org.vitaliistf.souvenirs.controller` package contains a controller, which is a facade for the whole system.
- `org.vitaliistf.souvenirs.menu` package includes classes for user interface.
- `org.vitaliistf.souvenirs.model` package contains model classes.
- `org.vitaliistf.souvenirs.repository` package includes repositories.
- `org.vitaliistf.souvenirs.service` package includes services.
- `org.vitaliistf.souvenirs.util` package contains user interface input reader.
- `org.vitaliistf.souvenirs.validation` package contains validators.

For more information about classes, please generate documentation.

## Selected design patterns

- `Singleton` is used for repositories.
- `Command` is used for menu commands.
- `MVC` is used to interact with the system.
- `Facade` - the controller is a facade for the whole system.
- `Template method` is used for generating table views.
- `Repository` is used for data interactions.
- `Object pool` is used for command retrieving.
- `Prototype` is used for models, in order to separate the actual objects 
in repositories from object existing on the client side.

## Getting Started

1. Clone the repository.
2. Change configuration in application.properties if needed.
3. Build and run the application.

> Note that there is already pre-saved objects in files of resources folder.

## Usage

Follow the on-screen menu to perform various operations.

## Testing

TestNG is used for testing the application. Tests require their own properties in application.properties.