package analytics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * unit tests for MonthlyStats
 * 
 * @author Avi Patel
 * @version 11/17/25
 */
public class MonthlyStatsTest
{
    /**
     * sample MonthlyStats object used in the tests
     */
    private MonthlyStats stats;

    /**
     * sets up MonthlyStats object before each test method
     */
    @BeforeEach
    public void setUp()
    {
        stats = new MonthlyStats("January", 100, 10, 1000, 20, 500);
    }


    /**
     * makes sure the constructor correctly stores all the field values and the
     * getters return the right things
     */
    @Test
    public void testConstructorAndGetters()
    {
        assertEquals("January", stats.getMonth());
        assertEquals(100, stats.getLikes());
        assertEquals(10, stats.getPosts());
        assertEquals(1000, stats.getFollowers());
        assertEquals(20, stats.getComments());
        assertEquals(500, stats.getViews());
    }


    /**
     * makes sure that getTotalInteractions() returns the sum of the likes and
     * comments
     */
    @Test
    public void testGetTotalInteractions()
    {
        assertEquals(120, stats.getTotalInteractions());
    }


    /**
     * makes sure that getTotalInteractions() works for a case where likes and
     * comments are zero
     */
    @Test
    public void testGetTotalInteractionsWithZero()
    {
        MonthlyStats zeroStats =
            new MonthlyStats("January", 0, 5, 2000, 0, 800);
        assertEquals(0, zeroStats.getTotalInteractions());
    }

}
