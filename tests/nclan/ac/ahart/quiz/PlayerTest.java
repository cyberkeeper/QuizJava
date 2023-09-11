package nclan.ac.ahart.quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p1 = new Player("alan", "hart");

    @Test
    void emptyPlayer() {
        assertEquals("Player: alan hart previousScores=[]", p1.toString());
        assertEquals(0, p1.getLastScore());
        assertEquals(0, p1.getHighestScore());
    }

    @Test
    void testSingleScore() {
        p1.recordScore(19);
        assertEquals("Player: alan hart previousScores=[19]", p1.toString());
        assertEquals(19, p1.getLastScore());
        assertEquals(19, p1.getHighestScore());
    }

    @Test
    void testHighScoreFirstPos() {
        p1.recordScore(52);
        p1.recordScore(23);
        p1.recordScore(33);
        p1.recordScore(19);
        assertEquals("Player: alan hart previousScores=[52, 23, 33, 19]", p1.toString());
        assertEquals(19, p1.getLastScore());
        assertEquals(52, p1.getHighestScore());
    }

    @Test
    void testHighScoreMidPos() {
        p1.recordScore(23);
        p1.recordScore(52);
        p1.recordScore(33);
        p1.recordScore(19);
        assertEquals("Player: alan hart previousScores=[23, 52, 33, 19]", p1.toString());
        assertEquals(19, p1.getLastScore());
        assertEquals(52, p1.getHighestScore());
    }

    @Test
    void testHighScoreLastPos() {
        p1.recordScore(19);
        p1.recordScore(23);
        p1.recordScore(33);
        p1.recordScore(52);
        assertEquals("Player: alan hart previousScores=[19, 23, 33, 52]", p1.toString());
        assertEquals(52, p1.getLastScore());
        assertEquals(52, p1.getHighestScore());
    }

    @Test
    void getDateOfBirth() {
        //check exception thrown when empty text supplied
        try {
            p1.setDateOfBirth("");
            System.out.println(p1.getDateOfBirth());
            System.out.println(p1.getAge());
        } catch (Exception e) {
            fail("Exception not expected! " + e.getMessage());
        }

        try {
            p1.setDateOfBirth("2001-03-22");
            assertEquals(22, p1.getAge());
        } catch (Exception e) {
            fail("Exception not expected! " + e.getMessage());
        }

        //check exception thrown when badly formatted text supplied
        assertThrowsExactly(Exception.class, () -> {
            p1.setDateOfBirth("2001,03,22");
        });

        //check exception thrown when badly formatted text supplied
        assertThrowsExactly(Exception.class, () -> {
            p1.setDateOfBirth("2001-03");
        });
    }

    @Test
    void testAddress() {
        p1.setAddress(10, "Glebe Street", "Auchenshoogle", "Perthshire", "P32 6AR");
        assertEquals("10 Glebe Street,\nAuchenshoogle,\nPerthshire,\nP32 6AR.", p1.getAddress().toString());
    }
}