# IntentBi-Assignment

_
# Java Spring Boot Excel Upload Application

This Java Spring Boot application allows users to upload Excel files via a REST endpoint and processes the uploaded file to save its data into a database table. Basic validations are included to ensure data integrity, and error handling is implemented to capture and return errors to the API caller.

## Features

- Upload Excel file via HTTP POST method.
- Process uploaded file and save data to a database table.
- Basic validations to prevent accepting invalid data types.
- Capture and return errors to the API caller.
- Display success message upon successful processing of the file.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Maven
- Spring Boot
- MySQL or any other preferred database

## Installation

1. Clone the repository:

    ```
    git clone https://github.com/Shubham17121999/java-spring-boot-excel-upload.git
    ```

2. Navigate to the project directory:

    ```
    cd java-spring-boot-excel-upload
    ```

3. Build the project using Maven:

    ```
    mvn clean install
    ```

4. Run the application:

    ```
    java -jar target/java-spring-boot-excel-upload-0.0.1-SNAPSHOT.jar
    ```

5. Access the application at `http://localhost:3000`.

## Usage

1. Upload Excel file via the provided REST endpoint.

2. Upon successful upload, the application will process the file and save the data to the database table.

3. If any errors occur during processing, they will be captured and returned to the API caller.

4. If no errors occur, a success message will be displayed.

## API Endpoints

- **POST** `/upload` - Endpoint to upload Excel file.

## Sample Excel File

A sample Excel file can be downloaded [here](https://www.example.com/sample-excel-file.xlsx).

## Database Table

The database table will be created with fields corresponding to the columns in the Excel file. Data types will be retained as per the Excel file.

## Contributors

-Shubham Kulkarni (@Shubham17121999)


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- 
- Special thanks to the Spring Boot and Java community for their invaluable resources and support.