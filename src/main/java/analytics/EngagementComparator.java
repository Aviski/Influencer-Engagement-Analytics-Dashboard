package analytics;

import java.util.Comparator;

/**
 * Comparator that orders influencer objects by engagement rate
 * 
 * @author Avi Patel
 * @version Nov 19, 2025
 */
public class EngagementComparator
    implements Comparator<Influencer>
{
    /*
     * engagement metric to use
     */
    private MetricType metric;

    /**
     * time period
     */
    private PeriodType period;

    /**
     * creates a comparator that orders influencers by the metric and period
     * 
     * @param metric
     *            the engagement metric to use
     * @param period
     *            the time period to use
     */
    public EngagementComparator(MetricType metric, PeriodType period)
    {
        if (metric == null || period == null)
        {
            throw new IllegalArgumentException(
                "Metric and period must not be null");
        }
        this.metric = metric;
        this.period = period;
    }


    /**
     * compares two influencers based on their engagement for this comparators
     * metric and period
     * 
     * @param a
     *            the first influencer
     * @param b
     *            the second influencer
     * @retun neg integer if a is before b, pos integer if a is after b, 0 if
     *            they're equal
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

        double engagementA = parseEngagement(a);
        double engagementB = parseEngagement(b);

        int compare = Double.compare(engagementA, engagementB);
        if (compare != 0)
        {
            return -compare;
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


    /**
     * Parses the engagement string into a double
     * 
     * @param influencer
     *            the influencer whose engagement is being parsed
     * @return the engagement value
     */
    private double parseEngagement(Influencer influencer)
    {
        if (influencer == null)
        {
            return Double.NEGATIVE_INFINITY;
        }

        String engagementString = influencer.getEngagement(metric, period);
        if (engagementString == null)
        {
            return Double.NEGATIVE_INFINITY;
        }

        engagementString = engagementString.trim();
        if (engagementString.equalsIgnoreCase("N/A"))
        {
            return Double.NEGATIVE_INFINITY;
        }

        try
        {
            return Double.parseDouble(engagementString);
        }
        catch (NumberFormatException e)
        {
            return Double.NEGATIVE_INFINITY;
        }
    }
}
