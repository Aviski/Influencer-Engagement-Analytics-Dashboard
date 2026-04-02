package analytics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * unit tests for Influencer
 * 
 * @author Avi Patel
 * @version 11/17/25
 */
public class InfluencerTest
{

    /**
     * sample influencer named abby
     */
    private Influencer abby;

    /**
     * sample monthlyStats for JANUARY
     */
    private MonthlyStats jan;

    /**
     * sample monthlyStats for FEBRUARY
     */
    private MonthlyStats feb;

    /**
     * sample monthlyStats for MARCH
     */
    private MonthlyStats mar;

    /**
     * sets up influencer with monthlyStats to be tested
     */
    @BeforeEach
    public void setUp()
    {
        abby = new Influencer("abby123", "Abby's Life", "Brazil", "Beauty");
        jan = new MonthlyStats("JANUARY", 100, 10, 1000, 20, 500);
        feb = new MonthlyStats("FEBRUARY", 200, 20, 2000, 30, 1000);
        mar = new MonthlyStats("MARCH", 300, 30, 3000, 40, 1500);
        abby.addMonthlyStats(jan);
        abby.addMonthlyStats(feb);
        abby.addMonthlyStats(mar);
    }


    /**
     * tests the getChannelName method
     */
    @Test
    public void testGetChannelName()
    {
        assertEquals("Abby's Life", abby.getChannelName());
    }


    /**
     * tests the getInfluencerUsername method
     */
    @Test
    public void testGetInfluencerUsername()
    {
        assertEquals("abby123", abby.getInfluencerUsername());
    }


    /**
     * tests the getCountry method
     */
    @Test
    public void testGetCountry()
    {
        assertEquals("Brazil", abby.getCountry());
    }


    /**
     * tests the getTopic method
     */
    @Test
    public void testGetTopic()
    {
        assertEquals("Beauty", abby.getTopic());
    }


    /**
     * tests the addMonthlyStats method and getMonthlyStats method
     */
    @Test
    public void testAddandGetMonthlyStats()
    {
        assertEquals(jan, abby.getMonthlyStats("JANUARY"));
        assertEquals(feb, abby.getMonthlyStats("FEBRUARY"));
        assertEquals(mar, abby.getMonthlyStats("MARCH"));

        // makes sure that it is not case sensitive
        assertEquals(jan, abby.getMonthlyStats("january"));
        assertEquals(feb, abby.getMonthlyStats(" February "));
        assertEquals(mar, abby.getMonthlyStats("mArCh"));

        MonthlyStats newJan =
            new MonthlyStats("January", 150, 15, 1000, 50, 500);
        abby.addMonthlyStats(newJan);
        assertEquals(newJan, abby.getMonthlyStats("JANUARY"));
    }


    /**
     * tests the getEngagement method
     */
    @Test
    public void testGetEngagment()
    {
        assertEquals(
            "12",
            abby.getEngagement(MetricType.TRADITIONAL, PeriodType.JANUARY));
        assertEquals(
            "24",
            abby.getEngagement(MetricType.REACH, PeriodType.JANUARY));
        assertEquals(
            "11.5",
            abby.getEngagement(MetricType.TRADITIONAL, PeriodType.FEBRUARY));
        assertEquals(
            "23",
            abby.getEngagement(MetricType.REACH, PeriodType.FEBRUARY));

        assertEquals(
            "11.3",
            abby.getEngagement(MetricType.TRADITIONAL, PeriodType.MARCH));

        assertEquals(
            "22.7",
            abby.getEngagement(MetricType.REACH, PeriodType.MARCH));

        assertEquals(
            "23",
            abby.getEngagement(MetricType.TRADITIONAL, PeriodType.Q1));

        assertEquals("23", abby.getEngagement(MetricType.REACH, PeriodType.Q1));
    }


    /**
     * tests the getEngagementString method
     */
    @Test
    public void testGetEngagmentMissingData()
    {
        Influencer steve =
            new Influencer("stevey", "Steve Please", "Canada", "Painting");
        steve.addMonthlyStats(feb);

        assertEquals(
            "N/A",
            steve.getEngagement(MetricType.TRADITIONAL, PeriodType.MARCH));
        assertEquals(
            "N/A",
            steve.getEngagement(MetricType.REACH, PeriodType.MARCH));

        assertEquals(
            "N/A",
            steve.getEngagement(MetricType.TRADITIONAL, PeriodType.Q1));
        assertEquals(
            "23",
            steve.getEngagement(MetricType.REACH, PeriodType.Q1));
    }


    /**
     * tests getEngagement behavior when denominators are zero
     */
    @Test
    public void testGetEngagementZeroDenominator()
    {
        Influencer zero = new Influencer("zero", "Zero Denom", "US", "Test");

        MonthlyStats janZeroFollowers =
            new MonthlyStats("January", 100, 10, 0, 20, 500);
        zero.addMonthlyStats(janZeroFollowers);

        MonthlyStats febZeroViews =
            new MonthlyStats("February", 100, 10, 1000, 20, 0);
        zero.addMonthlyStats(febZeroViews);

        assertEquals(
            "N/A",
            zero.getEngagement(MetricType.TRADITIONAL, PeriodType.JANUARY));
        assertEquals(
            "N/A",
            zero.getEngagement(MetricType.REACH, PeriodType.FEBRUARY));
    }


    /**
     * tests that getEngagement returns N/A when metric or period is null
     */
    @Test
    public void testGetEngagementNullArguments()
    {
        assertEquals("N/A", abby.getEngagement(null, PeriodType.JANUARY));
        assertEquals("N/A", abby.getEngagement(MetricType.TRADITIONAL, null));
    }

}
