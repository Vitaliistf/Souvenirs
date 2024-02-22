package org.vitaliistf.souvenirs.menu.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Interface representing a generic table view.
 * @param <T> The type of data displayed in the table.
 * (Usage of "Template method" design pattern.)
 */
public interface TableView<T> {

    /**
     * Generates a string representation of the table based on the provided data.
     * @param data The collection of data items to display in the table.
     * @return A string representing the generated table.
     */
    default String generateTable(Collection<T> data) {
        StringBuilder tableBuilder = new StringBuilder();
        String[] headers = getHeaders();
        int[] columnWidths = getColumnWidths(data);

        String headerFormat = buildHeaderFormat(columnWidths);
        String rowFormat = buildRowFormat(columnWidths);
        String divider = buildDividerLine(columnWidths);

        tableBuilder.append(getTitle())
                .append("\n")
                .append(divider)
                .append("\n")
                .append(String.format(headerFormat + "%n", (Object[]) headers))
                .append(divider)
                .append("\n");

        String rows = data.stream()
                .map(i -> String.format(rowFormat + "%n", (Object[]) getRowData(i)))
                .collect(Collectors.joining());
        tableBuilder.append(rows);

        tableBuilder.append(divider).append("\n");

        return tableBuilder.toString();
    }

    /**
     * Calculates the column widths based on the headers and data.
     * @param data The collection of data items.
     * @return An array representing the width of each column.
     */
    private int[] getColumnWidths(Collection<T> data) {
        int[] widths = Arrays.stream(getHeaders()).mapToInt(String::length).toArray();
        data.stream()
                .map(this::getRowData)
                .forEach(rowData -> IntStream.range(0, rowData.length)
                .forEach(i -> widths[i] = Math.max(rowData[i].length(), widths[i])));
        return widths;
    }

    /**
     * Builds the format string for the table header.
     * @param columnWidths An array representing the width of each column.
     * @return The format string for the header.
     */
    private String buildHeaderFormat(int[] columnWidths) {
        return Arrays.stream(columnWidths)
                .mapToObj(width -> " %-" + width + "s ")
                .collect(Collectors.joining("|", "|", "|"));
    }

    /**
     * Builds the format string for the table rows.
     * @param columnWidths An array representing the width of each column.
     * @return The format string for the rows.
     */
    private String buildRowFormat(int[] columnWidths) {
        return Arrays.stream(columnWidths)
                .mapToObj(width -> " %" + width + "s ")
                .collect(Collectors.joining("|", "|", "|"));
    }

    /**
     * Builds the divider line between rows and header.
     * @param columnWidths An array representing the width of each column.
     * @return The string representing the divider line.
     */
    private String buildDividerLine(int[] columnWidths) {
        return Arrays.stream(columnWidths)
                .mapToObj(width -> "-".repeat(width + 2))
                .collect(Collectors.joining("+", "+", "+"));
    }

    /**
     * Retrieves the headers of the table.
     * @return An array representing the headers.
     */
    String[] getHeaders();

    /**
     * Retrieves the row data for a specific item.
     * @param item The item for which to retrieve row data.
     * @return An array representing the data for a single row.
     */
    String[] getRowData(T item);

    /**
     * Retrieves the title of the table.
     * @return The title of the table.
     */
    String getTitle();
}