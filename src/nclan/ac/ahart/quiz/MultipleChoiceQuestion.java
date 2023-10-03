package nclan.ac.ahart.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which defines a multiple-choice question. The question is flexible and can a variety of
 * different answers that the player can select from. The player selects their answer by entering the
 * number associated with the answer they wish to submit. The options are shuffled when the question
 * is created.
 *
 * @author ahart
 */

public class MultipleChoiceQuestion extends Question {
    /**
     * Contains the correct answer and the wrong answers
     */
    protected List<String> options = new ArrayList<>();

    /**
     * Index in the options list of the correct answer, this is set after list is shuffled.
     */
    private final int correctIndex;

    /**
     * Constructor. Use this to set up the instance of a question. This is the only way to set the question and the
     * answer. Makes the answer lowercase for ease of checking.
     *
     * @param question A text based question
     * @param answer   A text based answer
     * @param options  Possible wrong answers
     * @param points   The number of points if the question is answered correctly
     */
    public MultipleChoiceQuestion(String question, String answer, String[] options, int points) {
        super(question, answer, points);

        //populate list with all options and the correct answer, then randomise the list
        for (String choice : options) {
            this.options.add(choice);
        }
        this.options.add(answer);
        Collections.shuffle(this.options);

        //find index of correct answer and store for later
        this.correctIndex = this.options.indexOf(answer);
    }

    /**
     * Returns the question for this instance.
     *
     * @return Question to be asked
     */
    @Override
    public String getQuestion() {
        StringBuilder output = new StringBuilder(super.getQuestion() + "\n");

        //build up the options the user can pick from, allows multiple choice questions with more or less options.
        StringBuilder userOptions = new StringBuilder("(Answer ");
        //add on each option prefixed by a number
        for (int i = 0; i < this.options.size(); i++) {
            output.append(i + 1).append(": ");
            output.append(options.get(i)).append("\n");
            userOptions.append((i + 1)).append(",");
        }
        //finish up the user options, remove last comma and add closing bracket
        userOptions.deleteCharAt(userOptions.length() - 1).append(")");

        output.append(userOptions);

        return output.toString();
    }

    /**
     * Check if the supplied answer is the correct answer. Makes the supplied answer lowercase for checking.
     *
     * @param userSays What ever the user answered in response to the question
     * @return True is the user answered correctly, else returns false
     */
    @Override
    public boolean isCorrect(String userSays) {
        boolean isCorrect = false;
        try {
            //change user answer to int and subtract 1 as index starts at zero.
            int userIndex = Integer.parseInt(userSays) - 1;
            if (correctIndex == userIndex)
                isCorrect = true;
        } catch (Exception e) {
            isCorrect = false;
        }
        return isCorrect;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                super.toString() +
                "options=" + options +
                "correct index=" + correctIndex +
                '}';
    }
}
