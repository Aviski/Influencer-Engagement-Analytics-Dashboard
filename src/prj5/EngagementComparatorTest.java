package prj5;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** Unit tests for EngagementComparator
 * 
 * @author Avi Patel
 * @version Nov 20, 2025
 */
public class EngagementComparatorTest
    extends TestCase
{
    private Influencer high;
    private Influencer mid;
    private Influencer low;
    private Influencer noData;
    private EngagementComparator tradJanComparator;

    /**
     * sets up a few Influencer objects with known engagement values
     */
    public void setUp()
    {
        high = new Influencer("userHigh", "HighChannel", "US", "Test");
        mid = new Influencer("userMid", "MidChannel", "US", "Test");
        low = new Influencer("userLow", "LowChannel", "US", "Test");
        noData = new Influencer("userNone", "NoDataChannel", "US", "Test");

        MonthlyStats janHigh =
            new MonthlyStats("January", 300, 10, 100, 0, 100);

        MonthlyStats janMid = new MonthlyStats("January", 200, 10, 100, 0, 100);

        MonthlyStats janLow = new MonthlyStats("January", 100, 10, 100, 0, 100);

        high.addMonthlyStats(janHigh);
        mid.addMonthlyStats(janMid);
        low.addMonthlyStats(janLow);

        MonthlyStats janZeroFollowers =
            new MonthlyStats("January", 50, 10, 0, 0, 100);
        noData.addMonthlyStats(janZeroFollowers);

        tradJanComparator = new EngagementComparator(
            MetricType.TRADITIONAL,
            PeriodType.JANUARY);

    }


    /**
     * tests that the constructor throws an exception if metric or period is
     * null
     */
    @SuppressWarnings("unused")
    public void testConstructorNullArguments()
    {
        Exception ex = null;
        try
        {
            new EngagementComparator(null, PeriodType.JANUARY);
        }
        catch (IllegalArgumentException e)
        {
            ex = e;
        }
        assertNotNull(ex);

        ex = null;
        try
        {
            new EngagementComparator(MetricType.TRADITIONAL, null);
        }
        catch (IllegalArgumentException e)
        {
            ex = e;
        }
        assertNotNull(ex);
    }


    /**
     * tests that influencers are ordered by engagement in descending order
     */
    public void testCompareDescendingOrder()
    {
        assertTrue(tradJanComparator.compare(high, mid) < 0);
        assertTrue(tradJanComparator.compare(high, low) < 0);
        assertTrue(tradJanComparator.compare(low, mid) > 0);
    }


    /**
     * tests that when engagement is tied, the comparator falls back to
     * comparing channel names alphabetically
     */
    public void testCompareTieBreakByName()
    {
        Influencer a = new Influencer("userA", "Alpha", "US", "Test");
        Influencer b = new Influencer("userB", "Bravo", "US", "Test");

        MonthlyStats statsA = new MonthlyStats("January", 100, 10, 100, 0, 100);
        MonthlyStats statsB = new MonthlyStats("January", 100, 10, 100, 0, 100);

        a.addMonthlyStats(statsA);
        b.addMonthlyStats(statsB);

        assertTrue(tradJanComparator.compare(a, b) < 0);
        assertTrue(tradJanComparator.compare(b, a) > 0);
    }


    /**
     * tests that influencers with N/A engagement are treated as having the
     * lowest engagement and therefore are ordered
     */
    public void testCompareWithNA()
    {
        assertEquals(
            "N/A",
            noData.getEngagement(MetricType.TRADITIONAL, PeriodType.JANUARY));

        assertTrue(tradJanComparator.compare(low, noData) < 0);
        assertTrue(tradJanComparator.compare(noData, low) > 0);
    }


    /**
     * tests handling of null influencer references
     */
    public void testCompareWithNullInfluencers()
    {
        assertEquals(0, tradJanComparator.compare(null, null));
        assertTrue(tradJanComparator.compare(high, null) < 0);
        assertTrue(tradJanComparator.compare(null, high) > 0);
    }

}
