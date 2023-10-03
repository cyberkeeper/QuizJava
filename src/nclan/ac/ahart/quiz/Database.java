package nclan.ac.ahart.quiz;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database class. Contains methods for connecting to, retrieving from and inserting into the question table in a
 * database called quiz. The methods are specific to a database and tables for the Quiz application.
 *
 * @author ahart
 */
public class Database {

    private Connection conn = null;

    /**
     * Connect to the database. Sets the private class attribute when successful connection made.
     */
    public void connect() {
        String connectionUrl = "jdbc:mysql://localhost:3306/quiz?serverTimezone=UTC";

        try {
            //uses default user id and password. Not a great idea!!
            conn = DriverManager.getConnection(connectionUrl, "root", "");

        } catch (
                SQLException e) {
            // handle the exception
            System.err.println("oops!" + e.getMessage());
        }
    }

    /**
     * Disconnect from the database.
     */
    public void disconnect() {
        try {
            // remember to close the connection
            conn.close();
        } catch (
                SQLException e) {
            // handle the exception
            System.err.println("oops!" + e.getMessage());
        }
    }

    /**
     * Read all rows from the database table and create Question instances from each. If the question type is not
     * recognised then it will be ignored. Note: you can't replace the table name in a prepared statement, it has to
     * be explicit.
     *
     * @return ArrayList containing all created questions
     * @throws SQLException provide error if there is a database error
     */
    public ArrayList<Question> getAllRows() throws SQLException {
        String sqlSelectAllPersons = "SELECT * FROM question";

        PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
        // executeQuery used for queries
        ResultSet rs = ps.executeQuery();

        ArrayList<Question> quizQuestions = new ArrayList<>();
        // output all returned data to the console
        while (rs.next()) {
            int typeOfQ = rs.getInt("type");
            String quest = rs.getString("question");
            String ans = rs.getString("answer");
            int pts = rs.getInt("points");

            switch (typeOfQ) {
                case 1 -> {
                    //textual question
                    Question newQ = new TextQuestion(quest, ans, pts);
                    quizQuestions.add(newQ);
                }
                case 2 -> {
                    //true/false question
                    boolean answer = Boolean.parseBoolean(ans);
                    Question newTF = new TrueFalseQuestion(quest, answer);
                    quizQuestions.add(newTF);
                }
                case 3 -> {
                    //multiple-choice question
                    String[] choices = rs.getString("multichoice").split(";");
                    Question newMC = new MultipleChoiceQuestion(quest, ans, choices, pts);
                    quizQuestions.add(newMC);
                }
                default -> System.err.println("Question type not recognised.");
            }

            // just a debug statement to check what was loaded in
            System.out.println("Q:" + quest);
        }
        return quizQuestions;
    }


    /**
     * Insert a new question into the database
     *
     * @param typeOfQ     Indicates Text, TrueFalse or Multichoice question
     * @param quest       Question to ask
     * @param ans         Answer to question
     * @param pts         The number of points for a correct answer, if nothing supplied this will be 1
     * @param multichoice Only used for multiple choice questions. Supply the wrong answers in ; separated string format
     * @throws SQLException provide details of what went wrong
     */
    public void insertRow(int typeOfQ, String quest, String ans, int pts, String multichoice) throws SQLException {
        String sqlInsertQuestion = "INSERT INTO question (answer, multichoice, points, question, type) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsertQuestion);

        ps.setString(1, ans);
        ps.setString(2, multichoice);
        ps.setInt(3, pts);
        ps.setString(4, quest);
        ps.setInt(5, typeOfQ);

        // executeUpdate used for create, drop, insert, update, delete etc...
        int row = ps.executeUpdate();

        //could update the prepared statement and execute it again now that the prepared statement is setup ie...
        //row = ps.executeUpdate();

        System.out.println(row);
    }
}
