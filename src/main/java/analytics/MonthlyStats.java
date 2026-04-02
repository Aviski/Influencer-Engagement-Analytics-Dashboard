package analytics;

/**
 * Represents the engagement statistics for a single influencer in one month
 * 
 * @author Avi Patel
 * @version 11/17/25
 */
public class MonthlyStats
{
    /**
     * the name of the month
     */
    private String month;

    /**
     * total number of likes received during the month
     */
    private int likes;

    /**
     * total number of posts made during this month
     */
    private int posts;

    /**
     * total number of followers during this month
     */
    private int followers;

    /**
     * total number of comments during this month
     */
    private int comments;

    /**
     * total number of views during this month
     */
    private int views;

    /**
     * creates a new monthlyStats object with all counts initialized
     * 
     * @param month
     *            the name of the month
     * @param likes
     *            the total number of likes for the month
     * @param posts
     *            the total number of posts for this month
     * @param followers
     *            the total number of followers for the month
     * @param comments
     *            the total number of comments for this month
     * @param views
     *            the total number of views for this month
     */
    public MonthlyStats(
        String month,
        int likes,
        int posts,
        int followers,
        int comments,
        int views)
    {
        this.month = month;
        this.likes = likes;
        this.posts = posts;
        this.followers = followers;
        this.comments = comments;
        this.views = views;
    }


    /**
     * returns the name of the month
     * 
     * @return the month name
     */
    public String getMonth()
    {
        return month;
    }


    /**
     * returns the total number of likes for this month
     * 
     * @return the number of likes
     */
    public int getLikes()
    {
        return likes;
    }


    /**
     * returns the total number of posts for this month
     * 
     * @return the number of posts
     */
    public int getPosts()
    {
        return posts;
    }


    /**
     * returns the total number of followers for this month
     * 
     * @return the number of followers
     */
    public int getFollowers()
    {
        return followers;
    }


    /**
     * returns the total number of comments for this month
     * 
     * @return the number of comments
     */
    public int getComments()
    {
        return comments;
    }


    /**
     * returns the total number of views for this month
     * 
     * @return the number of views
     */
    public int getViews()
    {
        return views;
    }


    /**
     * returns the total interactions for this month, it will be the sum of the
     * likes and comments
     * 
     * @return the total interactions
     */
    public int getTotalInteractions()
    {
        return likes + comments;
    }

}
