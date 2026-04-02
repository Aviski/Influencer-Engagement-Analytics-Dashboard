# Social Media Engagement Analyzer

A Java application that reads influencer performance data from CSV files, calculates engagement metrics, prints ranked reports to the console, and displays the results in a simple interactive GUI.

This project started as a data structures assignment and is being refined into a cleaner portfolio piece. The current version highlights custom data modeling, CSV parsing, comparator-based sorting, and a linked-list-backed collection used to drive both console and GUI views.

## Features

- Parses influencer data from CSV input files
- Computes traditional and reach engagement rates
- Supports monthly and first-quarter reporting
- Sorts results by channel name or engagement rate
- Displays rankings in both console and GUI form
- Includes unit tests for the core domain and collection logic

## Project Structure

- [`src/prj5`](src/prj5): application source code and tests
- [`SampleInput1_2023.csv`](SampleInput1_2023.csv): sample input dataset
- [`SampleOutput1_2023.txt`](SampleOutput1_2023.txt): expected console output for a sample dataset

## Current Tech Stack

- Java
- Custom linked list and comparator-based sorting
- Course-provided `cs2` GUI library
- Course-provided `student.TestCase` test framework

## Running The Project

The original project was built in a course environment that included the `cs2` and `student` libraries. Because those dependencies are not bundled in this repository yet, the easiest way to run the current version is through the same environment or an IDE project that already has those libraries configured.

The main entry point is [`src/prj5/ProjectRunner.java`](src/prj5/ProjectRunner.java).

By default, the app loads `SampleInput1_2023.csv` when no file argument is provided.

## Why This Project Matters

This repo demonstrates:

- object-oriented design with multiple collaborating classes
- custom collection implementation and iteration support
- data ingestion from structured files
- metric computation and ranking logic
- basic data visualization through a desktop GUI

## Planned Improvements

- replace course-specific dependencies with standard, portable libraries
- add a modern build tool such as Maven or Gradle
- improve test portability with JUnit
- refresh the GUI and overall project presentation
- add screenshots and clearer setup instructions
