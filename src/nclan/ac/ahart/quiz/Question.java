package nclan.ac.ahart.quiz;

/**
 * Class which defines a textual question. This is the super class for all types of question. If a textual
 * question is to be used, use a TextQuestion instance. The TextQuestion is a child class of Question and was
 * added to give better structure and clearer naming convention.
 *
 * @author ahart
 */
public abstract class Question {

    private final String question;
    private final String answer;
    private int points = 1;

    /**
     * Returns the question for this instance.
     *
     * @return Question to be asked
     */
    public String getQuestion() {
        return question + " (" + points + " pts) ?";
    }

    /**
     * The correct answer for this question
     *
     * @return Correct answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * The number of points for the correct answer for the current question
     *
     * @return Number of points to be awarded for correct answer
     */
    public int getPoints() {
        return points;
    }

    /**
     * Constructor. Use this to set up the instance of a question. This is the only way to set the question and the
     * answer. Makes the answer lowercase for ease of checking.
     *
     * @param question A text based question
     * @param answer   A text based answer
     */
    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase();
    }

    /**
     * Constructor. Use this to set up the instance of a question. This is the only way to set the question and the
     * answer. Makes the answer lowercase for ease of checking.
     *
     * @param question A text based question
     * @param answer   A text based answer
     * @param points   The number of points if the question is answered correctly
     */
    public Question(String question, String answer, int points) {
        this(question, answer);
        this.points = points;
    }

    /**
     * Check if the supplied answer is the correct answer. Makes the supplied answer lowercase for checking.
     *
     * @param userSays What ever the user answered in response to the question
     * @return True is the user answered correctly, else returns false
     */
    public boolean isCorrect(String userSays) {
        //check that some sort of answer was supplied, some text changed
        if (userSays != null && userSays.length() > 0) {
            //make user's answer lowercase and compare with the correct answer
            userSays = userSays.toLowerCase();
            return userSays.equals(getAnswer());
        }
        return false;
    }


    /**
     * Return question, answer and points value.
     * @return String for debug purposes.
     */
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}
