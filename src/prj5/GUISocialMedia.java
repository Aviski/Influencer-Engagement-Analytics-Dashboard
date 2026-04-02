package prj5;

import cs2.*;
import java.awt.Color;
import java.text.DecimalFormat;

// -------------------------------------------------------------------------
/**
 * The GUI class to display it
 * 
 * @author Avi Patel
 * @version Dec 4, 2025
 */
public class GUISocialMedia {
    // ~ Fields ................................................................
    private InfluencerLinkedList data;
    private MetricType currentMetric;
    private PeriodType currentPeriod;
    private SortMode currentSort;
    private Window window;

    // ~ Constructors ..........................................................
// ----------------------------------------------------------
    /**
     * Create a new GuiSocialMedia object.
     * 
     * @param data
     *            list of influencers
     */
    public GUISocialMedia(InfluencerLinkedList data) {
        this.data = data;
        this.currentMetric = MetricType.TRADITIONAL;
        this.currentPeriod = PeriodType.JANUARY;
        this.currentSort = SortMode.BY_NAME;
        window = new Window("Social Media Visual");
        setUpWindow();
        setUpButtons();
        refreshChart();

    }

    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * sets up the window
     */
    public void setUpWindow() {
        window.setSize(1200, 800);
        window.setTitle("Social Media Visual");
    }


    // ----------------------------------------------------------
    /**
     * sets up the buttons
     */
    public void setUpButtons() {
        // top buttons
        Button sortName = new Button("Sort by Channel Name");
        sortName.onClick(this, "onSortByNameSelected");
        window.addButton(sortName, WindowSide.NORTH);

        Button sortEngagement = new Button("Sort by Engagement Rate");
        sortEngagement.onClick(this, "onSortByEngagementSelected");
        window.addButton(sortEngagement, WindowSide.NORTH);

        Button quitButton = new Button("Quit");
        quitButton.onClick(this, "onQuitSelected");
        window.addButton(quitButton, WindowSide.NORTH);

        // left buttons
        Button tradButton = new Button("Traditional Engagement Rate");
        tradButton.onClick(this, "onTraditionalSelected");
        window.addButton(tradButton, WindowSide.WEST);

        Button reachButton = new Button("Reach Engagement Rate");
        reachButton.onClick(this, "onReachSelected");
        window.addButton(reachButton, WindowSide.WEST);

        // bottom buttons
        Button jan = new Button("January");
        jan.onClick(this, "onJanuarySelected");
        window.addButton(jan, WindowSide.SOUTH);

        Button feb = new Button("February");
        feb.onClick(this, "onFebruarySelected");
        window.addButton(feb, WindowSide.SOUTH);

        Button march = new Button("March");
        march.onClick(this, "onMarchSelected");
        window.addButton(march, WindowSide.SOUTH);

        Button q1 = new Button("First Quarter (Jan - March)");
        q1.onClick(this, "onQ1Selected");
        window.addButton(q1, WindowSide.SOUTH);

    }


    // ----------------------------------------------------------
    /**
     * selects metric type traditional
     * 
     * @param button
     *            in GUI
     */
    public void onTraditionalSelected(Button button) {
        currentMetric = MetricType.TRADITIONAL;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * selects metric type reach
     * 
     * @param button
     *            in GUI
     */
    public void onReachSelected(Button button) {
        currentMetric = MetricType.REACH;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * selects period type january
     * 
     * @param button
     *            in GUI
     */
    public void onJanuarySelected(Button button) {
        currentPeriod = PeriodType.JANUARY;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * selects period type february
     * 
     * @param button
     *            in GUI
     */
    public void onFebruarySelected(Button button) {
        currentPeriod = PeriodType.FEBRUARY;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * selects period type march
     * 
     * @param button
     *            in GUI
     */
    public void onMarchSelected(Button button) {
        currentPeriod = PeriodType.MARCH;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * selects period type february
     * 
     * @param button
     *            in GUI
     */
    public void onQ1Selected(Button button) {
        currentPeriod = PeriodType.Q1;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * sort by name
     * 
     * @param button
     *            in GUI
     */
    public void onSortByNameSelected(Button button) {

        currentSort = SortMode.BY_NAME;
        refreshChart();

    }


    // ----------------------------------------------------------
    /**
     * sort by engagement
     * 
     * @param button
     *            in GUI
     */
    public void onSortByEngagementSelected(Button button) {

        currentSort = SortMode.BY_ENGAGEMENT;
        refreshChart();
    }


    // ----------------------------------------------------------
    /**
     * quit
     * 
     * @param button
     *            in GUI
     */
    public void onQuitSelected(Button button) {
        System.exit(0);
    }


    // ----------------------------------------------------------
    /**
     * refreshes the window
     */
    public void refreshChart() {
        window.removeAllShapes();

        if (currentSort == SortMode.BY_NAME) {
            data.sort(new ChannelNameComparator());
        }

        else {
            data.sort(new EngagementComparator(currentMetric, currentPeriod));
        }

        // labels for enumerators
        @SuppressWarnings("unused")
        String periodText = "";
        switch (currentPeriod) {
            case JANUARY:
                periodText = "January";
                break;
            case FEBRUARY:
                periodText = "February";
                break;
            case MARCH:
                periodText = "March";
                break;

            case Q1:
                periodText = "First Quarter (Jan-March)";
                break;

            default:
                periodText = "";
                break;
        }

        String metricText = "";

        switch (currentMetric) {
            case TRADITIONAL:
                metricText = "Traditional Engagement";
                break;
            case REACH:
                metricText = "Reach Engagement";
                break;
            default:
                metricText = "";
                break;

        }

        String sortText = "";
        switch (currentSort) {
            case BY_NAME:
                sortText = "Channel Name";
                break;
            case BY_ENGAGEMENT:
                sortText = "Engagement Rate";
                break;
            default:
                sortText = "";
                break;
        }

        // top left corner labels
        TextShape line1 = new TextShape(20, 10, periodText);

        TextShape line2 = new TextShape(20, 30, metricText);
        TextShape line3 = new TextShape(20, 50, "Sorting by " + sortText);

        window.addShape(line1);
        window.addShape(line2);
        window.addShape(line3);

        Color[] colors = { Color.GREEN, Color.ORANGE, Color.RED, Color.BLUE };

        int x = 40;
        int y = 550;
        int barW = 40;
        int space = 150;

        DecimalFormat dc = new DecimalFormat("#.#");

        for (int i = 0; i < data.size(); i++) {
            Influencer inf = data.get(i);
            String valueText = inf.getEngagement(currentMetric, currentPeriod);

            double val;
            try {
                val = Double.parseDouble(valueText);
            }
            catch (NumberFormatException e) {
                val = 0;
            }

            int barH = (int)(val * 3);

            Color barColor = colors[i % colors.length];
            Shape bar = new Shape(x, y - barH, barW, barH, barColor);
            window.addShape(bar);

            TextShape infName = new TextShape(x, y + 5, inf.getChannelName());
            window.addShape(infName);

            TextShape valLabel = new TextShape(x, y + 20, dc.format(val));
            window.addShape(valLabel);

            x += space;

        }

    }

}
