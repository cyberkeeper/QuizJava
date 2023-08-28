/**
 * Class which defines a question.
 *
 * @author ahart
 */
public class Question {

    private String question;
    private String answer;


    /**
     * Returns the question for this instance.
     *
     * @return Question to be asked
     */
    public String getQuestion() {
        return question;
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
     * Check if the supplied answer is the correct answer. Makes the supplied answer lowercase for checking.
     *
     * @param userSays What ever the user answered in response to the question
     * @return True is the user answered correctly, else returns false
     */
    public boolean isCorrect(String userSays) {
        //check that some sort of answer was supplied
        if (userSays != null && userSays.length() > 0) {
            //make user's answer lowercase and compare with the correct answer
            userSays = userSays.toLowerCase();
            if (userSays.equals(getAnswer()))
                return true;
        }
        return false;
    }
}
