package nclan.ac.ahart.quiz;

/**
 * Class which defines a true or false question. By default, all this type of question is worth 1 point.
 *
 * @author ahart
 */

public class TrueFalseQuestion extends Question {
    /**
     * Constructor call the super class construction after
     *
     * @param question The question to answer
     * @param answer   The answer expected to be true or false
     */
    public TrueFalseQuestion(String question, Boolean answer) {
        super(question, answer.toString(), 1);
    }

    /**
     * Returns the question for this instance.
     *
     * @return Question to be asked
     */
    @Override
    public String getQuestion() {
        return super.getQuestion() + "\n(Answer true or false)";
    }
}
