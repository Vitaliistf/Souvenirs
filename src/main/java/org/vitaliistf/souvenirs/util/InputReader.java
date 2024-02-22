package org.vitaliistf.souvenirs.util;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Utility class for reading various types of input from the user.
 */
public class InputReader {

    private final Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withLocale(Locale.UK);

    /**
     * Constructs an InputReader with the specified InputStream.
     *
     * @param stream The stream to be used for input.
     */
    public InputReader(InputStream stream) {
        this.scanner = new Scanner(stream);
    }

    /**
     * Reads an integer input from the user.
     *
     * @param prompt The prompt message for the user.
     * @return The integer input provided by the user.
     */
    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️Enter integer number.");
            }
        }
    }

    /**
     * Reads a long integer input from the user.
     *
     * @param prompt The prompt message for the user.
     * @return The long integer input provided by the user.
     */
    public long getLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️Enter long integer number.");
            }
        }
    }

    /**
     * Reads a decimal number input from the user.
     *
     * @param prompt The prompt message for the user.
     * @return The decimal number input provided by the user.
     */
    public double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️Enter decimal number.");
            }
        }
    }

    /**
     * Reads a string input from the user.
     *
     * @param prompt The prompt message for the user.
     * @return The string input provided by the user.
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Reads a date input from the user.
     *
     * @param prompt The prompt message for the user.
     * @return The date input provided by the user.
     */
    public LocalDate getDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️Enter date in dd-mm-yyyy format (ex. 15-02-2024).");
            }
        }
    }

    /**
     * Closes the reader.
     */
    public void close() {
        scanner.close();
    }

}
