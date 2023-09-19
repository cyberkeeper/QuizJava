package nclan.ac.ahart.quiz;

public class TextQuestion extends Question{
    /**
     * Constructor. Use this to set up the instance of a question. This is the only way to set the question and the
     * answer. Makes the answer lowercase for ease of checking.
     *
     * @param question A text based question
     * @param answer   A text based answer
     * @param points   The number of points if the question is answered correctly
     */
    public TextQuestion(String question, String answer, int points) {
        super(question, answer, points);
    }
}
