package analytics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

/**
 * Reference tests for console output using the provided sample inputs.
 * 
 * @author Avi Patel
 * @version Nov 20, 2025
 */
public class Input2023FReferenceTest {
	@Test
	public void testSampleInput1MatchesReferenceOutput() throws IOException {
		assertConsoleOutputMatches("SampleInput1_2023.csv", "SampleOutput1_2023.txt");
	}

	@Test
	public void testSampleInput2MatchesReferenceOutput() throws IOException {
		assertConsoleOutputMatches("SampleInput2_2023.csv", "SampleOutput2_2023.txt");
	}

	@Test
	public void testSampleInput3MatchesReferenceOutput() throws IOException {
		assertConsoleOutputMatches("SampleInput3_2023.csv", "SampleOutput3_2023.txt");
	}

	private void assertConsoleOutputMatches(String inputFile, String outputFile) throws IOException {
		String expected = Files.readString(Path.of(outputFile), StandardCharsets.UTF_8).replace("\r\n", "\n");

		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;

		try {
			System.setOut(new PrintStream(outputBuffer, true, StandardCharsets.UTF_8));
			ProjectRunner.main(new String[] { inputFile });
		} finally {
			System.setOut(originalOut);
		}

		String actual = outputBuffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");

		assertEquals(expected, actual);
	}
}
