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

## Design
The code matches the design documents supplied in the tutorials. There
The code has been designed to be easy to maintain and expand. 

## Usage
The Quiz is run from the command line. Run the quiz by typing,

*java -jar Quiz.jar*