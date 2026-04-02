package prj5;

// -------------------------------------------------------------------------
/**
 * Runs project 5
 * 
 * @author Avi Patel
 * @version Nov 20, 2025
 */
public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Main constructor method for project runner
     * 
     * @param args
     *            the file name
     */
    public static void main(String[] args)
    {
        boolean showConsole = true;
        boolean showGUI = true;

        String fileName = getInputFileName(args);

        InputFileReader reader = new InputFileReader();
        InfluencerLinkedList data = reader.loadFile(fileName);

        if (showConsole)
        {
            runConsoleVersion(data);
        }

        if (showGUI)
        {
            runGuiVersion(data);
        }
    }
    // ~Public Methods ........................................................


    /**
     * Gets File name for input
     * 
     * @param args
     *            the name
     * @return String of file name
     */
    private static String getInputFileName(String[] args)
    {
        if (args != null && args.length > 0 && args[0] != null
            && !args[0].trim().isEmpty())
        {
            return args[0];
        }

        return "SampleInput1_2023.csv";
    }


    /**
     * Opens Console to print to GUI
     * 
     * @param data
     *            the list of influencers loaded from input file
     */
    private static void runConsoleVersion(InfluencerLinkedList data)
    {
        data.sort(new ChannelNameComparator());

        for (Influencer inf : data)
        {
            String channel = inf.getChannelName();
            String engagement =
                inf.getEngagement(MetricType.TRADITIONAL, PeriodType.Q1);

            System.out.println(channel);
            System.out.println("traditional: " + engagement);
            System.out.println("==========");
        }

        System.out.println("**********");
        System.out.println("**********");

        data.sort(new EngagementComparator(MetricType.REACH, PeriodType.Q1));

        for (Influencer inf : data)
        {
            String channel = inf.getChannelName();
            String engagement =
                inf.getEngagement(MetricType.REACH, PeriodType.Q1);

            System.out.println(channel);
            System.out.println("reach: " + engagement);
            System.out.println("==========");
        }
    }


    /**
     * Creates GUI Window
     * 
     * @param data
     *            presents data from InfluencerLinkedList
     */
    @SuppressWarnings("unused")
    private static void runGuiVersion(InfluencerLinkedList data)
    {

        new GUISocialMedia(data);

    }
}
