package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * test class for ChannelNameComparator
 * 
 * @author Avi Patel
 * @version Nov 20, 2025
 */
public class ChannelNameComparatorTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Influencer abby;
    private Influencer beth;

    // ~ Constructors ..........................................................
    /**
     * sets up Influencers to test methods on
     */
    public void setUp()
    {
        abby = new Influencer("abby1234", "Abby Beauty", "Brazil", "Beauty");
        beth = new Influencer("bethgamez", "BethGaming", "USA", "Gaming");
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * tests compare()
     */
    public void testCompare()
    {

        ChannelNameComparator compByChannel = new ChannelNameComparator();

        assertEquals(1, compByChannel.compare(null, abby));
        assertEquals(-1, compByChannel.compare(abby, null));
        assertEquals(0, compByChannel.compare(null, null));

        Influencer a = new Influencer("a", null, "USA", "Food");
        Influencer b = new Influencer("b", null, "USA", "Food");

        assertEquals(0, compByChannel.compare(a, b));
        assertEquals(1, compByChannel.compare(a, beth));
        assertEquals(-1, compByChannel.compare(abby, b));

        assertTrue(compByChannel.compare(abby, beth) < 0);
    }
}
