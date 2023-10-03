package nclan.ac.ahart.quiz;

import nclan.ac.ahart.useful.FileAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static nclan.ac.ahart.useful.FileAccess.readData;

/**
 * Quiz program. Asks the user for their name, asks questions and then displays the
 * total score to the user.This Quiz makes use of a Question class to keep question and answer.
 *
 * @author alan.hart
 * 03/09/2023
 */
public class Quiz {

    /**
     * if the user does not enter a name then they will be called this.
     */
    protected String DEFAULT_NAME = "Anon";
    /**
     * This is the default input filename for the application
     */
    protected String inputFilename = "/resources/scot-questions.csv";
    /**
     * Since each question could have a different number of points need to track max possible
     */
    private int maxPossibleScore = 0;
    /**
     * The questions for this quiz will be held here.
     */
    public ArrayList<Question> quizQuestions = new ArrayList<>();

    /**
     * Main entry point for the program.
     *
     * @param args None expected.
     */
    public static void main(String[] args) {
        //call the Quiz constructor
        Quiz myQuiz = new Quiz();

        //kick off the quiz.
        myQuiz.start();
    }

    /**
     * Constructor for the quiz. Set up the quiz. Create the instances of the questions. All question information is
     * read in either from the csv file or database.
     */
    public Quiz() {
        try {
            //quizQuestions = loadAndParseDataFromDatabase();
            quizQuestions = loadAndParseDataFromFile();
        } catch (Exception e) {
            System.err.println("Aborting program!");
            System.err.println("Exception thrown" + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Connect to database, read in all rows/questions and disconnect from database.
     *
     * @return ArrayList of Question objects.
     * @throws Exception Any SQLExceptions thrown will be passed to caller
     */
    private ArrayList<Question> loadAndParseDataFromDatabase() throws Exception {

        Database dbQuiz = new Database();
        dbQuiz.connect();
        ArrayList<Question> localQuestions = dbQuiz.getAllRows();
        dbQuiz.disconnect();

        //next line just for testing
        //dbQuiz.insertRow(1,"Who ami","Alan",2,"");
        return localQuestions;
    }

    /**
     * Open file. read all rows, parse into question objects and close file
     *
     * @return ArrayList of Question objects.
     * @throws Exception Any Exceptions thrown will be passed to caller
     */
    private ArrayList<Question> loadAndParseDataFromFile() throws Exception {
        //read all data from the file
        ArrayList<String> rows = readData(inputFilename);
        ArrayList<Question> localQuestions = new ArrayList<>();

        //for each element in the arraylist split it into name and rating. Count those with a rating >= RATING_LIMIT
        for (String row : rows) {
            String[] details = row.split(",");

            //switch on the value found in the first column
            int typeOfQ = Integer.parseInt(details[0]);

            switch (typeOfQ) {
                case 1 -> {
                    //textual question
                    int points = Integer.parseInt(details[3]);
                    Question newQ = new TextQuestion(details[1], details[2], points);
                    localQuestions.add(newQ);
                }
                case 2 -> {
                    //true/false question
                    boolean answer = Boolean.parseBoolean(details[2]);
                    Question newTF = new TrueFalseQuestion(details[1], answer);
                    localQuestions.add(newTF);
                }
                case 3 -> {
                    //multiple-choice question
                    String[] choices = details[4].split(";");
                    int points = Integer.parseInt(details[3]);
                    Question newMC = new MultipleChoiceQuestion(details[1], details[2], choices, points);
                    localQuestions.add(newMC);
                }
                default -> System.err.println("Question type not recognised.");
            }

        }
        return localQuestions;
    }


    /**
     * Start the quiz. Gets the username, asks the questions and keeps track of the current score and maximum
     * possible score. The loop to ask if the user wants to play again is here. The player is asked if they are the
     * same player retrying or if it is a different player attempting the quiz.
     */
    public void start() {
        boolean firstRun = true;
        boolean runAgain = true;

        Player livePlayer = new Player(getUserDetails(), "");

        while (runAgain) {
            //if we are rerunning the quiz check if the same person or someone else
            if (firstRun) {
                System.out.println("Welcome " + livePlayer.getFirstName() + " to the quiz of the century!");
            } else {
                //running the quiz for another attempt
                boolean who = yesNoUserResponse("Is " + livePlayer.getFirstName() + " still playing?");
                if (who) {
                    System.out.println("Try and beat your previous score.");
                } else {
                    livePlayer.setFirstName(getUserDetails());
                    System.out.println("Welcome " + livePlayer.getFirstName() + " to the quiz of the century!");
                }
            }

            //shuffle the questions before asking and reset max possible score
            Collections.shuffle(this.quizQuestions);
            maxPossibleScore = 0;

            //ask the questions and keep track of the score
            int total = 0;
            for (int i = 0; i < quizQuestions.size(); i++) {
                System.out.print("Q" + (i + 1) + ": ");
                total = total + askQuestion(quizQuestions.get(i));
            }

            //record latest score and print results
            livePlayer.recordScore(total);
            System.out.println(livePlayer.getFirstName() + " you scored " + livePlayer.getLastScore() + "/" + maxPossibleScore);


            //write results to file.
            try {
                FileAccess.writeData(livePlayer.getFirstName() + ".csv", livePlayer.getFirstName() + "," + total);
            } catch (Exception e) {
                System.err.println("Sorry, unable to save to file at this time.");
            }

            // do you want to rerun?
            runAgain = yesNoUserResponse("Do you want to rerun the quiz?");
            firstRun = false;
        }
        System.out.println("Thank you for playing " + livePlayer.getFirstName());
        System.out.println("Your last score: " + livePlayer.getLastScore());
        System.out.println("Your best score: " + livePlayer.getHighestScore());
    }

    /**
     * The details of the person playing the quiz. If no name is supplied default name is set.
     *
     * @return The name of the person playing the quiz.
     */
    private String getUserDetails() {
        Scanner input = new Scanner(System.in);

        //get the user's name
        System.out.println("What is your name?");
        String name = input.nextLine();

        //if the user never entered a name give them a default name
        if (name.isEmpty())
            name = DEFAULT_NAME;

        return name;
    }

    /**
     * Ask the user to respond to a question that will have a yes or no answer. If nothing is entered or if the response
     * starts with n or N then it is assumed the player entered a negative response.
     *
     * @param query A question that the user will answer yes or no to
     * @return True to if response was yes, Yes, y or Y, false for anything else
     */
    private boolean yesNoUserResponse(String query) {
        Scanner input = new Scanner(System.in);

        //get the user's response
        System.out.println(query + "\n(yes/no)");
        String response = input.nextLine();

        //if the user never entered a name give them a default name
        if (!response.isEmpty()) {
            response = response.toLowerCase();
            return (response.startsWith("y"));
        }

        return false;
    }

    /**
     * Ask the user a question.
     *
     * @param q Instance of the Question class. This contains the question and the correct answer
     * @return integer value of 1 for correct answer else 0 for wrong answer.
     */
    public int askQuestion(Question q) {
        Scanner input = new Scanner(System.in);
        int score = 0;

        System.out.println(q.getQuestion());

        //get the input user. Assumes everything will be a String type
        String answer = input.nextLine();

        //update maximum possible score
        maxPossibleScore = maxPossibleScore + q.getPoints();

        //if the user entered nothing then give them zero
        if (answer.length() < 1) {
            System.out.println("No answer supplied. 0 points");
        } else {
            if (q.isCorrect(answer)) {
                System.out.println(answer + " is the correct answer. " + q.getPoints() + " points.");
                score = q.getPoints();
            } else {
                System.out.println(answer + " is the wrong answer. 0 points.");
            }
        }
        return score;
    }
}
