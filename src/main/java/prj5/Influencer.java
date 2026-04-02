package prj5;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents information about an influencer
 * 
 * @author Avi Patel
 * @version 11/17/25
 */
public class Influencer
{

    /**
     * the username of the influencer
     */
    private String influencerUsername;

    /**
     * the name of the the influencer's channel
     */
    private String channelName;

    /**
     * the country the influencer is from
     */
    private String country;

    /**
     * the main topic of content that the influencer
     */
    private String mainTopic;

    /**
     * list of monthly stats for the influencer
     */
    private List<MonthlyStats> monthlyStats;

    /**
     * helps to format engagement rates with one decimal place
     */
    private static final DecimalFormat ONE_DECIMAL = new DecimalFormat("#.#");

    /**
     * creates a new influencer object with all counts initialized
     * 
     * @param influencerUsername
     *            the username of the influencer
     * @param channelName
     *            the name of the influencer's channel
     * @param country
     *            the country the influencer is from
     * @param mainTopic
     *            the influencer's channel's main topic
     */
    public Influencer(
        String influencerUsername,
        String channelName,
        String country,
        String mainTopic)
    {
        this.influencerUsername = influencerUsername;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
        this.monthlyStats = new ArrayList<MonthlyStats>();
    }


    /**
     * returns the name of the channel
     * 
     * @return the channel name
     */
    public String getChannelName()
    {
        return channelName;
    }


    /**
     * returns the username of the influencer
     * 
     * @return the username of the influencer
     */
    public String getInfluencerUsername()
    {
        return influencerUsername;
    }


    /**
     * returns the country the influencer is based
     * 
     * @return the country the influencer is based
     */
    public String getCountry()
    {
        return country;
    }


    /**
     * returns the topic that the influencer covers
     * 
     * @return the topic that the influencer covers
     */
    public String getTopic()
    {
        return mainTopic;
    }


    /**
     * Adds monthlyStats for a month and makes sure the month is stored in
     * uppercase
     * 
     * @param stats
     *            the stats the user wants to add
     */
    public void addMonthlyStats(MonthlyStats stats)
    {
        if (stats == null || stats.getMonth() == null)
        {
            return;
        }

        String newMonth = stats.getMonth().trim();

        for (int i = 0; i < monthlyStats.size(); i++)
        {
            MonthlyStats existing = monthlyStats.get(i);
            if (existing.getMonth() != null
                && existing.getMonth().trim().equalsIgnoreCase(newMonth))
            {
                monthlyStats.set(i, stats);
                return;
            }
        }
        monthlyStats.add(stats);
    }


    /**
     * returns the monthlyStats for a given month unless the month is null which
     * then it returns null
     * 
     * @param month
     *            the month used to find the corresponding stats
     * @return the MonthlyStats for the given month
     */
    public MonthlyStats getMonthlyStats(String month)
    {
        if (month == null)
        {
            return null;
        }
        String target = month.trim();

        for (MonthlyStats stats : monthlyStats)
        {
            if (stats.getMonth() != null
                && stats.getMonth().trim().equalsIgnoreCase(target))
            {
                return stats;
            }
        }
        return null;
    }


    /**
     * returns the engagement for the desired metric and period
     * 
     * @param metric
     *            traditional or reach metric wanted by user/system
     * @param period
     *            JANUARY, FEBRUARY, MARCH, or Q1 period wanted by user/system
     * @return string of the monthly engagement for the desired month's stats or
     *             the Q1 engagement for Q1's stats or 0 if the PeriodType is
     *             none of these
     */
    public String getEngagement(MetricType metric, PeriodType period)
    {
        double value = computeEngagementValue(metric, period);
        if (value < 0)
        {
            // Negative sentinel means "cannot compute"
            return "N/A";
        }
        return ONE_DECIMAL.format(value);
    }


    /**
     * computes the engagement value for the given metric and period
     * 
     * @param metric
     *            the engagement metric
     * @param period
     *            the period
     * @return the engagement percentage
     */
    private double computeEngagementValue(MetricType metric, PeriodType period)
    {
        if (metric == null || period == null)
        {
            return -1;
        }

        switch (period)
        {
            case JANUARY:
                return computeMonthlyEngagement(metric, "January");
            case FEBRUARY:
                return computeMonthlyEngagement(metric, "February");
            case MARCH:
                return computeMonthlyEngagement(metric, "March");
            case Q1:
                return computeQ1Engagement(metric);
            default:
                return -1;
        }
    }


    /**
     * returns the monthly engagement for the desired metric and month as a
     * double
     * 
     * @param metric
     *            traditional or reach metric wanted by user/system
     * @param month
     *            JANUARY, FEBRUARY, or MARCH month wanted by user/system
     * @return the monthly engagement for the desired metric and month as a
     *             double or 0 if any data is null
     */
    private double computeMonthlyEngagement(MetricType metric, String month)
    {
        MonthlyStats stats = getMonthlyStats(month);
        if (stats == null || metric == null)
        {
            return -1;
        }

        double interactions = stats.getLikes() + stats.getComments();
        double denominator;

        switch (metric)
        {
            case TRADITIONAL:
                denominator = stats.getFollowers();
                break;
            case REACH:
                denominator = stats.getViews();
                break;
            default:
                return -1;
        }

        if (denominator == 0)
        {
            return -1;
        }
        return (interactions / denominator) * 100.0;
    }


    /**
     * returns the Q1 engagement for the desired metric and month as a double as
     * an average
     * 
     * @param metric
     *            traditional or reach metric wanted by user/system
     * @return the Q1 engagement for the desired metric and month as a double as
     *             an average
     */
    private double computeQ1Engagement(MetricType metric)
    {
        if (metric == null)
        {
            return -1;
        }

        String[] q1Months = { "January", "February", "March" };
        double totalInteractions = 0.0;
        double totalViews = 0.0;

        for (String month : q1Months)
        {
            MonthlyStats stats = getMonthlyStats(month);
            if (stats != null)
            {
                totalInteractions += stats.getLikes() + stats.getComments();
                totalViews += stats.getViews();
            }
        }

        if (metric == MetricType.TRADITIONAL)
        {

            MonthlyStats marchStats = getMonthlyStats("March");
            if (marchStats == null || marchStats.getFollowers() == 0)
            {
                return -1;
            }
            return (totalInteractions / marchStats.getFollowers()) * 100.0;
        }
        else if (totalViews == 0)
        {
            return -1;
        }
        return (totalInteractions / totalViews) * 100.0;
    }
}
