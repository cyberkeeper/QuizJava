import java.util.ArrayList;
import java.util.Scanner;

/**
 * Quiz program. Asks the user for their name, asks three questions and then displays the
 * total score to the user.This Quiz makes use of a Question class to keep question and answer.
 *
 * @author alan.hart
 * 28/08/2023
 */
public class Quiz {

    /**
     * if the user does not enter a name then they will be called this.
     */
    protected String DEFAULT_NAME = "Anon";

    /**
     * The questions for this quiz will be held here.
     */
    public ArrayList<Question> quizQuestions = new ArrayList();
    /**
     * The questions to ask the user.
     */
    protected String[] questions = {"What is the capital of Scotland?", "What is the longest loch?", "What is the highest mountain in Scotland?"};

    /**
     * The correct answers to the questions. All answers in lowercase so that user doesn't need to worry about capitalisation
     */
    protected String[] answers = {"edinburgh", "loch ness", "ben nevis"};

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
     * Constructor for the quiz. Set up the quiz. Create the instances of the questions.
     */
    public Quiz() {
        //check how many question instances to create and use a loop to create all questions
        int numQ = questions.length;
        for (int i = 0; i < numQ; i++) {
            Question newQ = new Question(questions[i], answers[i]);
            quizQuestions.add(newQ);
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

        System.out.println(name + " you scored " + total + "/" + answers.length);
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

        //if the user entered nothing then give them zero
        if (answer.length() < 1) {
            System.out.println("No answer supplied. 0 points");
        } else {
            if (q.isCorrect(answer)) {
                System.out.println(answer + " is the correct answer. 1 point.");
                score = 1;
            } else {
                System.out.println(answer + " is the wrong answer. 0 points.");
            }
        }
        return score;
    }
}
