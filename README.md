# Quiz

## Introduction
This is a simple Quiz application which uses the Object-Oriented programming paradigm.
Questions can be of the following types,
- text based question
- true/false question
- multiple choice question

Data for the questions are loaded in from a csv file. Demo code
is presented but commented out to load questions from a MySQL 
database.

The Quiz application is currently a console application. The code could be extended to include a Java or JavaFX graphical user interface (GUI).

### CSV file format
The format of the csv file is,

| Question type       | Question | Answer | Points        | Other options       |
|---------------------|----------|--------|---------------|---------------------|
| 1 - text based      | Text     | Text   | Number        |                     |
| 2 - true/ false     | Text     | Text   | Defaults to 1 |                     |
| 3 - multiple choice | Text     | Text   | Number        | ; separated options |


### Database format
XAMPP was installed to test database connectivity with a MySQL compatible database, MariaDB v10.4.27. A database called quiz was created. The table to hold the questions was created using the following SQL,

`CREATE TABLE `quiz`.`question` (`type` INT(4) NOT NULL , `question` VARCHAR(128) NOT NULL , `answer` VARCHAR(128) NOT NULL , `points` INT(4) NOT NULL , `multichoice` VARCHAR(256) NULL ) ENGINE = InnoDB;`


## Design
The code matches the design documents supplied in the tutorials. The code has been designed to be easy to maintain and expand. 


## Environment
The code was written in JetBrains IntelliJ Community edition v2023.2. The SDK was Oracle Open JDK20, level 20 "No new language features". The operating system used was Microsoft Windows 10 and 11. The Jar file was tested by running in a non-adminisrator Powershell mode.


## Usage
The Quiz is run from the command line. Run the quiz by typing,

*java -jar Quiz.jar*
