package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) {
        var bigMacs = loadData();

        // 3:29, time to finish part 2

        // TODO: Find the BigMac entry for Canada in the year 2022

        // TODO: Create a list containing only the data for Canada

        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically

        // TODO: Print the most recent 5 years of data for Canada

        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD

        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries

    }

//    public static List<BigMac> loadData() {
//        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
//            return lines.map(
//                    // TODO: replace this ENTIRE lambda expression with a function reference that parses the line from the file
//                    //       (see the parseCsvLine method below)
//                    line -> {
//
//                        return new BigMac(1, "CAD", "ca", 1.0, 1.0, 1.0);
//                    })
//                    .toList();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static List<BigMac> loadData() {
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) {
//            return lines.map(line -> {return parseCsvLine(line);} ).toList();
            return lines.map(Main::parseCsvLine).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse a CSV line into a BigMac record. The expected format is:
     * year,currency,country,localPrice,exchangeRate,usdPrice
     * @param line the CSV line to parse
     * @return a BigMac record containing the data from the CSV line
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(",");
        return new BigMac(
                Integer.parseInt(values[0].substring(0,4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }
}
