package nclan.ac.ahart.quiz;

import nclan.ac.ahart.useful.Person;

import java.util.*;

/**
 * Player class keeps track of the name and scores of the player playing the quiz.
 *
 * @author ahart
 */
public class Player extends Person {

    /**
     * Store all the scores for this player in a stack
     */
    private Stack<Integer> previousScores = new Stack<>();

    /**
     * Simple constructor takes first name and surname
     *
     * @param firstName first name
     * @param surname   surname
     */
    public Player(String firstName, String surname) {
        super(firstName, surname);
    }

    /**
     * Constructor which calls takes in the name and also a csv formatted string of previous scores. If there is a
     * problem with the scores format then the stack will be populated with valid values. Badly formatted or wrong
     * data types will be ignored.
     *
     * @param firstName
     * @param surname
     * @param scores    String of comma separated integer values.
     */
    public Player(String firstName, String surname, String scores) {
        this(firstName, surname);

        //split the string by commas, parse to Integers and add to stack
        String[] csvScores = scores.split(";");
        for (String val : csvScores) {
            try {
                int iVal = Integer.parseInt(val);
                previousScores.add(iVal);
            } catch (NumberFormatException nfe) {
                System.err.println("Problem parsing previous score information.");
            }

        }
    }

    /**
     * Add a new score to the stack
     *
     * @param score Latest score
     */
    public void recordScore(int score) {
        previousScores.add(score);
    }

    /**
     * Query what was the most recent score for the player. Peeks at the stack to see latest value
     *
     * @return most recent score
     */
    public int getLastScore() {
        if (previousScores.size() > 0)
            return previousScores.peek();
        else
            return 0;
    }

    /**
     * Get the highest score recorded by the player.
     *
     * @return the highest score to date
     */
    public int getHighestScore() {
        if (previousScores.size() > 0) {
            //clone the stack so we don't change the original
            Stack<Integer> working = (Stack<Integer>) previousScores.clone();

            //pop every element in the stack to see if it is the new maximum score.
            int maxValue = working.pop();
            while (!working.isEmpty()) {
                int currentValue = working.pop();
                if (currentValue > maxValue) {
                    maxValue = currentValue;
                }
            }
            return maxValue;
        } else
            return 0;
    }

    @Override
    public String toString() {
        return "Player: " + super.toString() +
                " previousScores=" + previousScores;
    }
}
