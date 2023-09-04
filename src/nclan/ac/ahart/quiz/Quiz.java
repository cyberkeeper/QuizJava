package nclan.ac.ahart.quiz;

import java.util.ArrayList;
import java.util.Scanner;

import static nclan.ac.ahart.useful.FileAccess.readData;

/**
 * Quiz program. Asks the user for their name, asks three questions and then displays the
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
     * read in from the csv file.
     */
    public Quiz() {
        try {
            //read all data from the file
            ArrayList<String> rows = readData(inputFilename);

            //for each element in the arraylist split it into name and rating. Count those with a rating >= RATING_LIMIT
            for (String row : rows) {
                String[] details = row.split(",");

                //switch on the value found in the first column
                int typeOfQ = Integer.parseInt(details[0]);

                switch (typeOfQ) {
                    case 1 -> {
                        //textual question
                        int points = Integer.parseInt(details[3]);
                        Question newQ = new Question(details[1], details[2], points);
                        quizQuestions.add(newQ);
                    }
                    case 2 -> {
                        //true/false question
                        boolean answer = Boolean.parseBoolean(details[2]);
                        Question newTF = new TrueFalseQuestion(details[1], answer);
                        quizQuestions.add(newTF);
                    }
                    case 3 -> {
                        //multiple-choice question
                        String[] choices = details[4].split(";");
                        int points = Integer.parseInt(details[3]);
                        Question newMC = new MultipleChoiceQuestion(details[1], details[2], choices, points);
                        quizQuestions.add(newMC);
                    }
                    default -> System.err.println("Question type not recognised.");
                }

            }
        } catch (Exception e) {
            System.err.println("Aborting program!");
            System.err.println("Exception thrown" + e.getMessage());
            System.exit(1);
        }
    }


    /**
     * Start the quiz
     */
    public void start() {
        Scanner input = new Scanner(System.in);

        //get the user's name
        System.out.println("What is your name?");
        String name = input.nextLine();

        //if the user never entered a name give them a default name
        if (name.length() < 1)
            name = DEFAULT_NAME;

        System.out.println("Welcome " + name + " to the quiz of the century!");

        //ask the questions and keep track of the score
        int total = 0;
        for (int i = 0; i < quizQuestions.size(); i++) {
            System.out.print("Q" + (i + 1) + ": ");
            total = total + askQuestion(quizQuestions.get(i));
        }

        System.out.println(name + " you scored " + total + "/" + maxPossibleScore);
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
        //System.out.println(q);

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
