package nclan.ac.ahart.quiz;

import java.util.*;

/**
 * Class which defines a multiple-choice question.
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

        //add on each option prefixed by a number
        for (int i = 0; i < this.options.size(); i++) {
            output.append(i + 1).append(": ");
            output.append(options.get(i)).append("\n");
        }

        output.append("(Answer 1,2,3,4)");

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
