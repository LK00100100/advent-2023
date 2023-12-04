package puzzle3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Puzzle3 {

    /**
     * /**
     *
     * @param file assume valid
     * @return list of rows
     */
    public static List<String> readTokens(File file) throws FileNotFoundException {
        List<String> rows = new ArrayList<>();

        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            String row = in.nextLine();

            if (row.isEmpty()) {
                continue;
            }

            rows.add(row);
        }

        return rows;
    }

    /**
     * @param rows   -
     * @param rowIdx -
     * @return sum of numbers next to symbol
     */
    public static int getRowNumber(List<String> rows, int rowIdx) {
        String row = rows.get(rowIdx);

        //parse these rows for numbers
        int total = 0;
        for (int colIdx = 0; colIdx < rows.size(); colIdx++) {
            char c = row.charAt(colIdx);

            int lastDigitIdx = colIdx; //exclusive

            //is this the start of a number?
            if (Character.isDigit(c)) {
                //parse the entire number
                while (++lastDigitIdx < rows.size()) {
                    char d = row.charAt(lastDigitIdx);

                    if (!Character.isDigit(d))
                        break;
                }
            } else {
                continue;
            }

            //is this number next to a symbol? (8-directional)
            if (isNextToSymbol(rows, rowIdx, colIdx, lastDigitIdx - 1)) {
                total += Integer.parseInt(row.substring(colIdx, lastDigitIdx));
            }

            colIdx = lastDigitIdx - 1;
        }

        return total;
    }

    //dev note: just hackin' away. perhaps we can find the gear and then find the numbers

    /**
     * assign number to gear
     *
     * @param rows    -
     * @param rowIdx  -
     * @param gearMap 'row,col of gear' key with multiple value
     * @return sum of numbers next to symbol
     */
    public static int parseGearNumber(List<String> rows, int rowIdx, Map<String, List<Integer>> gearMap) {
        String row = rows.get(rowIdx);

        //parse these rows for numbers
        int total = 0;
        for (int colIdx = 0; colIdx < rows.size(); colIdx++) {
            char c = row.charAt(colIdx);

            int lastDigitIdx = colIdx; //exclusive

            //is this the start of a number?
            if (Character.isDigit(c)) {
                //parse the entire number
                while (++lastDigitIdx < rows.size()) {
                    char d = row.charAt(lastDigitIdx);

                    if (!Character.isDigit(d))
                        break;
                }
            } else {
                continue;
            }

            //is this number next to a symbol? (8-directional)
            var gearCoordinateOpt = isNextToGear(rows, rowIdx, colIdx, lastDigitIdx - 1);
            if (gearCoordinateOpt.isPresent()) {
                int number = Integer.parseInt(row.substring(colIdx, lastDigitIdx));

                var gearCoordinate = gearCoordinateOpt.get();
                if (!gearMap.containsKey(gearCoordinate)) {
                    gearMap.put(gearCoordinate, new LinkedList<>());
                }

                var gearNumbers = gearMap.get(gearCoordinate);
                gearNumbers.add(number);
            }

            colIdx = lastDigitIdx - 1;
        }

        return total;
    }

    /**
     * @param rows     -
     * @param row      number's row
     * @param colStart inclusive
     * @param colEnd   inclusive
     * @return true if next to symbol (8-directional)
     */
    private static boolean isNextToSymbol(List<String> rows, int row, int colStart, int colEnd) {
        //check top row
        for (int col = colStart - 1; col <= colEnd + 1; col++) {
            if (isSymbol(rows, row - 1, col))
                return true;
        }

        //check bottom row
        for (int col = colStart - 1; col <= colEnd + 1; col++) {
            if (isSymbol(rows, row + 1, col))
                return true;
        }

        //check to the sides of the number
        if (isSymbol(rows, row, colStart - 1))
            return true;

        if (isSymbol(rows, row, colEnd + 1))
            return true;

        return false;
    }

    /**
     * returns one gear (assumed one adjacent gear per number)
     *
     * @param rows     -
     * @param row      number's row
     * @param colStart inclusive
     * @param colEnd   inclusive
     * @return returns 'row,col' or Optional.empty if no gear
     */
    private static Optional<String> isNextToGear(List<String> rows, int row, int colStart, int colEnd) {
        //check top row
        for (int col = colStart - 1; col <= colEnd + 1; col++) {
            if (isGear(rows, row - 1, col))
                return Optional.of((row - 1) + "," + col);
        }

        //check bottom row
        for (int col = colStart - 1; col <= colEnd + 1; col++) {
            if (isGear(rows, row + 1, col))
                return Optional.of((row + 1) + "," + col);
        }

        //check to the sides of the number
        if (isGear(rows, row, colStart - 1))
            return Optional.of(row + "," + (colStart - 1));

        if (isGear(rows, row, colEnd + 1))
            return Optional.of(row + "," + (colEnd + 1));

        return Optional.empty();
    }

    /**
     * is there a symbol here? out of bounds is false.
     *
     * @return true if so.
     */
    private static boolean isSymbol(List<String> rows, int row, int col) {
        if (isOutOfBounds(rows, row, col))
            return false;

        char c = rows.get(row).charAt(col);

        if (Character.isDigit(c))
            return false;

        return c != '.';
    }

    /**
     * is there a '*' here? out of bounds is false.
     *
     * @return true if so.
     */
    private static boolean isGear(List<String> rows, int row, int col) {
        if (isOutOfBounds(rows, row, col))
            return false;

        char c = rows.get(row).charAt(col);

        return c == '*';
    }

    /**
     * true if out of bounds. Otherwise, false.
     *
     * @param rows -
     * @param row  -
     * @param col  -
     * @return -
     */
    private static boolean isOutOfBounds(List<String> rows, int row, int col) {
        if (row < 0 || row >= rows.size())
            return true;

        if (col < 0 || col >= rows.get(row).length())
            return true;

        return false;
    }

}
