package prj5;

import java.util.Comparator;

/**
 * Comparator that order Influencer objects alphabetically by channel name
 * 
 * @author Avi Patel
 * @version Nov 19, 2025
 */
public class ChannelNameComparator
    implements Comparator<Influencer>
{

    /**
     * compares two influencers by their channel name
     * 
     * @param a
     *            the first influencer
     * @param b
     *            the second influencer
     * @return a negative integer if a comes before b, pos integer if a coomes
     *             after b, or zero if they're equal
     */
    @Override
    public int compare(Influencer a, Influencer b)
    {
        if (a == null && b == null)
        {
            return 0;
        }
        if (a == null)
        {
            return 1;
        }
        if (b == null)
        {
            return -1;
        }

        String nameA = a.getChannelName();
        String nameB = b.getChannelName();

        if (nameA == null && nameB == null)
        {
            return 0;
        }
        if (nameA == null)
        {
            return 1;
        }
        if (nameB == null)
        {
            return -1;
        }

        return nameA.compareToIgnoreCase(nameB);
    }

}
