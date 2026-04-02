package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Reads the CSV input file and builds an InfluencerLinkedList containing all
 * influencers and their monthly statistics.
 * 
 * @author Avi Patel
 * @version Nov 19, 2025
 */
public class InputFileReader
{
    // ----------------------------------------------------------

    // ~ Constructors ..........................................................

    /**
     * Loads the given CSV file and returns a list of influencers populated with
     * MonthlyStats for each month found in the file.
     * 
     * @param fileName
     *            of CSV file
     * @return InfluencerLinkedList containing all influencers
     * @throws IllegalArgumentException
     *             if file can't be opened
     */
    public InfluencerLinkedList loadFile(String fileName)
    {
        InfluencerLinkedList list = new InfluencerLinkedList();

        try (Scanner fileScanner = new Scanner(new File(fileName)))
        {
            if (fileScanner.hasNextLine())
            {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty())
                {
                    parseLine(line, list);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalArgumentException(
                "File not found: " + fileName,
                e);
        }

        return list;
    }
    // ~Public Methods ........................................................


    /**
     * Finds an existing influencer with same channel name, or creates and adds
     * if none exists.
     */
    private Influencer getOrCreateInfluencer(
        InfluencerLinkedList list,
        String influencerUsername,
        String channel,
        String country,
        String mainTopic)
    {
        Influencer existing = list.findByChannelName(channel);
        if (existing != null)
        {
            return existing;
        }

        Influencer created =
            new Influencer(influencerUsername, channel, country, mainTopic);

        list.add(created);
        return created;
    }


    /**
     * helper to make sure that the months are only correct months
     */
    private boolean isValidMonth(String month)
    {
        if (month == null)
        {
            return false;
        }
        String m = month.trim().toLowerCase();
        return m.equals("january") || m.equals("february") || m.equals("march")
            || m.equals("april") || m.equals("may") || m.equals("june")
            || m.equals("july") || m.equals("august") || m.equals("september")
            || m.equals("october") || m.equals("november")
            || m.equals("december");
    }


    /**
     * Parses a single CSV line and updates the influencer list. If any numeric
     * field fails to parse, the line is skipped.
     */
    private void parseLine(String line, InfluencerLinkedList list)
    {
        String[] parts = line.split(",\\s*");

        if (parts.length != 10)
        {
            return;
        }

        String month = parts[0].trim();
        if (!isValidMonth(month))
        {
            return;
        }

        String username = parts[1].trim();
        String channelName = parts[2].trim();
        String country = parts[3].trim();
        String mainTopic = parts[4].trim();

        try
        {
            int likes = Integer.parseInt(parts[5]);
            int posts = Integer.parseInt(parts[6]);
            int followers = Integer.parseInt(parts[7]);
            int comments = Integer.parseInt(parts[8]);
            int views = Integer.parseInt(parts[9]);

            MonthlyStats stats = new MonthlyStats(
                month,
                likes,
                posts,
                followers,
                comments,
                views);

            Influencer influencer = getOrCreateInfluencer(
                list,
                username,
                channelName,
                country,
                mainTopic);

            influencer.addMonthlyStats(stats);
        }
        catch (NumberFormatException e)
        {
            return;
        }
    }
}
