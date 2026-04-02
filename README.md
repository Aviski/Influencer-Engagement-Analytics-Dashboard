# Social Media Engagement Analyzer

A Java application that reads influencer performance data from CSV files, calculates engagement metrics, and prints ranked console reports.

This repository is packaged as a standard Maven project. The portable build currently supports the console workflow only. The original course-specific GUI is not part of the runnable build because it depended on libraries that are not distributed with this repository.

## Requirements

- Java 17
- Maven 3.9+

## Build

```bash
mvn clean compile
```

## Test

```bash
mvn test
```

## Run

Run with the default sample input:

```bash
mvn exec:java
```

Run with a specific CSV file:

```bash
mvn exec:java -Dexec.args="SampleInput2_2023.csv"
```

## Current Scope

- Parses influencer data from CSV input files
- Computes traditional and reach engagement rates
- Prints ranked console output for first-quarter reporting
- Includes portable JUnit 5 tests for the existing core logic

## Project Layout

- `src/main/java/prj5`: application source
- `src/test/java/prj5`: test source
- `SampleInput*.csv`: sample datasets
- `SampleOutput*.txt`: expected console output used by reference tests

## Notes

- The application entry point is `prj5.ProjectRunner`.
- If no input file argument is provided, the app uses `SampleInput1_2023.csv`.
- The custom linked list and existing business logic were kept intact in this phase to avoid changing behavior beyond build, run, and test portability.
