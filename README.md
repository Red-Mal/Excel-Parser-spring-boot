
# Excel to SQL Converter using Spring Boot

This Spring Boot application reads customer data from an Excel file and generates SQL INSERT queries to insert the data into a database.

## Features

- Reads customer data from an Excel file.
- Generates parameterized SQL INSERT queries.
- Writes SQL queries to a file.

## Prerequisites

- Java 8+
- Maven
- An Excel file with customer data
- A database (e.g., MySQL, PostgreSQL) and its configuration

## Getting Started

1. Clone this repository to your local machine.
2. Open the project in your favorite Java IDE.
3. Update the database configuration in `application.properties`.
4. Place your Excel file with customer data in a suitable location.
5. Run the Spring Boot application.
6. The customer data will be read from the Excel file, and SQL queries will be generated and saved to a file.

## Configuration

- Database configuration: Edit the `spring.datasource` properties in `src/main/resources/application.properties` to match your database settings.

## Usage

1. Place your Excel file in the `src/main/resources` directory.
2. Run the Spring Boot application.
3. SQL queries will be generated and saved to `output.sql` in the project root directory.

## Contributing

Contributions are welcome! Please feel free to open a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- [Apache POI](https://poi.apache.org/) - Library for working with Microsoft Office documents.

