package nclan.ac.ahart.quiz;

/**
 * Text based question. This duplicates the functionality of the Question class. This class was added to provide
 * greater clarity in the naming of questions.
 */
public class TextQuestion extends Question {
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
