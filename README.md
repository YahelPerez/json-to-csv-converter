# Back End in Java for Information Processing

This project is a Java-based command-line application designed to automate the process of converting structured JSON files into CSV format. This project is built using Gradle.

---
## Sprint 2 Features

* **JSON Reading:** Reads and parses JSON files containing an array of objects.
* **CSV Writing:** Writes the parsed data into a well-formatted CSV file with headers.
* **Error Handling:** Gracefully handles common errors like file-not-found and invalid JSON format.
* **Gradle Build:** Uses the Gradle Wrapper for easy and consistent project building.
* **Unit Tested:** Includes JUnit 5 tests to validate the core JSON reading logic.

---
## Prerequisites

* Java Development Kit (JDK) 17 or newer.
* Gradle Wrapper (included in the repository).

---
## How to Run the Program

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YahelPerez/json-to-csv-converter.git](https://github.com/YahelPerez/json-to-csv-converter.git)
    cd json-to-csv-converter
    ```

2.  **Prepare your data:**
    * Create a sample `input.json` file in the root of the project.

3.  **Run the project using the Gradle Wrapper:**
    * On Windows, open a terminal (CMD or Git Bash) and run:
        ```bash
        gradlew.bat run
        ```
    * On macOS or Linux, run:
        ```bash
        ./gradlew run
        ```
    * This command will automatically download dependencies, compile the code, and run the program. An `output.csv` file will be generated.

---
## ## Testing

This project includes unit tests for the `JsonReader` class. To run the tests, you can either use your IDE (right-click on the `JsonReaderTest.java` file and select "Run 'JsonReaderTest'") or execute the following command in your terminal:

* On Windows:
    ```bash
    gradlew.bat test
    ```
* On macOS or Linux:
    ```bash
    ./gradlew test
    ```
    * This command will automatically download dependencies, compile the code, and run the program. An `output.csv` file will be generated.